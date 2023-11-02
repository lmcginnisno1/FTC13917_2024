package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

/**
 * Ready the arm for intake from home position
 */
public class CMD_ArmSetReadyIntakeLevelFive extends SequentialCommandGroup {
     public CMD_ArmSetReadyIntakeLevelFive(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank){
          addRequirements(p_blank);
          addCommands(
                  new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kReadyIntakeLevelFive).setTolerance(30)
                  ,new Sleep(200)
                  ,new CMD_SetElbowAngle(p_elbow ,Constants.ElbowConstants.kReadyIntakeLevelFive)
                  ,new CMD_SetWristPosition(p_wrist, Constants.WristConstants.kReadyIntakeLevelFive)
          );
     }
}