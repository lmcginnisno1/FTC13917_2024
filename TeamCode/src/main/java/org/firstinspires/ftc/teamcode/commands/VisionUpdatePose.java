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
	boolean m_isFinished = false;

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
		m_isFinished = false;
	}

	@Override
	public void execute() {
		Pose2d pose = m_aprilTags.getRobotPose(true);
		if (pose != null) {
			m_drivetrain.setPoseEstimate(new com.acmerobotics.roadrunner.geometry.Pose2d(pose.getX(),
				pose.getY(), pose.getHeading()));
			m_isFinished = true;
		}
	}

	@Override
	public boolean isFinished() {
		// If Vuforia never returns consistent results, give up and don't update the odometry pose.
		if (m_runtime.milliseconds() > m_Milliseconds) {
			m_poseArray.clear();
			return true;
		}

		return m_isFinished;

	}
}
