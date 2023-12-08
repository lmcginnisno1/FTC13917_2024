package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Intake;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_SetReadyToIntakeOff extends SequentialCommandGroup {
    public CMD_SetReadyToIntakeOff(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank,
                                GlobalVariables p_variables, SUB_Intake p_intake){

        addRequirements(p_blank);

        addCommands(
                new InstantCommand(()-> p_variables.setRobotState(GlobalVariables.RobotState.Transitioning))
                ,new InstantCommand(()-> p_elbow.setTargetAngle(-5))
                ,new InstantCommand(()-> p_wrist.PivotHome())
                ,new InstantCommand(()-> p_wrist.setPosition(Constants.WristConstants.kHome + 0.02))
                ,new Sleep(75)
                ,new InstantCommand(()-> p_elbow.setTargetAngle(Constants.ElbowConstants.kHome))
                ,new Sleep(750)
                ,new CMD_SetRobotState(p_variables, GlobalVariables.RobotState.Stow)
        );
    }
}