package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.Command;
import org.firstinspires.ftc.teamcode.ftclib.command.SelectCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

import java.util.HashMap;

public class CMD_DeployArm extends SequentialCommandGroup {
     public CMD_DeployArm(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank,
                          GlobalVariables p_variables){
          SelectCommand deployArm = new SelectCommand(
                  new HashMap<Object, Command>(){{
                       put(GlobalVariables.ScoringLevel.One, new CMD_ArmSetLevelOne(p_shoulder, p_elbow, p_wrist, p_blank));
                       put(GlobalVariables.ScoringLevel.Two, new CMD_ArmSetLevelTwo(p_shoulder, p_elbow, p_wrist, p_blank));
                       put(GlobalVariables.ScoringLevel.Three, new CMD_ArmSetLevelThree(p_shoulder, p_elbow, p_wrist, p_blank));
                       put(GlobalVariables.ScoringLevel.Four, new CMD_ArmSetLevelFour(p_shoulder, p_elbow, p_wrist, p_blank));
                  }}
                  ,p_variables::getScoringLevel
          );
          addCommands(deployArm);
          }
     }
     