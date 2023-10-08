package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_ArmSetLevelHome extends ParallelCommandGroup {
     public CMD_ArmSetLevelHome(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow){
//          addRequirements(p_shoulder, p_elbow);
          addCommands(
               new CMD_SetShoulderAngle(p_shoulder, 0),
               new SequentialCommandGroup(
                    new CMD_SetElbowAngle(p_elbow, -22),
                    new Sleep(1000),
                    new CMD_SetElbowAngle(p_elbow, -2)
               )
          );
     }
}
