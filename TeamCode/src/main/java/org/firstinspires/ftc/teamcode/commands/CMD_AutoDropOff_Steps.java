package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_VisionAprilTagsPlusAutoDetect;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_AutoDropOff_Steps extends ParallelCommandGroup {

     public CMD_AutoDropOff_Steps(MecanumDriveSubsystem p_drivetrain, SUB_Shoulder p_shoulder,
                                  SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank, GlobalVariables p_variables,
                                  SUB_VisionAprilTagsPlusAutoDetect p_aprilTags, boolean p_leftSlot){
          addCommands(
                     new SequentialCommandGroup(
                         new Sleep(1000)
                         ,new CMD_DeployFirstPixel(p_shoulder, p_elbow, p_wrist, p_blank, p_variables)
                         ,new Sleep(500)
                         ,new CMD_DeploySecondPixel(p_shoulder, p_elbow, p_wrist, p_blank, p_variables)
                     )
                     ,new CMD_AutoDropOff(p_drivetrain, p_aprilTags,this, p_leftSlot)
          );
     }

     public int closestTag = -1;
}
