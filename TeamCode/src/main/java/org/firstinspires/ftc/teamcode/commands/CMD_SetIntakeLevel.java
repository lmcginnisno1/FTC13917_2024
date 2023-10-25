package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.Command;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SelectCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

import java.util.HashMap;

public class CMD_SetIntakeLevel extends SequentialCommandGroup {
     public CMD_SetIntakeLevel(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank,
                              GlobalVariables p_variables){
          SelectCommand setIntakeLevel = new SelectCommand(
                  new HashMap<Object, Command>(){{
                       put(GlobalVariables.IntakeLevel.Two, new CMD_ArmSetReadyIntake(p_shoulder, p_elbow, p_wrist, p_blank));
                       put(GlobalVariables.IntakeLevel.Three, new InstantCommand());
                       put(GlobalVariables.IntakeLevel.Four, new InstantCommand());
                       put(GlobalVariables.IntakeLevel.Five, new CMD_ArmSetReadyIntakeLevelFive(p_shoulder, p_elbow, p_wrist, p_blank));
                  }}
                  ,p_variables::getIntakeLevel
          );
          addCommands(setIntakeLevel);
     }
}
