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
import java.util.function.IntSupplier;

public class CMD_SetReadyToDeploy extends SequentialCommandGroup {
    public CMD_SetReadyToDeploy(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank,
                                GlobalVariables p_variables, SUB_Intake p_intake){

        addRequirements(p_blank);

        addCommands(
                new InstantCommand(()-> p_variables.setRobotState(GlobalVariables.RobotState.Transitioning))
                ,new InstantCommand(()-> p_shoulder.setTargetAngle(90))
                ,new InstantCommand(()-> p_elbow.setTargetAngle(-60))
                ,new Sleep(200)
                ,new InstantCommand(()-> p_wrist.setPosition(Constants.WristConstants.kReadyToDeployPosition[p_variables.getScoringLevel()]))
                ,new InstantCommand(()-> p_variables.setRotation(1))
                ,new InstantCommand(()-> p_wrist.movePivotServo(Constants.WristConstants.kPivotRotation[p_variables.getRotation()]))
                ,new InstantCommand(()-> p_shoulder.setTargetAngle(Constants.ShoulderConstants.kReadyToDeployPosition[p_variables.getScoringLevel()]))
                ,new InstantCommand(()-> p_elbow.setTargetAngle(Constants.ElbowConstants.kReadyToDeployPosition[p_variables.getScoringLevel()]))
                ,new CMD_SetRobotState(p_variables, GlobalVariables.RobotState.ReadyToDeploy)
        );
    }
}
