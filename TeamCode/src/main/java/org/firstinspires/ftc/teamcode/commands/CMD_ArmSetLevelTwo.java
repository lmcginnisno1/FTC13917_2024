package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;

public class CMD_ArmSetLevelTwo extends SequentialCommandGroup {
     public CMD_ArmSetLevelTwo(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow){
          addRequirements(p_shoulder, p_elbow);
          addCommands(

          );
     }
}
