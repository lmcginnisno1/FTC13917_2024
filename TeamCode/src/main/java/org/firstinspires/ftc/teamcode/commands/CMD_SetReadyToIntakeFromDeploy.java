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

public class CMD_SetReadyToIntakeFromDeploy extends SequentialCommandGroup {
     public CMD_SetReadyToIntakeFromDeploy(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank,
                                 GlobalVariables p_variables, SUB_Intake p_intake){

          addRequirements(p_blank);

          addCommands(
                  new InstantCommand(()-> p_variables.setRobotState(GlobalVariables.RobotState.TransitioningToIntake))
                  ,new InstantCommand(()-> p_wrist.PivotHome())
                  ,new CMD_WristReleaseClaw(p_wrist)
                  ,new CMD_WristSetReadyToIntake(p_wrist, p_variables)
//                  ,new Sleep(500)
//                  ,new CMD_ElbowSetReadyToIntake(p_elbow, p_variables)
                  ,new InstantCommand( ()->p_elbow.setTargetAngle(Constants.ElbowConstants.kReadyToIntakePosition))
//                  ,new CMD_ShoulderSetReadyToIntake(p_shoulder, p_variables)
                  ,new InstantCommand( ()->p_shoulder.setTargetAngle(Constants.ShoulderConstants.kReadyToIntakePosition))
//                  ,new CMD_IntakeConveyorOn(p_intake)
                  ,new InstantCommand(()-> p_intake.pivotServoOut())
                  ,new InstantCommand(()->p_intake.conveyorOn())
                  ,new CMD_SetRobotState(p_variables, GlobalVariables.RobotState.ReadyToIntake)
          );
     }
}
