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

public class CMD_SetIntakePixel extends SequentialCommandGroup {
    public CMD_SetIntakePixel(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank,
                              GlobalVariables p_variables) {

        addRequirements(p_blank);

        addCommands(
                new InstantCommand(()-> p_variables.setRobotState(GlobalVariables.RobotState.Transitioning))
                ,new ParallelCommandGroup(
                        new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kIntakePickupPosition[p_variables.getIntakeLevel()])
                        ,new CMD_SetElbowAngle(p_elbow, Constants.ElbowConstants.kIntakePickupPosition[p_variables.getIntakeLevel()])
                        ,new CMD_SetWristPosition(p_wrist, Constants.WristConstants.kIntakePickupPosition[p_variables.getIntakeLevel()])
                )
                ,new CMD_WristCloseClaw(p_wrist)
                ,new Sleep(100)
                ,new CMD_ArmSetLevelHome(p_shoulder, p_elbow, p_wrist, p_blank)
                , new InstantCommand(()->p_variables.setRobotState(GlobalVariables.RobotState.Stow))
        );

    }
};