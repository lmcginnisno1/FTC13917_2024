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
import java.util.function.IntSupplier;

public class CMD_SetReadyToDeploy extends SequentialCommandGroup {
    public CMD_SetReadyToDeploy(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank,
                              GlobalVariables p_variables){

        addRequirements(p_blank);

        addCommands(
                new CMD_SetRobotState(p_variables, GlobalVariables.RobotState.Transitioning)
                ,new CMD_WristCloseClaw(p_wrist)
                ,new Sleep(750)
                // lift pixel off the platform
                , new ParallelCommandGroup(
                        new CMD_SetShoulderAngle(p_shoulder,20)//6
                        ,new CMD_SetElbowAngle(p_elbow,-5)//-4
                        ,new InstantCommand(()-> p_wrist.setPosition(Constants.WristConstants.kHome-.05))
                )
                //get wrist ready to deploy then move shoulder up so that the elbow can get to where it needs to go
                //then move shoulder to where it needs to go and update robot state
                ,new CMD_WristSetReadyToDeploy(p_wrist,p_variables)
                ,new CMD_SetShoulderAngle(p_shoulder, 40)
                ,new CMD_ElbowSetReadyToDeploy(p_elbow,p_variables)
                ,new CMD_ShoulderSetReadyToDeploy(p_shoulder,p_variables)
                ,new CMD_SetRobotState(p_variables, GlobalVariables.RobotState.ReadyToDeploy)
        );
    }
}
