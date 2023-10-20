package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_DeployArm extends SequentialCommandGroup {
     public CMD_DeployArm(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank,
                          GlobalVariables.ScoringLevel p_scoringLevel){
          switch (p_scoringLevel){
               case One:
                    new CMD_ArmSetLevelOne(p_shoulder, p_elbow, p_wrist, p_blank);
                    break;
               case Two:
                    new CMD_ArmSetLevelTwo(p_shoulder, p_elbow, p_wrist, p_blank);
                    break;
               default: break;
          }
     }
}
