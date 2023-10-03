package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Arm;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;

public class CMD_ArmSetLevelHome extends SequentialCommandGroup {
     public CMD_ArmSetLevelHome(SUB_Arm p_arm){
          addRequirements(p_arm);
          addCommands(

          );
     }
}
