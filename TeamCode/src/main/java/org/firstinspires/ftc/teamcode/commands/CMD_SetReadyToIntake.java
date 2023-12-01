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

public class CMD_SetReadyToIntake extends SequentialCommandGroup {
     public CMD_SetReadyToIntake(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank,
                                 GlobalVariables p_variables, SUB_Intake p_intake){

         addRequirements(p_blank);

         addCommands(
                 new CMD_SetRobotState(p_variables, GlobalVariables.RobotState.Transitioning)
                 ,new CMD_WristReleaseClaw(p_wrist)
                 ,new CMD_IntakeConveyorOn(p_intake)
                 ,new CMD_IntakeMiddleServoOn(p_intake)
                 ,new InstantCommand(()-> p_wrist.IntakePivotHome())
                 ,new InstantCommand(()->p_wrist.openPincher())
                 ,new InstantCommand(()->p_wrist.setPosition(Constants.WristConstants.kReadyToIntakePosition[2]))
                 , new ParallelCommandGroup(
                        new CMD_ShoulderSetReadyToIntake(p_shoulder, p_variables)
                        ,new CMD_ElbowSetReadyToIntake(p_elbow , p_variables)
                 )
                 ,new CMD_WristSetReadyToIntake(p_wrist , p_variables)
                 ,new InstantCommand(()-> p_intake.pivotServoOut())
                 ,new CMD_SetRobotState(p_variables, GlobalVariables.RobotState.ReadyToIntake)
         );
     }
}
