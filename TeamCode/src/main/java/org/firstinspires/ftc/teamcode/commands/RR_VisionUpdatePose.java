package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.ftclib.geometry.Pose2d;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SUB_VisionAprilTagsPlusAutoDetect;
//import com.acmerobotics.roadrunner.geometry.Pose2d;

import com.qualcomm.robotcore.util.ElapsedTime;

public class RR_VisionUpdatePose extends CommandBase {

	MecanumDriveSubsystem m_drivetrain;
	//	private final VisionAprilTags m_aprilTags;
	private final SUB_VisionAprilTagsPlusAutoDetect m_aprilTags;
	private final ElapsedTime m_runtime = new ElapsedTime();
	private double m_robotSettleTime = 250;
	private double m_dataCollectionTime = 800;
	boolean m_isFinished = false;
	com.acmerobotics.roadrunner.geometry.Pose2d m_currentPose;
	boolean m_fieldCentric;
//	List<Pose2d> m_poseArray = new ArrayList<Pose2d>();

	public RR_VisionUpdatePose(SUB_VisionAprilTagsPlusAutoDetect aprilTags, MecanumDriveSubsystem p_drivetrain, boolean fieldCentric) {
		m_aprilTags = aprilTags;
		m_drivetrain = p_drivetrain;
		m_fieldCentric = fieldCentric;
	}

	public RR_VisionUpdatePose(SUB_VisionAprilTagsPlusAutoDetect aprilTags, MecanumDriveSubsystem p_drivetrain){
		this(aprilTags, p_drivetrain, false);
	}

	@Override
	public void initialize() {
		m_currentPose = m_drivetrain.getPoseEstimate();
		m_aprilTags.getRobotPoseStart();
		m_runtime.reset();
		m_isFinished = false;
	}

	@Override
	public void execute() {
		if (m_runtime.milliseconds() > m_robotSettleTime) // start collecting data after settling time
			m_aprilTags.getRobotPoseUpdate(true, m_currentPose);
		if (m_runtime.milliseconds() > m_dataCollectionTime){ // max collection time reached, get result
			Pose2d pose = m_aprilTags.getRobotPoseFiltered();
			if (pose != null) saveNewPose(pose);
			m_isFinished = true;
		}
	}

	void saveNewPose(Pose2d pose) {
//					Pose2d old = m_odometry.getPose();
//					m_opMode.telemetry.addData("aprilTag seen", "old: (%.2f, %.2f, %.2f) new: (%.2f, %.2f, %.2f)",
//							old.getX(), old.getY(), Math.toDegrees(old.getHeading()), pose.getX(), pose.getY(), Math.toDegrees(pose.getHeading()));
		// This uses odometry heading rather than apriltag heading. Use pose.getHeading() to use apriltag heading.
//					pose = new Pose2d(pose.getX(), pose.getY(), new Rotation2d(old.getHeading()));

		if (pose != null) {
			if(m_fieldCentric){
				m_drivetrain.setPoseEstimate(new com.acmerobotics.roadrunner.geometry.Pose2d(pose.getX(),
						pose.getY(), pose.getHeading() - Math.toRadians(90)));
			}
			else{
				m_drivetrain.setPoseEstimate(new com.acmerobotics.roadrunner.geometry.Pose2d(pose.getX(),
						pose.getY(), pose.getHeading()));
			}
			m_isFinished = true;
		}
	}

	@Override
	public boolean isFinished() {
		return m_isFinished;
	}

	public com.acmerobotics.roadrunner.geometry.Pose2d getPoseEstimate(){
		return new com.acmerobotics.roadrunner.geometry.Pose2d(m_aprilTags.getRobotPose(true).getX()
				,m_aprilTags.getRobotPose(true).getY()
				,m_aprilTags.getRobotPose(true).getHeading());
	}
}
