package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_ToggleArmLevelDown extends CommandBase {
     public CMD_ToggleArmLevelDown(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, GlobalVariables p_variables){
          switch (p_variables.getScoringLevel()){
//               case Two: ArmConstants.setArmLevel(ArmLevel.One);
//                    new CMD_ArmSetLevelOne(p_shoulder, p_elbow, p_wrist);
//                    break;
//               case Three: ArmConstants.setArmLevel(ArmLevel.Two);
//                    new CMD_ArmSetLevelTwo(p_shoulder, p_elbow, p_wrist);
//                    break;
               default: break;
          }
     }
}
