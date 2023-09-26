package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;
import org.openftc.easyopencv.OpenCvPipeline;

public class SUB_OpenCvCamera extends SubsystemBase{
    static private OpMode m_opMode;
    static private OpenCvWebcam m_webcam;
    static private int m_frameWidth;
    static private int m_frameHeight;

    public SUB_OpenCvCamera(OpMode p_opMode, final String p_webCameraName) {
        this(p_opMode, p_webCameraName,false);
    };

    public SUB_OpenCvCamera(OpMode p_opMode, final String p_webCameraName, boolean p_debug) {
        // set true to output to the cameraMonitorView
        m_opMode = p_opMode;

        HardwareMap hardwareMap = m_opMode.hardwareMap;
        WebcamName webcamName = hardwareMap.get(WebcamName.class, p_webCameraName);

        if (p_debug) {
            int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            m_webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        } else {
            m_webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName);
        }

    }

    public void setPipeline(OpenCvPipeline p_pipeline) {
        m_webcam.setPipeline(p_pipeline);
    };

    public void startStreaming(int p_frameWidth, int p_frameHeight) {
        m_frameWidth = p_frameWidth;
        m_frameHeight = p_frameHeight;

        m_webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                m_webcam.startStreaming(m_frameWidth, m_frameHeight, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
    }

}
