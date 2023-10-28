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
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.opencv.core.Mat;

@Autonomous(name = "Auto Red Left Test", group = "Auto Red Test", preselectTeleOp = "Robot Teleop")
public class AUTO_Red_Left_Test extends Robot_Auto {

     private int m_Analysis;

     public AUTO_Red_Left_Test() {
          super(true);
     }

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
                  placePurplePixel()
//                  ,placeYellowPixel()
          );

          m_robot.schedule(completetasks);
          return completetasks;
     }


     private SequentialCommandGroup placePurplePixel(){
          SequentialCommandGroup cmds = new SequentialCommandGroup();
          Pose2d m_initialPose = new Pose2d(0, 0, Math.toRadians(0));

          Trajectory m_prePurple1 = m_robot.drivetrain.trajectoryBuilder(m_initialPose, false)
                  .back(48)
                  .build();

          Trajectory m_purplePixel1 = m_robot.drivetrain.trajectoryBuilder(new Pose2d(-48, 0, -30), false)
                  .lineToLinearHeading(new Pose2d(-42, -5, Math.toRadians(-30)))
                  .build();

          Trajectory m_alignToWhitePixel1 = m_robot.drivetrain.trajectoryBuilder(new Pose2d(-40, 0, -30), false)
                  .lineToLinearHeading(new Pose2d(-48, 0, Math.toRadians(-90)))
                  .build();

          Trajectory m_getWhitePixel1 = m_robot.drivetrain.trajectoryBuilder(m_alignToWhitePixel1.end(), false)
                  .lineTo(new Vector2d(-48, -27.5))
                  .build();

          switch (m_Analysis){
               case 1:
                    cmds.addCommands(
                            new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_prePurple1)
                                    ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 45)
                                    ,new SequentialCommandGroup(
                                        new Sleep(1500)
                                        ,new ParallelCommandGroup(
                                             new CMD_SetElbowAngle(m_robot.m_elbow, 60)
                                             ,new SequentialCommandGroup(
                                                new Sleep(500)
                                                ,new CMD_SetWristPosition(m_robot.m_wrist, .5)
                                             )
                                    ))
                            )
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel1)
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
//                            ,new ParallelCommandGroup(
//                                    new CMD_ArmSetReadyIntakeLevelFive(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
//                                    ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_alignToWhitePixel1)
//                            )
//                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_getWhitePixel1)
//                            ,new CMD_ArmDropIntakeLevelFive(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                    );
                    break;
               case 2:

                    break;
               case 3:

                    break;
          }

          return cmds;
     }

     //-30, 80

     private SequentialCommandGroup placeYellowPixel(){
          SequentialCommandGroup cmds = new SequentialCommandGroup();
          Trajectory dropSpot1 = m_robot.drivetrain.trajectoryBuilder(new Pose2d(-47, -27, Math.toRadians(-90)), true)
                  .splineToLinearHeading(new Pose2d(-32, 84, Math.toRadians(-90)), Math.toRadians(70))
                  .build();

          Trajectory getMorePixels1 = m_robot.drivetrain.trajectoryBuilder(dropSpot1.end(), false)
                  .lineToLinearHeading(new Pose2d(-45, 50, Math.toRadians(-90)))//-48,
                  .splineToLinearHeading(new Pose2d(-45, -12, Math.toRadians(-90)), Math.toRadians(70))
                  .build();

          Trajectory forward1 = m_robot.drivetrain.trajectoryBuilder(getMorePixels1.end(), false)
                  .forward(13)
                  .build();

          Trajectory park1 = m_robot.drivetrain.trajectoryBuilder(forward1.end(), true)
                  .splineToLinearHeading(new Pose2d(-32, 84, Math.toRadians(-90)), Math.toRadians(70))
                  .build();

          switch (m_Analysis){
               case 1:
                    cmds.addCommands(
                            new ParallelCommandGroup(
                               new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                               ,new SequentialCommandGroup(
                                   new Sleep(1000)
                                   ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, dropSpot1)

                               )
                            )
                            ,new CMD_ArmSetLevelOne(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                            ,new Sleep(500)
                            ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                            ,new Sleep(250)
                            ,new ParallelCommandGroup(
                                    new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                                    ,new SequentialCommandGroup(
                                         new Sleep(1000)
                                         ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, getMorePixels1)
                                    )
                            )
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, forward1)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, park1)
                    );
                    break;
               case 2:

                    break;
               case 3:

                    break;
          }
          return cmds;
     }
}
