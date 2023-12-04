package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

public class CMD_AutoDropOff extends CommandBase{

     MecanumDriveSubsystem m_drivetrain;
     GlobalVariables m_variables;
     boolean m_leftSideBoard;

     public CMD_AutoDropOff(MecanumDriveSubsystem p_drivetrain, GlobalVariables p_variables){
          addRequirements(p_drivetrain);

          m_drivetrain = p_drivetrain;
          m_variables = p_variables;
     }

     @Override
     public void initialize(){
          Pose2d m_robotPose = m_drivetrain.getPoseEstimate();
          int closestTagID = GlobalVariables.closestTagID;
          double wantedY;

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

          //determine if we are on the left or right side of the board
          m_leftSideBoard = Math.abs(m_robotPose.getY()) > 36;

          //move the robot 1.5 inches left or right based on which side of the board we are on
          double offset = m_leftSideBoard ? Math.copySign(1.5, m_robotPose.getY()) : Math.copySign(-1.5, m_robotPose.getY());

          //offset on even drop-off levels and on left and right sides of the board
          wantedY+= m_variables.getScoringLevel() % 2 == 0 ? (offset * 2) : offset;

          //make sure we aren't going to far left or right on the board that we miss the pixels
          if(Math.abs(wantedY) > 40){
               wantedY = Math.copySign(30, wantedY);
          }else if(Math.abs(wantedY) < 30) {
               wantedY = Math.copySign(40, wantedY);
          }

          Trajectory m_dropOffSecondPixel = m_drivetrain.trajectoryBuilder(m_robotPose, true)
                  .lineToLinearHeading(new Pose2d(50, wantedY, Math.toRadians(180)))
                  .build();

          m_drivetrain.followTrajectory(m_dropOffSecondPixel);
     }

     @Override
     public void execute(){
          m_drivetrain.update();
     }

     @Override
     public boolean isFinished() {
          //end command if it is interrupted or the trajectory is finished
          return Thread.currentThread().isInterrupted() || !m_drivetrain.isBusy();
     }
}
