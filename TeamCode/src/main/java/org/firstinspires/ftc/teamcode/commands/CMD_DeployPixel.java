package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_DeployPixel extends SequentialCommandGroup {
     public CMD_DeployPixel(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist
                                   , SUB_Blank p_blank, GlobalVariables p_variables, MecanumDriveSubsystem p_drivetrain){

          addCommands(
//                  new InstantCommand(()-> p_variables.setRobotState(GlobalVariables.RobotState.Transitioning))
                  new ParallelCommandGroup(
                     new InstantCommand(()-> p_elbow.setTargetAngle(Constants.ElbowConstants.kPushIntoBackdrop[p_variables.getScoringLevel()]))
                     ,new InstantCommand(()-> p_shoulder.setTargetAngle(Constants.ShoulderConstants.kPushIntoBackdrop[p_variables.getScoringLevel()]))
                  )
                  ,new Sleep(250)
                  ,new InstantCommand(()-> p_wrist.openPincher())
                  ,new Sleep(200)
                  ,new InstantCommand(()-> p_wrist.setPosition(Constants.WristConstants.kReadyToDeployPosition[p_variables.getScoringLevel()] + Constants.WristConstants.kPushIntoBackDrop[p_variables.getScoringLevel()]))
                  ,new Sleep(300)
                  ,new RR_TrajectoryForwardFromCurrent(p_drivetrain, 6, false)
                  ,new CMD_ArmSetLevelHome(p_shoulder, p_elbow, p_wrist, p_blank)
                  ,new InstantCommand(()-> p_variables.setRobotState(GlobalVariables.RobotState.Home))
          );
     }
}
