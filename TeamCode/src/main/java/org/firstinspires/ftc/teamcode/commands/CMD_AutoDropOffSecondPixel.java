package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

public class CMD_AutoDropOffSecondPixel extends CommandBase{

     MecanumDriveSubsystem m_drivetrain;

     double m_leftSlotOffset = 1.5;
     double m_rightSlotOffset = -1.5;
     boolean m_leftSlot;
     GlobalVariables m_variables;

     public CMD_AutoDropOffSecondPixel(MecanumDriveSubsystem p_drivetrain, boolean p_leftSlot
                                        ,GlobalVariables p_variables){
          addRequirements(p_drivetrain);

          m_drivetrain = p_drivetrain;
          m_leftSlot = p_leftSlot;
          m_variables = p_variables;
     }

     @Override
     public void initialize(){
          Pose2d m_robotPose = m_drivetrain.getPoseEstimate();
          int closestTagID = GlobalVariables.closestTagID;
          double wantedY = 0;

          if(closestTagID == 1){
               wantedY = 41.75;
          }else if(closestTagID == 2){
               wantedY = 35.75;
          }else if(closestTagID == 3){
               wantedY = 29.5;
          }else if(closestTagID == 4){
               wantedY = -29.5;
          }else if(closestTagID == 5){
               wantedY = -35.75;
          }else if(closestTagID == 6){
               wantedY = -41.75;
          }else{
               wantedY = m_robotPose.getY();
          }

          double offset = m_leftSlot ? m_leftSlotOffset : m_rightSlotOffset;

          wantedY = m_variables.getScoringLevel() % 2 == 0 ? wantedY : wantedY + offset;

          Trajectory m_dropOffSecondPixel = m_drivetrain.trajectoryBuilder(m_robotPose, true)
                  .lineToLinearHeading(new Pose2d(48, wantedY, Math.toRadians(180)))
                  .build();

          m_drivetrain.followTrajectory(m_dropOffSecondPixel);
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
