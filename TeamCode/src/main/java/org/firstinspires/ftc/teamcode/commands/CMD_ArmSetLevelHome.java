package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_DroneLauncher;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_ArmSetLevelHome extends SequentialCommandGroup {
     public CMD_ArmSetLevelHome(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank){
          addRequirements(p_blank);
          addCommands(
                  new ParallelCommandGroup(
                         new CMD_SetWristPosition(p_wrist,.52)
                          ,new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kHome)
                         ,new CMD_SetElbowAngle(p_elbow, -5)
                  )
                  ,new InstantCommand(()-> p_wrist.setPosition(Constants.WristConstants.kHome))
                  ,new InstantCommand(() -> p_elbow.setTargetAngle(Constants.ElbowConstants.kHome))

          );
     }
}
