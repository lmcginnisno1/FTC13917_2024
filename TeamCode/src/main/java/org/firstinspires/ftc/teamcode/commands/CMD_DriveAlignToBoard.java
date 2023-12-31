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
          m_drivetrain.turn(Math.toRadians(180) - m_drivetrain.getPoseEstimate().getHeading());
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
