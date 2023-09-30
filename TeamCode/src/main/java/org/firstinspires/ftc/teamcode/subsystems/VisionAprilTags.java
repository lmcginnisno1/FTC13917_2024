package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;
import org.firstinspires.ftc.teamcode.ftclib.geometry.Pose2d;
import org.firstinspires.ftc.teamcode.ftclib.geometry.Rotation2d;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class VisionAprilTags extends SubsystemBase {

	class FieldAprilTag {
		public int id;
		public double x;
		public double y;
		public double angle;
		FieldAprilTag(int p_id, double p_x, double p_y, double p_angle) {
			id = p_id;
			x = p_x;
			y = p_y;
			angle = p_angle;
		}
	}

	// cameraOffset is the distance in inches from the center of the robot to the camera's
	// location.  Angle is in degrees. A camera facing forward that is 8 inches in front and 6 inches
	// to the left of the robot center has offset (8, 6, 0).  A camera in the center of the robot
	// pointing directly to the left has an offset of (0, 0, 90 degrees).
	final double m_cameraOffsetX;
	final double m_cameraOffsetY;
	final double m_cameraOffsetAngle;

	private final OpMode m_opMode;
	private final String m_cameraName;

	private VisionPortal m_visionPortal;               // Used to manage the video source.
	private AprilTagProcessor m_aprilTag;              // Used for managing the AprilTag detection process.
//    private AprilTagDetection desiredTag = null;     // Used to hold the data for a detected AprilTag

	// Set id to the apriltag id. X, Y are in ZooBOTix field coordinates (72, 144 is blue non-audience
	// corner). angle is in degrees. A tag's angle is where an arrow shot through the tag's center from
	// within the field would point.  Currently can only be 0 (top), -90 (red side), -180 (audience
	// side), or 90 (blue side).
	private final FieldAprilTag[] m_aprilTagLocations = {
			new FieldAprilTag(1,132, 41.5, 0),
			new FieldAprilTag(2,132, 35.5, 0),
			new FieldAprilTag(3,132, 29.67, 0),
			new FieldAprilTag(4,132, -29.67, 0),
			new FieldAprilTag(5,132, -35.5, 0),
			new FieldAprilTag(6,132, -41.5, 0),
			new FieldAprilTag(7, 0, -40.5, -180),		// Red, Large
			new FieldAprilTag(8, 0,-35.5, -180),		// Red, Small
			new FieldAprilTag(9, 0,35.5, -180),		// Blue, Small
			new FieldAprilTag(10, 0,40.5, -180)		// Blue, Large
//            new FieldAprilTag(585,0, -24, -180),
//            new FieldAprilTag(585,24, -72, -90),
//			new FieldAprilTag(585,144, 24, 0),
//			new FieldAprilTag(585,24, 72, 90),
//			new FieldAprilTag(1,5,5, 0)
	};

	public VisionAprilTags(final String usbCameraName,
					   double cameraOffsetX, double cameraOffsetY, double cameraOffsetAngle, OpMode p_opMode) {
		m_cameraName = usbCameraName;
		m_cameraOffsetX = cameraOffsetX;
		m_cameraOffsetY = cameraOffsetY;
		m_cameraOffsetAngle = cameraOffsetAngle;
		m_opMode = p_opMode;
		initAprilTag();
	}

	@Override
	public void periodic() {
		 telemetry();
	}

	private void initAprilTag() {
		// Create the AprilTag processor by using a builder.
		m_aprilTag = new AprilTagProcessor.Builder().build();

		// Create the vision portal by using a builder.
		m_visionPortal = new VisionPortal.Builder()
							.setCamera(m_opMode.hardwareMap.get(WebcamName.class, m_cameraName))
							.addProcessor(m_aprilTag)
							.build();
	}

	public Pose2d getRobotPose(boolean offset) {

		List<AprilTagDetection> currentDetections = m_aprilTag.getDetections();
		for (AprilTagDetection detection: currentDetections) {
			if (detection.metadata != null) {
				return getRobotPoseFromAprilTagPose(detection.id, detection.ftcPose, offset);
			}
		}

		return null;
	}

	private Pose2d getRobotPoseFromAprilTagPose(int id, AprilTagPoseFtc ftcPose, boolean offset) {
		int index = 0;
		while (index < m_aprilTagLocations.length) {
			if (m_aprilTagLocations[index].id == id)
				break;
			index++;
		}
		if (index >= m_aprilTagLocations.length)
			return null;

		FieldAprilTag tag = m_aprilTagLocations[index];

		double angle = angleWrap(Math.toRadians(ftcPose.bearing - ftcPose.yaw));
		double x = ftcPose.range * Math.cos(angle);
		double y = ftcPose.range * Math.sin(angle);
		if (tag.angle == 90) {
			x = tag.x + x;
			y = tag.y - y;
			angle = angleWrap(angle + Math.toRadians(90));
		}
		else if (tag.angle == 0) {
			x = tag.x - x;
			y = tag.y - y;
		}
		else if (tag.angle == -90) {
			x = tag.x - x;
			y = tag.y + y;
			angle = angleWrap(angle + Math.toRadians(-90));
		}
		else if (tag.angle == -180) {
			x = tag.x + x;
			y = tag.y + y;
			angle = angleWrap(angle - Math.toRadians(180));
		}

		m_opMode.telemetry.addData("XYH", "%6.1f %6.1f %6.1f", x, y, Math.toDegrees(angle));

		// Find the robot's heading in field coordinates.  (Non-audience side is zero, blue side is 90).
		double heading = angleWrap(Math.toRadians(tag.angle + -ftcPose.yaw));

		// The camera usually isn't directly in the middle of the robot. Adjust the pose to
		// include the offset of the camera from the center of the robot.  This only depends on the
		// direction the robot is facing--the aprilTag position isn't needed.
		if (offset) {
			double xVertOffset = Math.abs(m_cameraOffsetX * Math.cos(heading)) * Math.signum(m_cameraOffsetX);
			double xHorzOffset = Math.abs(m_cameraOffsetY * Math.sin(heading)) * Math.signum(m_cameraOffsetY);
			double yVertOffset = Math.abs(m_cameraOffsetX * Math.sin(heading)) * Math.signum(m_cameraOffsetX);
			double yHorzOffset = Math.abs(m_cameraOffsetY * Math.cos(heading)) * Math.signum(m_cameraOffsetY);

			// Adjust robot heading based on the direction the camera is facing.
			heading = angleWrap(heading + -Math.toRadians(m_cameraOffsetAngle));

			// Quad 1 (upper left)
			if (heading >= Math.toRadians(0) && heading <= Math.toRadians(90)) {
				return new Pose2d(x - xVertOffset - xHorzOffset, y - yVertOffset + yHorzOffset, new Rotation2d(heading));
			}
			// Quad 2 (lower left)
			else if (heading > Math.toRadians(90) && heading <= Math.toRadians(180)) {
				return new Pose2d(x + xVertOffset - xHorzOffset, y - yVertOffset - yHorzOffset, new Rotation2d(heading));
			}
			// Quad 3 (lower right)
			else if (heading <= Math.toRadians(-90) && heading >= Math.toRadians(-180)) {
				return new Pose2d(x + xVertOffset + xHorzOffset, y + yVertOffset - yHorzOffset, new Rotation2d(heading));
			}
			// Quad 4 (upper right)
			else {
				return new Pose2d(x - xVertOffset + xHorzOffset, y + yVertOffset + yHorzOffset, new Rotation2d(heading));
			}
		}

		return new Pose2d(x, y, new Rotation2d(heading));
	}

	public void telemetry() {
		Pose2d pose = getRobotPose(false);
		if (pose != null)
			m_opMode.telemetry.addData("pose", "XY (%.1f,%.1f) Heading(%.1f)",
				pose.getX(), pose.getY(), Math.toDegrees(pose.getHeading()));
		pose = getRobotPose(true);
		if (pose != null)
			m_opMode.telemetry.addData("pose+offset", "XY (%.1f,%.1f) Heading(%.1f)",
					pose.getX(), pose.getY(), Math.toDegrees(pose.getHeading()));

		List<AprilTagDetection> currentDetections = m_aprilTag.getDetections();
		m_opMode.telemetry.addData("# AprilTags Detected", currentDetections.size());

		// Step through the list of detections and display info for each one.
		for (AprilTagDetection detection : currentDetections) {
			if (detection.metadata != null) {
				m_opMode.telemetry.addData("\n==== ID:", "%d %s", detection.id, detection.metadata.name);
				m_opMode.telemetry.addData("XYZ", "%6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z);
				m_opMode.telemetry.addData("PRY", "%6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw);
				m_opMode.telemetry.addData("RBE", "%6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation);
			} else {
				m_opMode.telemetry.addData("\n==== ID:", "%d Unknown", detection.id);
				m_opMode.telemetry.addData("Center", "%6.0f %6.0f   (pixels)", detection.center.x, detection.center.y);
			}
		}   // end for() loop

		// Add "key" information to telemetry
		m_opMode.telemetry.addData("\nkey", "\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
		m_opMode.telemetry.addData("PRY", "Pitch, Roll & Yaw (XYZ Rotation)");
		m_opMode.telemetry.addData("RBE", "Range, Bearing & Elevation");
	}

	private static double angleWrap(double angle) {
		if (angle > 0)
			return ((angle + Math.PI) % (Math.PI * 2)) - Math.PI;
		else
			return ((angle - Math.PI) % (Math.PI * 2)) + Math.PI;
	}
}
