package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robot_Auto;
import org.firstinspires.ftc.teamcode.commands.CMD_ArmDropIntakeLevelFive;
import org.firstinspires.ftc.teamcode.commands.CMD_ArmSetLevelHome;
import org.firstinspires.ftc.teamcode.commands.CMD_ArmSetReadyIntakeLevelFive;
import org.firstinspires.ftc.teamcode.commands.CMD_SetElbowAngle;
import org.firstinspires.ftc.teamcode.commands.CMD_SetShoulderAngle;
import org.firstinspires.ftc.teamcode.commands.CMD_SetWristPosition;
import org.firstinspires.ftc.teamcode.commands.CMD_WristReleaseClaw;
import org.firstinspires.ftc.teamcode.commands.CMD_WristReleaseOutsideClaw;
import org.firstinspires.ftc.teamcode.commands.RR_TrajectoryLineToConstantHeadingFromCurrent;
import org.firstinspires.ftc.teamcode.commands.RR_TrajectorySplineFromCurrent;
import org.firstinspires.ftc.teamcode.commands.RR_TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.commands.Sleep;
import org.firstinspires.ftc.teamcode.commands.VisionUpdatePose;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;

@Autonomous(name = "Auto Red Right Safe", group = "Auto Red Right", preselectTeleOp = "Robot Teleop")
public class AUTO_Red_Right_Safe extends Robot_Auto {
     int m_Analysis;
     public AUTO_Red_Right_Safe() {
          super(true);
     }

     @Override
     public void prebuildTasks() {
          //run these tasks now
          m_robot.m_wrist.closeClawA();
          m_robot.m_wrist.closeClawB();
          m_robot.m_wrist.setPosition(0);
          setStartingPose(new Pose2d(12, -61, Math.toRadians(-90)));
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

          Trajectory m_prePurplePixel1 = m_robot.drivetrain.trajectoryBuilder(getStartingPose(), true)
                  .lineTo(new Vector2d(12, -40))
                  .build();

          Trajectory m_purplePixel1 = m_robot.drivetrain.trajectoryBuilder(m_prePurplePixel1.end(), true)
                  .lineToLinearHeading(new Pose2d(8, -30, Math.toRadians(-140)))
                  .build();

          Trajectory m_dropSpot1 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel1.end(), true)
                  .lineToLinearHeading(new Pose2d(43, -29.5, Math.toRadians(180)))
                  .build();

          Trajectory m_park1 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot1.end(), true)
                  .lineToConstantHeading(new Vector2d(50, -60))
                  .build();

          Trajectory m_purplePixel2 = m_robot.drivetrain.trajectoryBuilder(getStartingPose(), true)
                  .splineTo(new Vector2d(24,-25), Math.toRadians(0))
                  .build();

          Trajectory m_dropSpot2 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel2.end(), true)
                  .lineToConstantHeading(new Vector2d(43, -37.5))
                  .build();

          Trajectory m_park2 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot2.end(), true)
                  .lineToConstantHeading(new Vector2d(50, -60))
                  .build();

          Trajectory m_purplePixel3 = m_robot.drivetrain.trajectoryBuilder(getStartingPose(), true)
                  .splineTo(new Vector2d(33,-35), Math.toRadians(0))
                  .build();

          Trajectory m_dropSpot3 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel3.end(), true)
                  .lineToConstantHeading(new Vector2d(43, -40))
                  .build();

          Trajectory m_park3 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot3.end(), true)
                  .lineToConstantHeading(new Vector2d(50, -60))
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
                                    ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_prePurplePixel1)
                            )
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel1)
                            ,new InstantCommand(()-> m_robot.m_wrist.openClawB())
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot1)
                                    ,new SequentialCommandGroup(
                                         new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                              ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 110).setTolerance(90)
                                              ,new CMD_SetElbowAngle(m_robot.m_elbow, -40)
                                              ,new CMD_SetWristPosition(m_robot.m_wrist, 0.3)
                                    )
                            )
                            ,new VisionUpdatePose(m_robot.m_backCamera, m_robot.drivetrain)
                            ,new RR_TrajectoryLineToConstantHeadingFromCurrent(m_robot, new Vector2d(52, -31), true)
                            ,new Sleep(100)
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new ParallelCommandGroup(
                                    new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                                    ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_park1)
                            )
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
                            ,new InstantCommand(()-> m_robot.m_wrist.openClawB())
                            ,new Sleep(500)
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot2)
                                    ,new SequentialCommandGroup(
                                         new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                         ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 110).setTolerance(90)
                                         ,new CMD_SetElbowAngle(m_robot.m_elbow, -40)
                                         ,new CMD_SetWristPosition(m_robot.m_wrist, 0.3)
                                    )
                            )
                            ,new VisionUpdatePose(m_robot.m_backCamera, m_robot.drivetrain)
                            ,new RR_TrajectoryLineToConstantHeadingFromCurrent(m_robot, new Vector2d(51, -36), true)
                            ,new Sleep(100)
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new ParallelCommandGroup(
                                    new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                                    ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_park2)
                            )
                    );
                    break;
               case 3:
                    cmds.addCommands(
                            new ParallelCommandGroup(
                                    new ParallelCommandGroup(
                                            new CMD_SetShoulderAngle(m_robot.m_shoulder, 40)
                                            ,new SequentialCommandGroup(
                                                 new Sleep(500)
                                                 ,new CMD_SetElbowAngle(m_robot.m_elbow, 70)
                                                 ,new CMD_SetWristPosition(m_robot.m_wrist, .5)
                                            )
                                    )
                                    ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel3)
                            )
                            ,new InstantCommand(()-> m_robot.m_wrist.openClawB())
                            ,new Sleep(500)
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot3)
                                    ,new SequentialCommandGroup(
                                         new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                         ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 110).setTolerance(90)
                                         ,new CMD_SetElbowAngle(m_robot.m_elbow, -40)
                                         ,new CMD_SetWristPosition(m_robot.m_wrist, 0.3)
                                    )
                            )
                            ,new VisionUpdatePose(m_robot.m_backCamera, m_robot.drivetrain)
                            ,new RR_TrajectoryLineToConstantHeadingFromCurrent(m_robot, new Vector2d(52, -44), true)
                            ,new Sleep(100)
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new ParallelCommandGroup(
                              new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                              ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_park3)
                            )
                    );
                    break;
          }

          return cmds;
     }

}
