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
@Autonomous(name="Red Left Strafe", group = "Auto Red", preselectTeleOp = "Robot Teleop")
public class AUTO_Red_Left_Strafe extends Robot_Auto {

     private int m_Analysis;

     public AUTO_Red_Left_Strafe(){
          super(true);
     }

     @Override
     public void prebuildTasks() {
          //run these tasks now
          setStartingPose(new Pose2d(-36, -61, Math.toRadians(-90)));
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
          Pose2d m_initalPose = getStartingPose();
          int m_delayYellowPixel = 100000;

          Trajectory m_purplePixel1 = m_robot.drivetrain.trajectoryBuilder(m_initalPose, true)
                  .splineTo(new Vector2d(-42.5, -26), Math.toRadians(55))
                  .build();

          Trajectory m_dropSpot1 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel1.end(), true)
                  .splineTo(new Vector2d(0, -12), Math.toRadians(0))
                  .splineTo(new Vector2d(40, -26.5), Math.toRadians(0))
                  .build();

          Pose2d m_startOfStrafe1 = new Pose2d(55, -43, Math.toRadians(180));

          Trajectory m_strafe1 = m_robot.drivetrain.trajectoryBuilder(m_startOfStrafe1, true)
                  .strafeTo(new Vector2d(53, -31.0))
                  .build();


          Trajectory m_park1 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot1.end(), true)
                  .lineToConstantHeading(new Vector2d(50, -18))
                  .build();


          Trajectory m_purplePixel2 = m_robot.drivetrain.trajectoryBuilder(m_initalPose, true)
                  .splineTo(new Vector2d(-34,-19), Math.toRadians(55))
                  .build();

          Trajectory m_dropSpot2 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel2.end(), true)
                  .splineTo(new Vector2d(10, -12), Math.toRadians(0))
                  .splineTo(new Vector2d(40, -31), Math.toRadians(0))
                  .build();

          Pose2d m_startOfStrafe2 = new Pose2d(55, -43, Math.toRadians(180));

          Trajectory m_strafe2= m_robot.drivetrain.trajectoryBuilder(m_startOfStrafe2, true)
                  .strafeTo(new Vector2d(53, -33.5))
                  .build();

          Trajectory m_park2 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot2.end(), true)
                  .lineToConstantHeading(new Vector2d(50, -18))
                  .build();

          Trajectory m_prePurplePixel3 = m_robot.drivetrain.trajectoryBuilder(m_initalPose, true)
                  .lineTo(new Vector2d(-36, -38))
                  .build();

          Trajectory m_purplePixel3 = m_robot.drivetrain.trajectoryBuilder(m_prePurplePixel3.end(), true)
                  .lineToLinearHeading(new Pose2d(-31, -28, Math.toRadians(-35)))
                  .build();

          Trajectory m_readyToDropSpot3 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel3.end(), true)
                  .back(4)
                  .splineTo(new Vector2d(-54, -24), Math.toRadians(60))
                  .build();

          Trajectory m_dropSpot3 = m_robot.drivetrain.trajectoryBuilder(m_readyToDropSpot3.end(), true)
                  .splineTo(new Vector2d(0, -10), Math.toRadians(0))
                  .splineTo(new Vector2d(20, -12), Math.toRadians(-30))
                  .splineTo(new Vector2d(40, -32), Math.toRadians(0))
                  .build();

          Pose2d m_startOfStrafe3 = new Pose2d(55, -30, Math.toRadians(180));

          Trajectory m_strafe3 = m_robot.drivetrain.trajectoryBuilder(m_startOfStrafe3, true)
                  .strafeTo(new Vector2d(53, -41.0))
                  .build();

          Trajectory m_park3 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot3.end(), true)
                  .lineToConstantHeading(new Vector2d(50, -18))
                  .build();

          switch(m_Analysis){
               case 1:
                    cmds.addCommands(
                            new ParallelCommandGroup(
                                    new ParallelCommandGroup(
                                            new CMD_SetShoulderAngle(m_robot.m_shoulder, 40)
                                            ,new SequentialCommandGroup(
                                                 new Sleep(1000)
                                                 ,new CMD_SetElbowAngle(m_robot.m_elbow, 50)
                                                 ,new CMD_SetWristPosition(m_robot.m_wrist, .5)
                                            )
                                    )
                                    ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel1)
                            )
                            ,new InstantCommand(()-> m_robot.m_wrist.openClawB())
                            ,new Sleep(500)
                            ,new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                            ,new CMD_SetElbowAngle(m_robot.m_elbow, -2)
                            ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 9)
                            ,new Sleep(m_delayYellowPixel)
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot1)
                                    ,new SequentialCommandGroup(
                                    new Sleep(1500)
                                    ,new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                    ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 125).setTolerance(45)
                                    ,new CMD_SetElbowAngle(m_robot.m_elbow, -47.5)
                                    ,new CMD_SetWristPosition(m_robot.m_wrist, 0.25)
                            )
                            )
                            ,new VisionUpdatePose(m_robot.m_backCamera, m_robot.drivetrain)
                            ,new RR_TrajectoryLineToLinearHeadingFromCurrent(m_robot, m_startOfStrafe1, true)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_strafe1)
                            ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                            ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_park1)

                    );
                    break;
               case 2:
                    cmds.addCommands(
                            new ParallelCommandGroup(
                                    new ParallelCommandGroup(
                                            new CMD_SetShoulderAngle(m_robot.m_shoulder, 40)
                                            ,new SequentialCommandGroup(
                                                 new Sleep(1000)
                                                 ,new CMD_SetElbowAngle(m_robot.m_elbow, 50)
                                                 ,new CMD_SetWristPosition(m_robot.m_wrist, .5)
                                            )
                                    )
                                    ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel2)
                            )
                            ,new InstantCommand(()-> m_robot.m_wrist.openClawB())
                            ,new Sleep(500)
                            ,new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                            ,new CMD_SetElbowAngle(m_robot.m_elbow, -2)
                            ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 9)
                            ,new Sleep(m_delayYellowPixel)
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot2)
                                    ,new SequentialCommandGroup(
                                    new Sleep(1500)
                                    ,new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                    ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 125).setTolerance(45)
                                    ,new CMD_SetElbowAngle(m_robot.m_elbow, -40)
                                    ,new CMD_SetWristPosition(m_robot.m_wrist, 0.3)
                            )
                            )
                            ,new VisionUpdatePose(m_robot.m_backCamera, m_robot.drivetrain)
                            ,new RR_TrajectoryLineToLinearHeadingFromCurrent(m_robot, m_startOfStrafe2, true)
                            , new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_strafe2)
                            ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                            ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_park2)

                    );
                    break;
               case 3:
                    cmds.addCommands(
                            new ParallelCommandGroup(
                                    new ParallelCommandGroup(
                                            new CMD_SetShoulderAngle(m_robot.m_shoulder, 40)
                                            ,new SequentialCommandGroup(
                                                 new Sleep(1000)
                                                 ,new CMD_SetElbowAngle(m_robot.m_elbow, 50)
                                                 ,new CMD_SetWristPosition(m_robot.m_wrist, .5)
                                            )
                                    )
                                    ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_prePurplePixel3)
                            )
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel3)
                            ,new InstantCommand(()-> m_robot.m_wrist.openClawB())
                            ,new Sleep(500)
                            ,new ParallelCommandGroup(
                                    new SequentialCommandGroup(
                                            new Sleep(1000)
                                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_readyToDropSpot3)
                                    )
                                    ,new SequentialCommandGroup(
                                    new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                    ,new CMD_SetElbowAngle(m_robot.m_elbow, -2)
                                    ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 9)
                            )
                            )
                            ,new Sleep(m_delayYellowPixel)
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot3)
                                    ,new SequentialCommandGroup(
                                    new Sleep(2000)
                                    ,new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                    ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 125).setTolerance(45)
                                    ,new CMD_SetElbowAngle(m_robot.m_elbow, -40)
                                    ,new CMD_SetWristPosition(m_robot.m_wrist, 0.3)
                            )
                            )
                            ,new VisionUpdatePose(m_robot.m_backCamera, m_robot.drivetrain)
                            ,new RR_TrajectoryLineToLinearHeadingFromCurrent(m_robot, m_startOfStrafe3, true)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_strafe3)
                            ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                            ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_park3)
                    );
                    break;
          }

          return cmds;
     }
}