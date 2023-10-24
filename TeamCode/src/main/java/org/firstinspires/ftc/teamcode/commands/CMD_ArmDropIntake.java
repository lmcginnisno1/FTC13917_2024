package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.ConditionalCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
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
              new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kPreDropIntake)
              ,new Sleep(200)
              ,new ParallelCommandGroup(
                    new CMD_SetElbowAngle(p_elbow ,Constants.ElbowConstants.kDropIntake)
                    ,new InstantCommand(()-> p_wrist.setClawBPosition(Constants.WristConstants.kClawBHalfOpen))
                    ,new CMD_SetShoulderAngle(p_shoulder, 38)
                    ,new SequentialCommandGroup(
                       new CMD_SetWristPosition(p_wrist, .675)
                       ,new Sleep(250)
                       ,new CMD_SetWristPosition(p_wrist, .6)
                       ,new Sleep(250)
                       ,new CMD_SetWristPosition(p_wrist, Constants.WristConstants.kDropIntake)
                    )
              )
              ,new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kDropIntake)
              ,new CMD_WristCloseClaw(p_wrist)
              ,new CMD_ArmSetLevelHome(p_shoulder, p_elbow, p_wrist, p_blank)
         );
    }
}
