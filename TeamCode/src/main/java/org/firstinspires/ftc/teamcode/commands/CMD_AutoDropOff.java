package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

public class CMD_AutoDropOff extends CommandBase{

     MecanumDriveSubsystem m_drivetrain;
     GlobalVariables m_variables;
     double m_leftSide = -29.5;
     double m_rightSide = -42;
     double m_wantedY;
     public CMD_AutoDropOff(MecanumDriveSubsystem p_drivetrain, GlobalVariables p_variables){
          addRequirements(p_drivetrain);

          m_drivetrain = p_drivetrain;
          m_variables = p_variables;
     }

     @Override
     public void initialize(){
          boolean m_leftSideDrop = Math.abs(m_drivetrain.getPoseEstimate().getY()) < 36;

          m_wantedY = m_leftSideDrop ? m_leftSide : m_rightSide;

          Trajectory m_driveToBoard = m_drivetrain.trajectoryBuilder(m_drivetrain.getPoseEstimate(), true)
                  .lineToLinearHeading(new Pose2d(Constants.AutoDropOffConstants.kX, m_wantedY, Math.toRadians(180)))
                  .build();

          m_drivetrain.followTrajectory(m_driveToBoard);
     }

     @Override
     public void execute(){
          m_drivetrain.update();
     }

     @Override
     public boolean isFinished() {
          //end command if it is interrupted or the trajectory is finished
          if(GlobalVariables.firstCycle){
               GlobalVariables.firstCycle = false;
          }
          return Thread.currentThread().isInterrupted() || !m_drivetrain.isBusy();
     }
}
