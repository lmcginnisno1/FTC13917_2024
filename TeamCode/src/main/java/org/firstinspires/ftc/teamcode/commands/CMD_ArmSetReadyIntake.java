package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

/**
 * Ready the arm for intake from home position
 */
public class CMD_ArmSetReadyIntake extends ParallelCommandGroup {
    public CMD_ArmSetReadyIntake(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank){
        addRequirements(p_blank);
        addCommands(
            new CMD_WristReleaseOutsideClaw(p_wrist),
            new CMD_WristReleaseClaw(p_wrist),
            new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kPreReadyIntake),
            new SequentialCommandGroup(
                    new Sleep(750),
                    new ParallelCommandGroup(
                            new CMD_SetElbowAngle(p_elbow, Constants.ElbowConstants.kReadyIntake),
                            new SequentialCommandGroup(
                                   new Sleep(500),
                                   new CMD_SetWristPosition(p_wrist, Constants.WristConstants.kReadyIntake),
                                   new Sleep(750),
                                   new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kReadyIntake),
                                   new CMD_SetElbowAngle(p_elbow, Constants.ElbowConstants.kReadyFinal)
                            )
                    )
            )
        );
    }
}