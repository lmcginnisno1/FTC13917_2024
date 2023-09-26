package org.firstinspires.ftc.teamcode.pipelines;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class Pipeline_DuckHunt extends OpenCvPipeline {
    // Some color constants
    static final Scalar BLUE = new Scalar(0, 0, 255);
    static final Scalar GREEN = new Scalar(0, 255, 0);
    static private int m_frameWidth = 640;
    static private int m_frameHeight = 480;

    // The input image is covered with a 5 x 5 grid of regions, all the same size.
    // Each region is surrounded by an equal amount of space except the outer regions
    // start at the edge of the image.  Guessing the image size is 640 x 480.

    static final int X_REGIONS = 1; // 7;
    static final int Y_REGIONS = 5;
    // static final int FRAME_WIDTH = 320; // 640;
    // static final int FRAME_HEIGHT = 240; // 480;
    static final int REGION_WIDTH = 30;  // Gap is 25
    static final int REGION_HEIGHT = 30; // Gap is 32.5
    static final int YELLOW_THRESHOLD = 95;

    Point[] m_regions = new Point[X_REGIONS * Y_REGIONS];

    Mat[] m_regionCb = new Mat[X_REGIONS * Y_REGIONS];

    // Working variables
    Mat YCrCb = new Mat();
    Mat Cb = new Mat();

    // Volatile since accessed by OpMode thread w/o synchronization
    private volatile int driveDirection = -99; // 0;

    // This function takes the RGB frame, converts to YCrCb,
    // and extracts the Cb channel to the 'Cb' variable
    void inputToCb(Mat input)
    {
        // Adjust the brightness and contrast of the image.  Widen gap between two input
        // numbers to increase contrast(?).
        // Core.normalize(input, input, -50, 305, Core.NORM_MINMAX);

        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
        // For the last parameter to this function: 0 is Y, 1 is Cr, 2 is Cb
        Core.extractChannel(YCrCb, Cb, 2);
    }

    // In the region, it gets the ave Cb value for all pixels that seem to be yellow.
    // Yellowness is any pixel with a Cb less than 50.  If less than a 10% of the
    // pixels are yellowish, then consider that just noise and skip them.
    int getYellowAve(Mat region)
    {
        int sum = 0;
        int count = 0;
        int rows = region.rows();
        int cols = region.cols();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                double yellow = region.get(y, x)[0];
                if (yellow < YELLOW_THRESHOLD) {
                    sum += yellow;
                    count++;
                }
                // if (log && count > 0 && x > 65 && y > 65)
                // m_baseRobot.telemetry.addData("Cb", "%d,%d, Cb: %.0f, sum: %d, count: %d", x, y, region.get(y, x)[0], sum, count);
            }
        }

        // return count < 1 ? 255 : sum / count;
        return count < rows * cols * 0.1 ? 255 : sum / count;
    }

    @Override
    public void init(Mat firstFrame)
    {
        int gapWidth = (m_frameWidth - REGION_WIDTH * X_REGIONS) / Math.max(1, X_REGIONS - 1);
        int gapHeight = (m_frameHeight - REGION_HEIGHT * Y_REGIONS) / Math.max(1, Y_REGIONS - 1);
        int xStart = 0;

        if (X_REGIONS == 1)
            xStart = m_frameWidth / 2 - REGION_WIDTH / 2;

        // regions array first holds the row at the bottom of the image, left to right.
        // Then proceeds one row at a time going up to the top of the image.  Each point
        // is the upper left corner of the region.
        int i = 0;
        for (int y = Y_REGIONS - 1; y >= 0; y--) {
            for (int x = 0; x < X_REGIONS; x++) {
                m_regions[i++] = new Point(xStart + x * (REGION_WIDTH + gapWidth),
                        y * (REGION_HEIGHT + gapHeight));
            }
        }

        inputToCb(firstFrame);

        i = 0;
        for (int y = Y_REGIONS - 1; y >= 0; y--) {
            for (int x = 0; x < X_REGIONS; x++) {
                Point pointB = new Point(m_regions[i].x + REGION_WIDTH, m_regions[i].y + REGION_HEIGHT);
                m_regionCb[i] = Cb.submat(new Rect(m_regions[i], pointB));
                i++;
            }
        }
    }

    @Override
    public Mat processFrame(Mat input)
    {
        inputToCb(input);

        // For yellows, Cb is always very small so find the minimum of each region.
        // The region with the smallest minimum probably contains the duck.
        // Search row by row starting with the row nearest the camera.  Stop searching
        // if any region in the row contains a significant yellow color.  If the center
        // region contains yellow, keep going straight.  Otherwise turn left or right
        // depending on if a region to the left or right is yellow.  Turn more aggressively
        // the further from center the region is.


        // NOTE: Search order maybe should be: Start at middle column nearest camera.  If
        // sufficiently yellow, stop.  Otherwise look one square on each side.  Then look
        // up one row and repeat the same search order.

        int i = 0;
        int col = 0;
        int row = 0;
        int minAve = 255;
        for (int y = 0; y < Y_REGIONS; y++) {
            for (int x = 0; x < X_REGIONS; x++) {
                int ave = getYellowAve(m_regionCb[i]);
                // int ave = 100;
                // m_baseRobot.telemetry.addData("region", "row: %d, col: %d, ave: %d", y, x, ave);
                if (ave < minAve) {
                    col = x;
                    minAve = ave;
                    row = y;
                }
                i++;
            }
            // If the minAve seen so far is yellow enough, stop looking.
            // if (minAve < YELLOW_THRESHOLD)
            // break;
            // row++;
        }

        if (minAve > YELLOW_THRESHOLD) {
            row = Y_REGIONS;
            col = X_REGIONS / 2;
        }

        // if (row == Y_REGIONS)
        // col = X_REGIONS / 2;

        if (minAve > YELLOW_THRESHOLD)
            driveDirection = -99;
        else
            driveDirection = col - X_REGIONS / 2;

//             m_baseRobot.telemetry.addData("hunt", "dir: %d, row: %d, col: %d, minAve: %d, fps: %.1f, frames: %d, time: %d",
//                                           driveDirection, row, col, minAve,
//                                           m_webcam.getFps(), m_webcam.getFrameCount(), m_webcam.getTotalFrameTimeMs());

        i = 0;
        for (int y = 0; y < Y_REGIONS; y++) {
            for (int x = 0; x < X_REGIONS; x++) {
                boolean selected = (row != Y_REGIONS) && (i == row * X_REGIONS + col);
                Point pointB = new Point(m_regions[i].x + REGION_WIDTH, m_regions[i].y + REGION_HEIGHT);
                Imgproc.rectangle(input, m_regions[i], pointB, selected ? GREEN : BLUE, 2);
                i++;
            }
        }

        // Render the annotated 'input' buffer to the viewport.
        return input;
    }

    // Call this from the OpMode thread to obtain the latest analysis
    public int getAnalysis()
    {
        return driveDirection;
    }

    public int getThickness()
    {
        return 0;
    }
};
