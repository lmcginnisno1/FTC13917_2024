package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;

import org.firstinspires.ftc.teamcode.ftclib.geometry.Pose2d;
import org.firstinspires.ftc.teamcode.ftclib.geometry.Rotation2d;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.VisionAprilTags;

//import org.firstinspires.ftc.teamcode.ftclib.gamepad.GamepadEx;
//import org.firstinspires.ftc.teamcode.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.function.DoubleSupplier;
import java.util.ArrayList;
import java.util.List;

public class VisionUpdatePose extends CommandBase {

	MecanumDriveSubsystem m_drivetrain;
	private final VisionAprilTags m_aprilTags;
	private final ElapsedTime m_runtime = new ElapsedTime();
	private double m_Milliseconds = 1000;
	private boolean m_shouldBeClose = true;

	List<Pose2d> m_poseArray = new ArrayList<Pose2d>();

	public VisionUpdatePose(VisionAprilTags aprilTags, MecanumDriveSubsystem p_drivetrain) {
		m_aprilTags = aprilTags;
		m_drivetrain = p_drivetrain;
		m_Milliseconds = 1000;
		m_shouldBeClose = true;
	}

	public VisionUpdatePose(VisionAprilTags aprilTags, MecanumDriveSubsystem p_drivetrain, double runTime) {
		this(aprilTags, p_drivetrain);
		m_Milliseconds = runTime;
	}

	public VisionUpdatePose(VisionAprilTags aprilTags, MecanumDriveSubsystem p_drivetrain, boolean shouldBeClose) {
		this(aprilTags, p_drivetrain);
		m_shouldBeClose = shouldBeClose;
	}

	@Override
	public void initialize() {
		m_runtime.reset();
	}

	@Override
	public void execute() {
		Pose2d pose = m_aprilTags.getRobotPose(true);
		if (pose != null)
			m_poseArray.add(pose);

//        if (m_VuforiaNav.seeTarget()) {
//            VectorF vectorF = m_VuforiaNav.getRobotLocationFtclib();
//            double heading = m_VuforiaNav.getHeadingFtclib();
//            double x = vectorF.get(0);
//            double y = vectorF.get(1);
//            m_poseArray.add(new Pose2d(x, y, new Rotation2d(Math.toRadians(heading))));
//        }

		telemetry();
	}

	@Override
	public boolean isFinished() {
		if (m_poseArray.size() != 0) {
			m_drivetrain.setPoseEstimate(new com.acmerobotics.roadrunner.geometry.Pose2d(m_poseArray.get(0).getX(),
				m_poseArray.get(0).getY(),
				m_poseArray.get(0).getHeading()));

			m_poseArray.clear();
			return true;
		}


//        final int numSame = 1;
//        final double maxDiff = 1.0 * (numSame - 1);
//
//        if (m_poseArray.size() >= numSame) {
//
//            // Look at the last numSame readings.  If they are all only a small amount
//            // different, then use that pose.  Keep reading until the readings settle down.
//            double diffX = 0;
//            double diffY = 0;
//            double diffH = 0;
//            int index = m_poseArray.size() - 1;
//            for (int i = m_poseArray.size() - numSame; i < m_poseArray.size() - 1; i++) {
//                diffX += Math.abs(m_poseArray.get(index).getX() - m_poseArray.get(i).getX());
//                diffY += Math.abs(m_poseArray.get(index).getY() - m_poseArray.get(i).getY());
//                diffH += Math.abs(m_poseArray.get(index).getHeading() - m_poseArray.get(i).getHeading());
//            }
//
//            if (diffX < maxDiff && diffY < maxDiff && diffH < maxDiff) {
//                diffX = Math.abs(m_poseArray.get(index).getX() - m_odometry.getX());
//                diffY = Math.abs(m_poseArray.get(index).getY() - m_odometry.getY());
//                diffH = Math.abs(m_poseArray.get(index).getHeading() - m_odometry.getHeading());
//                if (!m_shouldBeClose || (diffX < 18 && diffY < 18 && diffH < 20)) {
//                    m_odometry.m_lastVufPose = m_poseArray.get(index);
//                    m_odometry.updatePose(m_poseArray.get(index));
////                    m_LED.pattern(RevBlinkinLedDriver.BlinkinPattern.GREEN).duration(2000).blink(500, 200);
//                    m_poseArray.clear();
//                    return true;
//                }
//            }
//        }

		// If Vuforia never returns consistent results, give up and don't update the odometry pose.
		if (m_runtime.milliseconds() > m_Milliseconds) {
			m_poseArray.clear();
			return true;
		}

		return false;
	}

	public void telemetry() {
//        if (m_poseArray.size() > 0)
//            m_odometry.telemetry.addData("UpdVuf: ", "size: %d, ms = %.1f, pose: (%.1f, %.1f, %.1f)",
//                    m_poseArray.size(), m_runtime.milliseconds(),
//                    m_poseArray.get(m_poseArray.size() - 1).getX(),
//                    m_poseArray.get(m_poseArray.size() - 1).getY(),
//                    m_poseArray.get(m_poseArray.size() - 1).getHeading());
	}
}
