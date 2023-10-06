package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_ArmSetLevelTwo extends SequentialCommandGroup {
     public CMD_ArmSetLevelTwo(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist){
          addRequirements(p_shoulder, p_elbow, p_wrist);
          addCommands(

          );
     }
}
