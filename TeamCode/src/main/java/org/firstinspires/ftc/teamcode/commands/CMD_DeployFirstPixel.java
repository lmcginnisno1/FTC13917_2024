package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_DeployFirstPixel extends SequentialCommandGroup {
     public CMD_DeployFirstPixel(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist,
                                 SUB_Blank p_blank, GlobalVariables p_variables){
          addCommands(
             new InstantCommand(()-> p_variables.setRobotState(GlobalVariables.RobotState.Transitioning))
             ,new InstantCommand(()-> p_shoulder.setTargetAngle(
                     Constants.ShoulderConstants.kReadyToDeployPosition[p_variables.getScoringLevel()]
                             + Constants.ShoulderConstants.kPushIntoBackdrop[p_variables.getScoringLevel()]
             ))
             ,new InstantCommand(()-> p_elbow.setTargetAngle(
                     Constants.ElbowConstants.kReadyToDeployPosition[p_variables.getScoringLevel()]
                             - Constants.ElbowConstants.kPushIntoBackdrop[p_variables.getScoringLevel()]))
             ,new WaitCommand(500)
             ,new CMD_WristReleaseOutsideClaw(p_wrist)
             ,new CMD_ArmBackOffBackdrop(p_shoulder, p_elbow, p_wrist, p_blank, p_variables)
             ,new InstantCommand(()-> p_variables.increaseScoringLevel())
             ,new CMD_ShoulderSetReadyToDeploy(p_shoulder, p_variables)
             ,new CMD_ElbowSetReadyToDeploy(p_elbow, p_variables)
             ,new CMD_WristSetReadyToDeploy(p_wrist, p_variables)
             ,new InstantCommand(()-> p_variables.setRobotState(GlobalVariables.RobotState.ReadyToDeploy))
          );

     }
}
