package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

public class CMD_DriveAlignToBoard extends CommandBase{
     MecanumDriveSubsystem m_drivetrain;
     public CMD_DriveAlignToBoard(MecanumDriveSubsystem p_drivetrain){
          m_drivetrain = p_drivetrain;
     }

     @Override
     public void initialize(){
          Pose2d m_robotPose = m_drivetrain.getPoseEstimate();
          double turnAngle = Math.toRadians(180) - m_robotPose.getHeading();

          TrajectorySequence m_alignToBoard = m_drivetrain.trajectorySequenceBuilder(m_robotPose)
                  .turn(turnAngle)
                  .build();

          m_drivetrain.followTrajectorySequence(m_alignToBoard);
     }

     @Override
     public void execute(){
          m_drivetrain.update();
     }

     @Override
     public boolean isFinished(){
          return Thread.currentThread().isInterrupted() || !m_drivetrain.isBusy();
     }
}
