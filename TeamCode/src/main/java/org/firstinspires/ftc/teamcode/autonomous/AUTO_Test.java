package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot_Auto;
import org.firstinspires.ftc.teamcode.commands.CMD_ArmDropIntakeLevelFive;
import org.firstinspires.ftc.teamcode.commands.CMD_ArmDropIntakeLevelFour;
import org.firstinspires.ftc.teamcode.commands.CMD_ArmSetLevelHome;
import org.firstinspires.ftc.teamcode.commands.CMD_ArmSetLevelOne;
import org.firstinspires.ftc.teamcode.commands.CMD_ArmSetReadyIntakeLevelFive;
import org.firstinspires.ftc.teamcode.commands.CMD_ArmSetReadyIntakeLevelFour;
import org.firstinspires.ftc.teamcode.commands.CMD_SetElbowAngle;
import org.firstinspires.ftc.teamcode.commands.CMD_SetShoulderAngle;
import org.firstinspires.ftc.teamcode.commands.CMD_SetWristPosition;
import org.firstinspires.ftc.teamcode.commands.CMD_WristReleaseClaw;
import org.firstinspires.ftc.teamcode.commands.CMD_WristReleaseOutsideClaw;
import org.firstinspires.ftc.teamcode.commands.RR_TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.commands.RR_TurnCommand;
import org.firstinspires.ftc.teamcode.commands.Sleep;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.opencv.core.Mat;

@Autonomous(name = "Auto Test", group = "Auto Test", preselectTeleOp = "Robot Teleop")
public class AUTO_Test extends Robot_Auto {

     private int m_Analysis;

     public AUTO_Test() {
          super(true);
     }

     @Override
     public void prebuildTasks() {
          // run these tasks now
          m_robot.m_wrist.closeClawA();
          m_robot.m_wrist.closeClawB();
          m_robot.m_wrist.setPosition(0);
     }

     @Override
     public SequentialCommandGroup buildTasks(int p_Analysis) {
          m_Analysis = p_Analysis;

          SequentialCommandGroup completetasks = new SequentialCommandGroup();
          completetasks.addCommands(
                  placePurplePixel()
          );

          m_robot.schedule(completetasks);
          return completetasks;
     }


     private SequentialCommandGroup placePurplePixel(){
          SequentialCommandGroup cmds = new SequentialCommandGroup();
          Pose2d m_initialPose = new Pose2d(0, 0, Math.toRadians(0));

          Trajectory m_purplePixel1 = m_robot.drivetrain.trajectoryBuilder(m_initialPose, false)
                  .lineToLinearHeading(new Pose2d(-38, -13, Math.toRadians(15)))
                  .build();
          Trajectory m_alignToWhiteStack = m_robot.drivetrain.trajectoryBuilder(m_purplePixel1.end(), false)
                  .lineToLinearHeading(new Pose2d(-50, -13, Math.toRadians(-90)))
                  .build();

          cmds.addCommands(
               new ParallelCommandGroup(
                  new ParallelCommandGroup(
                          new CMD_SetShoulderAngle(m_robot.m_shoulder, 45)
                          ,new SequentialCommandGroup(
                               new Sleep(500)
                               ,new CMD_SetElbowAngle(m_robot.m_elbow, 60)
                               ,new CMD_SetWristPosition(m_robot.m_wrist, .5)
                          )
                  )
                  ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel1)
               )
               ,new Sleep(1000)
               ,new InstantCommand(()-> m_robot.m_wrist.openClawB())
               ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_alignToWhiteStack)
//                  new ParallelCommandGroup(
//                     new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_prePurple1)
//                     ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 45)
//                     ,new SequentialCommandGroup(
//                         new Sleep(1500)
//                         ,new ParallelCommandGroup(
//                              new CMD_SetElbowAngle(m_robot.m_elbow, 60)
//                              ,new SequentialCommandGroup(
//                                   new Sleep(500)
//                                   ,new CMD_SetWristPosition(m_robot.m_wrist, .5)
//                              )
//                         )
//                     )
//                  )
//                  ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel1)
//                  ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
          );

          return cmds;
     }
}