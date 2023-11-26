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
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

public class CMD_AutoDropOff extends CommandBase{

     SUB_VisionAprilTagsPlusAutoDetect m_aprilTags;
     MecanumDriveSubsystem m_drivetrain;

     CMD_AutoDropOff_Steps m_ref = null;

     List<AprilTagDetection> m_detections;

     double m_leftSlotOffset = 1.5;
     double m_rightSlotOffset = -1.5;
     boolean m_leftSlot;

     public CMD_AutoDropOff(MecanumDriveSubsystem p_drivetrain, SUB_VisionAprilTagsPlusAutoDetect p_aprilTags,
                            CMD_AutoDropOff_Steps p_ref, boolean p_leftSlot){
          addRequirements(p_drivetrain);

          m_drivetrain = p_drivetrain;
          m_aprilTags = p_aprilTags;
          m_ref = p_ref;
          m_leftSlot = p_leftSlot;
     }

     @Override
     public void initialize(){
          Pose2d m_robotPose = m_drivetrain.getPoseEstimate();
          m_ref.closestTag = m_aprilTags.findClosestTag();
          int closestTagID = m_aprilTags.findClosestTag();
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

          double offset = m_leftSlot ? m_leftSlotOffset : m_rightSlotOffset;

          wantedY += offset;

          Trajectory m_dropOffFirstPixel = m_drivetrain.trajectoryBuilder(m_robotPose, true)
                  .lineToLinearHeading(new Pose2d(48, m_drivetrain.getPoseEstimate().getY(), Math.toRadians(180)))
                  .build();

          Trajectory m_dropOffSecondPixel = m_drivetrain.trajectoryBuilder(m_dropOffFirstPixel.end(), true)
                  .lineToConstantHeading(new Vector2d(48, wantedY))
                  .build();

          TrajectorySequence m_trajectorySequence = m_drivetrain.trajectorySequenceBuilder(m_dropOffFirstPixel.start())
                  .addTrajectory(m_dropOffFirstPixel)
                  .waitSeconds(1)
                  .addTrajectory(m_dropOffSecondPixel)
                  .build();

          m_drivetrain.followTrajectorySequence(m_trajectorySequence);
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
