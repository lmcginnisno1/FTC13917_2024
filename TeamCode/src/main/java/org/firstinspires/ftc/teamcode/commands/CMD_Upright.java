package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_Upright extends SequentialCommandGroup {
     public CMD_Upright(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank){
          addRequirements(p_blank);
          addCommands(
                  new CMD_SetElbowAngle(p_elbow, Constants.ElbowConstants.kParallel),
                  new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kUpright),
                  new CMD_ArmSetLevelHome(p_shoulder, p_elbow, p_wrist, p_blank)
          );
     }
}
