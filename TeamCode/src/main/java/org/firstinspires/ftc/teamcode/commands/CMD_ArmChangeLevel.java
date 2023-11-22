package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_ArmChangeLevel extends SequentialCommandGroup {
     public CMD_ArmChangeLevel(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist,
                               SUB_Blank p_blank, GlobalVariables p_variables){

          addRequirements(p_blank);
          addCommands(
             new CMD_ShoulderSetReadyToDeploy(p_shoulder, p_variables).noWait()
             ,new CMD_ElbowSetReadyToDeploy(p_elbow, p_variables).noWait()
             ,new CMD_WristSetReadyToDeploy(p_wrist, p_variables)
          );
     }
}
