package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_ArmSetLevelHome extends SequentialCommandGroup {
     public CMD_ArmSetLevelHome(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank){
          addRequirements(p_blank);
          addCommands(
               new ParallelCommandGroup(
                       new CMD_SetShoulderAngle(p_shoulder, 50).setTolerance(30),
                       new SequentialCommandGroup(
                               new Sleep(500),
                               new CMD_SetWristPosition(p_wrist, Constants.WristConstants.kHome),
                               new CMD_SetElbowAngle(p_elbow, Constants.ElbowConstants.kParallel)
                       )
               ),
               new ParallelCommandGroup(
                       new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kHome),
                       new SequentialCommandGroup(
                               new Sleep(500),
                               new CMD_SetElbowAngle(p_elbow, Constants.ElbowConstants.kHome)
                       )

               )
          );
     }
}
