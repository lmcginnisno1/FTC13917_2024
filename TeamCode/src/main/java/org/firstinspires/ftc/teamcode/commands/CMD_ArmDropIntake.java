package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.ConditionalCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

/**
 * Lowers the arm to the ground for intake, from ready intake position
 */
public class CMD_ArmDropIntake extends SequentialCommandGroup {
    public CMD_ArmDropIntake(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank){
         addRequirements(p_blank);
         addCommands(
              new CMD_WristReleaseOutsideClaw(p_wrist),
              new Sleep(250),
              new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kDropIntake),
              new ParallelCommandGroup(
                      new CMD_SetElbowAngle(p_elbow, Constants.ElbowConstants.kDropIntake),
                      new CMD_SetWristPosition(p_wrist, Constants.WristConstants.kDropIntake)
              ),
              new CMD_WristCloseClaw(p_wrist),
              new Sleep(100),
              new CMD_WristReleaseClaw(p_wrist),
              new Sleep(100),
              new CMD_WristCloseClaw(p_wrist)
         );
    }
}
