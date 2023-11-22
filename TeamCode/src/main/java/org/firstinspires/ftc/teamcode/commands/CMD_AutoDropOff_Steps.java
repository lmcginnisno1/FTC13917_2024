package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_VisionAprilTagsPlusAutoDetect;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_AutoDropOff_Steps extends SequentialCommandGroup
{

     public CMD_AutoDropOff_Steps(MecanumDriveSubsystem p_drivetrain, SUB_Shoulder p_shoulder,
                                  SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank, SUB_VisionAprilTagsPlusAutoDetect p_aprilTags){
          addRequirements(p_blank);
          addCommands(
                  new CMD_AutoDropOff(p_drivetrain, p_shoulder,p_elbow, p_wrist, p_blank, p_aprilTags,this)
          );
     }

     public int closestTag = -1;
}
