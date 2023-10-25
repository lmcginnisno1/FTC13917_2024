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
public class CMD_ArmSetReadyIntake extends SequentialCommandGroup {
    public CMD_ArmSetReadyIntake(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank){
        addRequirements(p_blank);
        addCommands(
                new CMD_WristReleaseClaw(p_wrist)
                ,new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kReadyIntake)
                ,new Sleep(200)
                ,new ParallelCommandGroup(
                        new CMD_SetElbowAngle(p_elbow ,Constants.ElbowConstants.kReadyIntake)
                        ,new InstantCommand(()-> p_wrist.setClawBPosition(Constants.WristConstants.kClawBHalfOpen))
                        ,new CMD_SetShoulderAngle(p_shoulder, 36)
                )
                ,new CMD_SetWristPosition(p_wrist, Constants.WristConstants.kReadyIntake)
        );
    }
}