package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Constants.ArmConstants;
import org.firstinspires.ftc.teamcode.Constants.ArmConstants.*;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_ToggleArmLevelUp extends CommandBase {
     public CMD_ToggleArmLevelUp(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist){
          ArmConstants.ArmLevel m_currentLevel = ArmConstants.getArmLevel();
          switch (m_currentLevel){
//               case One: ArmConstants.setArmLevel(ArmLevel.Two);
//                    new CMD_ArmSetLevelTwo(p_shoulder, p_elbow, p_wrist);
//                    break;
//               case Two: ArmConstants.setArmLevel(ArmLevel.Three);
//                    new CMD_ArmSetLevelThree(p_shoulder, p_elbow, p_wrist);
//                    break;
               default: break;
          }
     }
}
