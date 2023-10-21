package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robot_Auto;
import org.firstinspires.ftc.teamcode.commands.RR_TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.*;

import org.firstinspires.ftc.teamcode.commands.*;

@Autonomous(name = "Auto Red Left", group = "Auto Red",
        preselectTeleOp = "Robot Teleop")
public class AUTO_Red_Left extends Robot_Auto {

     private int m_Analysis;
     private SequentialCommandGroup placePurplePixel;

     @Override
     public void prebuildTasks() {
          // run these tasks now
          m_robot.m_wrist.closeClawA();
          m_robot.m_wrist.closeClawB();
     }

     @Override
     public SequentialCommandGroup buildTasks() {
          m_Analysis = 2;
          SequentialCommandGroup completetasks = new SequentialCommandGroup();
          completetasks.addCommands(placePurplePixel());

          m_robot.schedule(completetasks);
          return completetasks;
     }

     private SequentialCommandGroup placePurplePixel(){
          SequentialCommandGroup cmds = new SequentialCommandGroup();
          Pose2d m_initialPose = new Pose2d(0, 0, Math.toRadians(0));

          Trajectory m_test = m_robot.drivetrain.trajectoryBuilder(m_initialPose, false)
                  .lineToConstantHeading(new Vector2d(-6, 6))
                  .build();

          cmds.addCommands(
                  new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_test)
                  ,new Sleep(200)
                  ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 90)
                  ,new CMD_SetElbowAngle(m_robot.m_elbow, -90)
                  ,new ParallelCommandGroup(
                          new CMD_SetElbowAngle(m_robot.m_elbow, -180)
                          ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 180)
                  )
                  ,new CMD_SetWristPosition(m_robot.m_wrist, .75)
                  ,new Sleep(1500)
                  ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                  ,new Sleep(500)
                  ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
          );

          return cmds;
     }

}

