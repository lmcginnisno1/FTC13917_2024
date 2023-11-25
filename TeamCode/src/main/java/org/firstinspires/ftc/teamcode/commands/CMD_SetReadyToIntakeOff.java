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
                new CMD_SetRobotState(p_variables, GlobalVariables.RobotState.Transitioning)
                ,new CMD_IntakeOff(p_intake)
                ,new CMD_IntakeConveyorOff(p_intake)
                ,new InstantCommand(()-> p_intake.pivotServoHome())
                //first scrape
//                ,new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kHome + 5)
                ,new CMD_SetElbowAngle(p_elbow,-3)
                ,new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kHome)
                ,new CMD_SetWristPosition(p_wrist, Constants.WristConstants.kHome - .025)
                //second scrape
                ,new Sleep(500)
                ,new ParallelCommandGroup(
                        new CMD_ShoulderSetReadyToIntake(p_shoulder, p_variables)
                        ,new CMD_ElbowSetReadyToIntake(p_elbow , p_variables)
                )
                ,new CMD_WristSetReadyToIntake(p_wrist , p_variables)
                ,new Sleep(500)
//                ,new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kHome + 5)
                ,new CMD_SetElbowAngle(p_elbow,-3)
                ,new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kHome)
                ,new CMD_SetWristPosition(p_wrist, Constants.WristConstants.kHome - .025)
                ,new CMD_SetRobotState(p_variables, GlobalVariables.RobotState.Stow)
        );
    }
}