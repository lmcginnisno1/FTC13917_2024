package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_AutoDropOff extends SequentialCommandGroup{

     public CMD_AutoDropOff(OpMode p_opMode,MecanumDriveSubsystem p_drivetrain, SUB_Shoulder p_shoulder,
                            SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank){
          addRequirements(p_blank, p_drivetrain);

          Trajectory m_driveToBoard = p_drivetrain.trajectoryBuilder(GlobalVariables.currentPose, true)
                  .lineToConstantHeading(new Vector2d(48, GlobalVariables.currentPose.getY()))
                  .build();

          while(true){
               p_opMode.telemetry.addData("starting pose", GlobalVariables.currentPose);
          }
     }
}
