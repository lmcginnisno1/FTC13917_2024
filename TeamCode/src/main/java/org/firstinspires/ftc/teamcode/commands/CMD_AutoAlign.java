package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_AutoAlign extends SequentialCommandGroup{
     public CMD_AutoAlign(MecanumDriveSubsystem p_drivetrain, SUB_Shoulder p_shoulder,
                   SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank, RobotContainer p_robot,
                   Vector2d p_vector){
          addCommands(
                  new RR_TrajectorySplineFromCurrent(p_robot, p_vector, Math.toRadians(0), true)
          );
     }
}
