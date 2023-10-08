package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_ArmSetLevelOne extends ParallelCommandGroup {
     public CMD_ArmSetLevelOne(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow){
//          addRequirements(p_shoulder, p_elbow);
          addCommands(
               new CMD_SetShoulderAngle(p_shoulder, 135+6),
               new SequentialCommandGroup(
                       new Sleep(1000),
                       new CMD_SetElbowAngle(p_elbow, -75)
               )
          );
     }
}
