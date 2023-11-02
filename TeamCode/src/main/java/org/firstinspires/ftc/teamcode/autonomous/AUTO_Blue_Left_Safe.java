package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robot_Auto;
import org.firstinspires.ftc.teamcode.commands.CMD_ArmSetLevelHome;
import org.firstinspires.ftc.teamcode.commands.CMD_SetElbowAngle;
import org.firstinspires.ftc.teamcode.commands.CMD_SetShoulderAngle;
import org.firstinspires.ftc.teamcode.commands.CMD_SetWristPosition;
import org.firstinspires.ftc.teamcode.commands.CMD_WristReleaseClaw;
import org.firstinspires.ftc.teamcode.commands.CMD_WristReleaseOutsideClaw;
import org.firstinspires.ftc.teamcode.commands.RR_TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.commands.Sleep;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;

@Autonomous(name = "Auto Blue Left Safe", group = "Auto Blue Left", preselectTeleOp = "Robot Teleop")
public class AUTO_Blue_Left_Safe extends Robot_Auto {
     int m_Analysis;
     public AUTO_Blue_Left_Safe() {
          super(false);
     }

     @Override
     public void prebuildTasks() {
          //run these tasks now
          m_robot.m_wrist.closeClawA();
          m_robot.m_wrist.closeClawB();
          m_robot.m_wrist.setPosition(0);
          setStartingPose(new Pose2d(12, 61, Math.toRadians(90)));
     }

     @Override
     public SequentialCommandGroup buildTasks(int p_Analysis) {
          m_Analysis = p_Analysis;
          SequentialCommandGroup completeTasks = new SequentialCommandGroup();
          completeTasks.addCommands(
               placePurplePixel()
          );
          m_robot.schedule(completeTasks);
          return completeTasks;
     }

     private SequentialCommandGroup placePurplePixel(){
          SequentialCommandGroup cmds = new SequentialCommandGroup();

          Trajectory m_purplePixel1 = m_robot.drivetrain.trajectoryBuilder(getStartingPose(), true)
                  .splineTo(new Vector2d(32,32), Math.toRadians(0))
                  .build();

          Trajectory m_dropSpot1 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel1.end(), true)
                  .splineTo(new Vector2d(51, 42.5), Math.toRadians(0))
                  .build();

          Trajectory m_purplePixel2 = m_robot.drivetrain.trajectoryBuilder(getStartingPose(), true)
                  .splineTo(new Vector2d(24,25), Math.toRadians(0))
                  .build();

          Trajectory m_dropSpot2 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel2.end(), true)
                  .splineTo(new Vector2d(50.5, 36.5), Math.toRadians(0))
                  .build();

          Trajectory m_prePurplePixel3 = m_robot.drivetrain.trajectoryBuilder(getStartingPose(), true)
                  .lineTo(new Vector2d(12, 40))
                  .build();

          Trajectory m_purplePixel3 = m_robot.drivetrain.trajectoryBuilder(m_prePurplePixel3.end(), true)
                  .lineToLinearHeading(new Pose2d(8, 30, Math.toRadians(140)))
                  .build();

          Trajectory m_dropSpot3 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel3.end(), true)
                  .splineTo(new Vector2d(50.5, 30.5), Math.toRadians(0))
                  .build();

          switch (m_Analysis){
               case 1:
                    cmds.addCommands(
                            new ParallelCommandGroup(
                                    new ParallelCommandGroup(
                                            new CMD_SetShoulderAngle(m_robot.m_shoulder, 35)
                                            ,new SequentialCommandGroup(
                                                 new Sleep(500)
                                                 ,new CMD_SetElbowAngle(m_robot.m_elbow, 65)
                                                 ,new CMD_SetWristPosition(m_robot.m_wrist, .5)
                                            )
                                    )
                                    ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel1)
                            )
                            ,new Sleep(1000)
                            ,new InstantCommand(()-> m_robot.m_wrist.openClawB())
                            ,new Sleep(500)
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot1)
                                    ,new SequentialCommandGroup(
                                         new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                         ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 110).setTolerance(45)
                                         ,new CMD_SetElbowAngle(m_robot.m_elbow, -40)
                                         ,new CMD_SetWristPosition(m_robot.m_wrist, 0.3)
                                    )
                            )
                            ,new Sleep(100)
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                    );
                    break;
               case 2:
                  cmds.addCommands(
                          new ParallelCommandGroup(
                                  new ParallelCommandGroup(
                                          new CMD_SetShoulderAngle(m_robot.m_shoulder, 35)
                                          ,new SequentialCommandGroup(
                                          new Sleep(500)
                                          ,new CMD_SetElbowAngle(m_robot.m_elbow, 65)
                                          ,new CMD_SetWristPosition(m_robot.m_wrist, .5)
                                  )
                                  )
                                  ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel2)
                          )
                          ,new Sleep(1000)
                          ,new InstantCommand(()-> m_robot.m_wrist.openClawB())
                          ,new Sleep(500)
                          ,new ParallelCommandGroup(
                                  new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot2)
                                  ,new SequentialCommandGroup(
                                       new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                       ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 110).setTolerance(45)
                                       ,new CMD_SetElbowAngle(m_robot.m_elbow, -40)
                                       ,new CMD_SetWristPosition(m_robot.m_wrist, 0.3)
                                  )
                          )
                          ,new Sleep(100)
                          ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                          ,new Sleep(100)
                          ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                          ,new Sleep(100)
                          ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                    );
                    break;
               case 3:
                    cmds.addCommands(
                            new ParallelCommandGroup(
                                    new ParallelCommandGroup(
                                            new CMD_SetShoulderAngle(m_robot.m_shoulder, 35)
                                            ,new SequentialCommandGroup(
                                            new Sleep(500)
                                            ,new CMD_SetElbowAngle(m_robot.m_elbow, 65)
                                            ,new CMD_SetWristPosition(m_robot.m_wrist, .5)
                                    )
                                    )
                                    ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_prePurplePixel3)
                            )
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel3)
                            ,new InstantCommand(()-> m_robot.m_wrist.openClawB())
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot3)
                                    ,new SequentialCommandGroup(
                                         new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                         ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 110).setTolerance(45)
                                         ,new CMD_SetElbowAngle(m_robot.m_elbow, -40)
                                         ,new CMD_SetWristPosition(m_robot.m_wrist, 0.3)
                                    )
                            )
                            ,new Sleep(100)
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                    );
                    break;
          }

          return cmds;
     }

}
