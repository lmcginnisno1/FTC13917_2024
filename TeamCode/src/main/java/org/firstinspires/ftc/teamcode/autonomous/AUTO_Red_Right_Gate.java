package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Robot_Auto;
import org.firstinspires.ftc.teamcode.commands.*;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;

@Disabled
@Autonomous(name = "Auto Red Right Gate", group = "Auto Red Right", preselectTeleOp = "Robot Teleop")
public class AUTO_Red_Right_Gate extends Robot_Auto {
     int m_Analysis;
     public AUTO_Red_Right_Gate() {
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
                  .splineTo(new Vector2d(50.5, -29.5), Math.toRadians(0))
                  .build();

          Trajectory m_preGetMorePixels1 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot1.end(), false)
                  .splineTo(new Vector2d(0, -12), Math.toRadians(180))
                  .build();

          Trajectory m_getMorePixels1 = m_robot.drivetrain.trajectoryBuilder(m_preGetMorePixels1.end(), false)
                  .splineTo(new Vector2d(-54, -12), Math.toRadians(180))
                  .build();

          Trajectory m_driveIntoPixels1 = m_robot.drivetrain.trajectoryBuilder(m_getMorePixels1.end(), false)
                  .forward(9)
                  .build();

          Trajectory m_preBackToDrop1 = m_robot.drivetrain.trajectoryBuilder(m_driveIntoPixels1.end(), true)
                  .splineTo(new Vector2d(0, -12), Math.toRadians(0))
                  .build();

          Trajectory m_backToDrop1 = m_robot.drivetrain.trajectoryBuilder(m_preBackToDrop1.end(), true)
                  .splineTo(new Vector2d(50, -33.5), Math.toRadians(0))
                  .build();

          Trajectory m_purplePixel2 = m_robot.drivetrain.trajectoryBuilder(getStartingPose(), true)
                  .splineTo(new Vector2d(20,-25), Math.toRadians(0))
                  .build();

          Trajectory m_dropSpot2 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel2.end(), true)
                  .splineTo(new Vector2d(52, -36), Math.toRadians(0))
                  .build();

          Trajectory m_preGetMorePixels2 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot2.end(), false)
                  .splineTo(new Vector2d(12, -12), Math.toRadians(180))
                  .forward(18)
                  .build();

          Trajectory m_getMorePixels2 = m_robot.drivetrain.trajectoryBuilder(m_preGetMorePixels2.end(), false)
                  .splineTo(new Vector2d(-60, -12), Math.toRadians(180))
                  .build();

          Trajectory m_driveIntoPixels2 = m_robot.drivetrain.trajectoryBuilder(m_getMorePixels2.end(), false)
                  .forward(3)
                  .build();

          Trajectory m_preBackToDrop2 = m_robot.drivetrain.trajectoryBuilder(m_driveIntoPixels2.end(), true)
                  .splineTo(new Vector2d(0, -12), Math.toRadians(0))
                  .build();

          Trajectory m_backToDrop2 = m_robot.drivetrain.trajectoryBuilder(m_preBackToDrop2.end(), true)
                  .splineTo(new Vector2d(48.5, -28), Math.toRadians(0))
                  .build();

          Trajectory m_purplePixel3 = m_robot.drivetrain.trajectoryBuilder(getStartingPose(), true)
                  .splineTo(new Vector2d(32,-32), Math.toRadians(0))
                  .build();

          Trajectory m_dropSpot3 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel3.end(), true)
                  .splineTo(new Vector2d(51.5, -40.5), Math.toRadians(0))
                  .build();

          Trajectory m_preGetMorePixels3 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot3.end(), false)
                  .splineTo(new Vector2d(12, -12), Math.toRadians(180))
                  .forward(18)
                  .build();

          Trajectory m_getMorePixels3 = m_robot.drivetrain.trajectoryBuilder(m_preGetMorePixels3.end(), false)
                  .splineTo(new Vector2d(-60, -12), Math.toRadians(180))
                  .build();

          Trajectory m_driveIntoPixels3 = m_robot.drivetrain.trajectoryBuilder(m_getMorePixels3.end(), false)
                  .forward(3)
                  .build();

          Trajectory m_preBackToDrop3 = m_robot.drivetrain.trajectoryBuilder(m_driveIntoPixels3.end(), true)
                  .splineTo(new Vector2d(0, -12), Math.toRadians(0))
                  .build();

          Trajectory m_backToDrop3 = m_robot.drivetrain.trajectoryBuilder(m_preBackToDrop3.end(), true)
                  .splineTo(new Vector2d(48, -27.5), Math.toRadians(0))
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
                            ,new Sleep(100)
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new ParallelCommandGroup(
                              new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                              ,new SequentialCommandGroup(
                                    new Sleep(1250)
                                    ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_preGetMorePixels1)
                              )
                            )
                            ,new ParallelCommandGroup(
                               new CMD_ArmSetReadyIntakeLevelFive(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                               ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_getMorePixels1)
                            )
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_driveIntoPixels1)
                            ,new CMD_ArmDropIntakeLevelFive(m_robot.m_shoulder,m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                            ,new ParallelCommandGroup(
                              new SequentialCommandGroup(
                                   new Sleep(2000)
                                   ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_preBackToDrop1)
                              )
                              ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                            )
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_backToDrop1)
                                    ,new SequentialCommandGroup(
                                         new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                         ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 110).setTolerance(45)
                                         ,new CMD_SetElbowAngle(m_robot.m_elbow, -45)
                                         ,new CMD_SetWristPosition(m_robot.m_wrist, 0.3)
                                    )
                            )
                            ,new Sleep(100)
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                            ,new Sleep(250)
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
                                                 new Sleep(250)
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
                                         ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 120).setTolerance(45)
                                         ,new CMD_SetElbowAngle(m_robot.m_elbow, -30)
                                         ,new CMD_SetWristPosition(m_robot.m_wrist, 0.25)
                                    )
                            )
                            ,new Sleep(100)
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new ParallelCommandGroup(
                                    new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                                    ,new SequentialCommandGroup(
                                         new Sleep(1250)
                                         ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_preGetMorePixels2)
                                    )
                            )
                            ,new ParallelCommandGroup(
                                    new CMD_ArmSetReadyIntakeLevelFive(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                                    ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_getMorePixels2)
                            )
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_driveIntoPixels2)
                            ,new CMD_ArmDropIntakeLevelFive(m_robot.m_shoulder,m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                            ,new ParallelCommandGroup(
                                    new SequentialCommandGroup(
                                            new Sleep(2000)
                                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_preBackToDrop2)
                                    )
                                    ,new SequentialCommandGroup(
                                        new Sleep(250)
                                        ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                                    )
                            )
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_backToDrop2)
                                    ,new SequentialCommandGroup(
                                         new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                         ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 115).setTolerance(45)
                                         ,new CMD_SetElbowAngle(m_robot.m_elbow, -50)
                                         ,new CMD_SetWristPosition(m_robot.m_wrist, 0.3)
                                    )
                            )
                            ,new Sleep(100)
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                            ,new Sleep(500)
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
                                            new Sleep(250)
                                            ,new CMD_SetElbowAngle(m_robot.m_elbow, 65)
                                            ,new CMD_SetWristPosition(m_robot.m_wrist, .5)
                                    )
                                    )
                                    ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel3)
                            )
                            ,new Sleep(1000)
                            ,new InstantCommand(()-> m_robot.m_wrist.openClawB())
                            ,new Sleep(500)
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot3)
                                    ,new SequentialCommandGroup(
                                    new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                    ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 120).setTolerance(45)
                                    ,new CMD_SetElbowAngle(m_robot.m_elbow, -30)
                                    ,new CMD_SetWristPosition(m_robot.m_wrist, 0.25)
                            )
                            )
                            ,new Sleep(100)
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new ParallelCommandGroup(
                                    new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                                    ,new SequentialCommandGroup(
                                    new Sleep(1250)
                                    ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_preGetMorePixels3)
                            )
                            )
                            ,new ParallelCommandGroup(
                                    new CMD_ArmSetReadyIntakeLevelFive(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                                    ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_getMorePixels3)
                            )
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_driveIntoPixels3)
                            ,new CMD_ArmDropIntakeLevelFive(m_robot.m_shoulder,m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                            ,new ParallelCommandGroup(
                                    new SequentialCommandGroup(
                                            new Sleep(2000)
                                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_preBackToDrop3)
                                    )
                                    ,new SequentialCommandGroup(
                                    new Sleep(250)
                                    ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                            )
                            )
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_backToDrop3)
                                    ,new SequentialCommandGroup(
                                    new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                    ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 115).setTolerance(45)
                                    ,new CMD_SetElbowAngle(m_robot.m_elbow, -50)
                                    ,new CMD_SetWristPosition(m_robot.m_wrist, 0.3)
                            )
                            )
                            ,new Sleep(100)
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                            ,new Sleep(500)
                            ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                    );
                    break;
          }

          return cmds;
     }

}
