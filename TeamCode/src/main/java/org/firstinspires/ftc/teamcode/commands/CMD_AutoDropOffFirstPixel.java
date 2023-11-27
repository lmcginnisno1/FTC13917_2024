package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

public class CMD_AutoDropOffFirstPixel extends CommandBase{

     MecanumDriveSubsystem m_drivetrain;


     double maxY = 45;
     double minY = 24;

     public CMD_AutoDropOffFirstPixel(MecanumDriveSubsystem p_drivetrain){
          addRequirements(p_drivetrain);

          m_drivetrain = p_drivetrain;
     }

     @Override
     public void initialize(){
          Pose2d m_robotPose = m_drivetrain.getPoseEstimate();


          double firstPixelY;
          boolean redSide = m_robotPose.getY() < 0;

          if(Math.abs(m_robotPose.getY()) > maxY){
               firstPixelY = maxY;
          }else if(Math.abs(m_robotPose.getY()) < minY){
               firstPixelY = minY;
          }else{
               firstPixelY = Math.abs(m_robotPose.getY());
          }

          firstPixelY = redSide ? firstPixelY * -1 : firstPixelY;

          Trajectory m_dropOffFirstPixel = m_drivetrain.trajectoryBuilder(m_robotPose, true)
                  .lineToLinearHeading(new Pose2d(50, firstPixelY, Math.toRadians(180)))
                  .build();

          m_drivetrain.followTrajectory(m_dropOffFirstPixel);
     }

     @Override
     public void execute(){
          m_drivetrain.update();
     }

     @Override
     public boolean isFinished() {
          return Thread.currentThread().isInterrupted() || !m_drivetrain.isBusy();
     }
}
