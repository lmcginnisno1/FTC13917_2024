package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

/**
 * Goes to a level one position from the home position
 */
public class CMD_ArmSetLevelOne extends ParallelCommandGroup {
     public CMD_ArmSetLevelOne(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, GlobalVariables p_variables){
          p_variables.setRobotState(GlobalVariables.RobotState.Score);
          addCommands(
               new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kLevelOne),
               new CMD_SetElbowAngle(p_elbow, Constants.ElbowConstants.kLevelOne),
               new SequentialCommandGroup(
                       new Sleep(500),
                       new CMD_SetWristPosition(p_wrist, Constants.WristConstants.kLevelOne)
               )
          );
     }
}
