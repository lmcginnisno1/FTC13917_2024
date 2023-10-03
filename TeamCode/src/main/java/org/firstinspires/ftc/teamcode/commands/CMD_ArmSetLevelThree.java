package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Arm;

public class CMD_ArmSetLevelThree extends SequentialCommandGroup {
     public CMD_ArmSetLevelThree(SUB_Arm p_arm){
          addRequirements(p_arm);
          addCommands(

          );
     }
}
