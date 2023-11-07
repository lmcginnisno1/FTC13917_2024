package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;

public class RR_TrajectoryLineToLinearHeadingFromCurrent extends CommandBase {

     RobotContainer m_robot;
     private final Pose2d m_endPose;
     private boolean m_reversed;

     public RR_TrajectoryLineToLinearHeadingFromCurrent(RobotContainer p_robot, Pose2d p_endPose, boolean p_reversed) {
          m_robot = p_robot;
          m_endPose = p_endPose;
          m_reversed = p_reversed;

          addRequirements(m_robot.drivetrain);
     }

     public RR_TrajectoryLineToLinearHeadingFromCurrent(RobotContainer p_robot, Pose2d p_endPose) {
          this(p_robot, p_endPose, false);
     }

     @Override
     public void initialize() {
          Pose2d poseEstimate = m_robot.drivetrain.getPoseEstimate();
          Trajectory trajectory = m_robot.drivetrain.trajectoryBuilder(poseEstimate,m_reversed)
                  .lineToLinearHeading(m_endPose)
                  .build();

          m_robot.drivetrain.followTrajectory(trajectory);
     }

     @Override
     public void execute() {
          m_robot.drivetrain.update();
     }

     @Override
     public void end(boolean interrupted) {
          if (interrupted) {
               m_robot.drivetrain.stop();
          }
     }

     @Override
     public boolean isFinished() {
          return Thread.currentThread().isInterrupted() || !m_robot.drivetrain.isBusy();
     }

}
