package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
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
                                  GlobalVariables p_variables, boolean p_leftSlot, GamepadEx p_driverOp){
          addCommands(
                  //drive to the board, does not place any pixels
                  new CMD_AutoDropOffFirstPixel(p_drivetrain)
                  ,new Sleep(250)
                  //deploy sequence for first pixel
                  ,new CMD_DeployFirstPixel(p_shoulder, p_elbow, p_wrist, p_blank, p_variables)
                  ,new Sleep(250)
                  //drive to the left or right slot on the closest april tag, does not place any pixels
                  ,new CMD_AutoDropOffSecondPixel(p_drivetrain, p_leftSlot)
                  ,new Sleep(250)
                  //deploy sequence for second pixel, drops and goes home
                  ,new CMD_DeploySecondPixel(p_shoulder, p_elbow, p_wrist, p_blank, p_variables)
          );
     }
}
