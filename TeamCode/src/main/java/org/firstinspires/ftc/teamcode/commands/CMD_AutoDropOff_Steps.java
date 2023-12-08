package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_AutoDropOff_Steps extends SequentialCommandGroup {

     public CMD_AutoDropOff_Steps(MecanumDriveSubsystem p_drivetrain, SUB_Shoulder p_shoulder,
                                  SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank,
                                  GlobalVariables p_variables, boolean p_redSide){
          addCommands(
                  //drive to the left or right slot on the closest april tag, does not place any pixels
                  new CMD_AutoDropOff(p_drivetrain, p_variables, p_redSide)
                  //deploy sequence for second pixel, drops and goes home
                  ,new CMD_DeployPixel(p_shoulder, p_elbow, p_wrist, p_blank, p_variables, p_drivetrain)
          );
     }
}
