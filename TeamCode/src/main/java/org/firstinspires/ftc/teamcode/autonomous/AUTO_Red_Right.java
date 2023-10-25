package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robot_Auto;
import org.firstinspires.ftc.teamcode.commands.RR_TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.*;

import org.firstinspires.ftc.teamcode.commands.*;

@Autonomous(name = "Auto Red Right", group = "Auto Red",
        preselectTeleOp = "Robot Teleop")
public class AUTO_Red_Right extends Robot_Auto {
     public AUTO_Red_Right() {
          super(true);
     }

     private int m_Analysis;
     @Override
     public void prebuildTasks() {
          // run these tasks now
          m_robot.m_wrist.closeClawA();
          m_robot.m_wrist.closeClawB();
     }

     @Override
     public SequentialCommandGroup buildTasks(int p_Analysis) {
          m_Analysis = p_Analysis;
          SequentialCommandGroup completetasks = new SequentialCommandGroup();
          completetasks.addCommands(
                  placePurplePixel(),
                  placeYellowPixel()
          );

          m_robot.schedule(completetasks);
          return completetasks;
     }

     private SequentialCommandGroup placePurplePixel(){
          SequentialCommandGroup cmds = new SequentialCommandGroup();
          Pose2d m_initialPose = new Pose2d(0, 0, Math.toRadians(0));

          Trajectory m_spikeMark1 = m_robot.drivetrain.trajectoryBuilder(m_initialPose, false)
                  .lineToLinearHeading(new Pose2d(-4, 0, Math.toRadians(28)))
                  .build();

          Trajectory m_spikeMark2 = m_robot.drivetrain.trajectoryBuilder(m_initialPose, false)
                  .lineToConstantHeading(new Vector2d(-8, 6))
                  .build();

          Trajectory m_spikeMark3 = m_robot.drivetrain.trajectoryBuilder(m_initialPose, false)
                  .lineToLinearHeading(new Pose2d(-4, 0, Math.toRadians(-28)))
                  .build();


          switch (m_Analysis){
               case 1:
                    cmds.addCommands(
                            new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_spikeMark1)
                            ,new Sleep(200)
                            ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 90)
                            ,new CMD_SetElbowAngle(m_robot.m_elbow, -70)//-100
                            ,new ParallelCommandGroup(
                                    new CMD_SetElbowAngle(m_robot.m_elbow, -140)
                                    ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 170)
                                    ,new CMD_SetWristPosition(m_robot.m_wrist, .75)
                            )
                            ,new Sleep(500)
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                            ,new Sleep(500)
                            ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                    );
                    break;
               case 2:
                    cmds.addCommands(
                            new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_spikeMark2)
                            ,new Sleep(200)
                            ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 90)
                            ,new CMD_SetElbowAngle(m_robot.m_elbow, -90)
                            ,new ParallelCommandGroup(
                                    new CMD_SetElbowAngle(m_robot.m_elbow, -180)
                                    ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 180)
                                    ,new CMD_SetWristPosition(m_robot.m_wrist, .75)
                            )
                            ,new Sleep(500)
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                            ,new Sleep(500)
                            ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                    );
                    break;
               case 3:
                    cmds.addCommands(
                            new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_spikeMark3)
                            ,new Sleep(200)
                            ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 90)
                            ,new CMD_SetElbowAngle(m_robot.m_elbow, -70)//-100
                            ,new ParallelCommandGroup(
                                    new CMD_SetElbowAngle(m_robot.m_elbow, -140)
                                    ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 170)
                                    ,new CMD_SetWristPosition(m_robot.m_wrist, .75)
                            )
                            ,new Sleep(500)
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                            ,new Sleep(500)
                            ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                    );
                    break;
          }

          return cmds;
     }

     private SequentialCommandGroup placeYellowPixel(){
          SequentialCommandGroup cmds = new SequentialCommandGroup();
          Trajectory dropSpot1 = m_robot.drivetrain.trajectoryBuilder(new Pose2d(-4, 0, Math.toRadians(0)), true)
                  .lineToLinearHeading(new Pose2d(-33.5, 38.75, Math.toRadians(-90)))
                  .build();

          Trajectory dropSpot2 = m_robot.drivetrain.trajectoryBuilder(new Pose2d(-8, -6, Math.toRadians(0)), true)
                  .lineToLinearHeading(new Pose2d(-25, 38.25, Math.toRadians(-90)))
                  .build();

          Trajectory dropSpot3 = m_robot.drivetrain.trajectoryBuilder(new Pose2d(-4, 0, Math.toRadians(0)), true)
                  .lineToLinearHeading(new Pose2d(-19.5, 38.75, Math.toRadians(-90)))
                  .build();

          Trajectory park = m_robot.drivetrain.trajectoryBuilder(dropSpot2.end(), false)
                .strafeLeft(24)
                .build();

          switch (m_Analysis){
               case 1:
                    cmds.addCommands(
                            new RR_TrajectoryFollowerCommand(m_robot.drivetrain, dropSpot1),
                            new CMD_ArmSetLevelOne(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist,m_robot.m_blank),
                            new Sleep(500),
                            new CMD_WristReleaseClaw(m_robot.m_wrist),
                            new Sleep(500),
                            new ParallelCommandGroup(
                                    new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank),
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, park)
                            )
                    );
                    break;
               case 2:
                    cmds.addCommands(
                            new RR_TrajectoryFollowerCommand(m_robot.drivetrain, dropSpot2),
                            new CMD_ArmSetLevelOne(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist,m_robot.m_blank),
                            new Sleep(500),
                            new CMD_WristReleaseClaw(m_robot.m_wrist),
                            new Sleep(500),
                            new ParallelCommandGroup(
                                    new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank),
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, park)
                            )
                    );
                    break;
               case 3:
                    cmds.addCommands(
                            new RR_TrajectoryFollowerCommand(m_robot.drivetrain, dropSpot3),
                            new CMD_ArmSetLevelOne(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist,m_robot.m_blank),
                            new Sleep(500),
                            new CMD_WristReleaseClaw(m_robot.m_wrist),
                            new Sleep(500),
                            new ParallelCommandGroup(
                                    new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank),
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, park)
                            )
                    );
                    break;
          }




          return cmds;
     }

}

