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
import org.firstinspires.ftc.teamcode.commands.RR_TurnCommand;
import org.firstinspires.ftc.teamcode.commands.Sleep;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;

@Autonomous(name = "Auto Red Left Old", group = "Auto Red", preselectTeleOp = "Robot Teleop")
public class AUTO_Red_Left_Old extends Robot_Auto {

     private int m_Analysis;

     public AUTO_Red_Left_Old() {
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

          Trajectory m_purplePixel1 = m_robot.drivetrain.trajectoryBuilder(m_initialPose, true)
                  .lineToLinearHeading(new Pose2d(-40, -6, Math.toRadians(-40)))
                  .build();

          Trajectory m_dropSpot1 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel1.end(), true)
                  .lineToSplineHeading((new Pose2d(-48, 14, Math.toRadians(-90))))
                  .splineToSplineHeading(new Pose2d(-37, 82, Math.toRadians(-90)), Math.toRadians(90))
                  .build();

          Trajectory m_park1 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot1.end(), false)
                  .strafeRight(12)
                  .build();

          Trajectory m_purplePixel2 = m_robot.drivetrain.trajectoryBuilder(m_initialPose, true)
                 .lineToConstantHeading(new Vector2d(-30, 3))
                 .build();

          Trajectory m_preDropSpot2 = m_robot.drivetrain.trajectoryBuilder(new Pose2d(m_purplePixel2.end().getX(), m_purplePixel2.end().getY(), 60), true)
//                  .splineTo(new Vector2d(-36, -3), Math.toRadians(60))
                  .lineToLinearHeading(new Pose2d(-36, -3, Math.toRadians(60)))
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
                            ,new ParallelCommandGroup(
                                    new SequentialCommandGroup(
                                            new Sleep(1500)
                                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot1)
                                    )
                                    ,new SequentialCommandGroup(
                                         new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                         ,new CMD_SetElbowAngle(m_robot.m_elbow, 0)
                                         ,new CMD_SetWristPosition(m_robot.m_wrist, 0.2)
                                         ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 10)
                                    )
                            )
                            ,new SequentialCommandGroup(
                                    new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                    ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 110)
                                    ,new CMD_SetElbowAngle(m_robot.m_elbow, -65)
                                    ,new CMD_SetWristPosition(m_robot.m_wrist, 0.4)
                            )
                            ,new Sleep(100)
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                            ,new Sleep(100)
                            ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_park1)
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
                                    ,new SequentialCommandGroup(
                                        new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel2)
                                        ,new RR_TurnCommand(m_robot.drivetrain, Math.toRadians(60))
                                        ,new InstantCommand(()->m_robot.drivetrain.setPoseEstimate(new Pose2d(m_robot.drivetrain.getPoseEstimate().getX(), m_robot.drivetrain.getPoseEstimate().getY(), 60)))
                                    )
                            )
                            ,new Sleep(500)
                            ,new InstantCommand(()-> m_robot.m_wrist.openClawB())
                            ,new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                            ,new CMD_SetElbowAngle(m_robot.m_elbow, 0)
                            ,new CMD_SetWristPosition(m_robot.m_wrist, 0.2)
                            ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 10)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_preDropSpot2)
                    );
                    break;
          }


          return cmds;
     }
}