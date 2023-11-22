package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_VisionAprilTagsPlusAutoDetect;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

public class CMD_AutoDropOff extends CommandBase{

     SUB_Shoulder m_shoulder;
     SUB_Elbow m_elbow;
     SUB_Wrist m_wrist;
     SUB_Blank m_blank;
     SUB_VisionAprilTagsPlusAutoDetect m_aprilTags;
     MecanumDriveSubsystem m_drivetrain;

     CMD_AutoDropOff_Steps m_ref = null;

     List<AprilTagDetection> m_detections;

     public CMD_AutoDropOff(MecanumDriveSubsystem p_drivetrain, SUB_Shoulder p_shoulder,
                            SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank, SUB_VisionAprilTagsPlusAutoDetect p_aprilTags,
                            CMD_AutoDropOff_Steps p_ref){
          addRequirements(p_blank, p_drivetrain);

          m_drivetrain = p_drivetrain;
          m_shoulder = p_shoulder;
          m_elbow = p_elbow;
          m_wrist = p_wrist;
          m_blank = p_blank;
          m_aprilTags = p_aprilTags;
          m_ref = p_ref;
     }

     @Override
     public void initialize(){
          Pose2d m_robotPose = m_drivetrain.getPoseEstimate();
          m_ref.closestTag = m_aprilTags.findClosestTag(m_robotPose);
          int closestTagID = m_aprilTags.findClosestTag(m_robotPose);
          double wantedY = 0;

          if(closestTagID == 1){
               wantedY = 41.75;
          }else if(closestTagID == 2){
               wantedY = 35.75;
          }else if(closestTagID == 3){
               wantedY = 29.5;
          }else if(closestTagID == 4){
               wantedY = -29.5;
          }else if(closestTagID == 5){
               wantedY = -35.75;
          }else if(closestTagID == 6){
               wantedY = -41.75;
          }

          Trajectory m_getToBoard = m_drivetrain.trajectoryBuilder(m_robotPose, true)
                  .splineTo(new Vector2d(58, wantedY), Math.toRadians(0))
                  .build();

          m_drivetrain.followTrajectory(m_getToBoard);
     }

     @Override
     public void execute(){
          m_drivetrain.update();
     }

     @Override
     public boolean isFinished() {
          return Thread.currentThread().isInterrupted() || !m_drivetrain.isBusy();
     }
}
