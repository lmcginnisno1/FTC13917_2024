package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robot_Auto;
import org.firstinspires.ftc.teamcode.commands.CMD_ArmSetLevelHome;
import org.firstinspires.ftc.teamcode.commands.CMD_ArmSetLevelOne;
import org.firstinspires.ftc.teamcode.commands.CMD_SetElbowAngle;
import org.firstinspires.ftc.teamcode.commands.CMD_SetShoulderAngle;
import org.firstinspires.ftc.teamcode.commands.CMD_SetWristPosition;
import org.firstinspires.ftc.teamcode.commands.CMD_WristReleaseClaw;
import org.firstinspires.ftc.teamcode.commands.CMD_WristReleaseOutsideClaw;
import org.firstinspires.ftc.teamcode.commands.RR_TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.commands.Sleep;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.opencv.core.Mat;

@Autonomous(name = "Auto Red Left", group = "Auto Red", preselectTeleOp = "Robot Teleop")
public class AUTO_Red_Left extends Robot_Auto {

     private int m_Analysis;

     public AUTO_Red_Left() {
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
                  ,placeYellowPixel()
          );

          m_robot.schedule(completetasks);
          return completetasks;
     }


     private SequentialCommandGroup placePurplePixel(){
          SequentialCommandGroup cmds = new SequentialCommandGroup();
          Pose2d m_initialPose = new Pose2d(0, 0, Math.toRadians(0));

          Trajectory m_spikeMark1 = m_robot.drivetrain.trajectoryBuilder(m_initialPose, false)
                  .lineToLinearHeading(new Pose2d(-34, -8, Math.toRadians(-30)))
                  .build();

          switch (m_Analysis){
               case 1:
                    cmds.addCommands(
                            new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_spikeMark1)
                                    ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 50)
                            )
                            ,new ParallelCommandGroup(
                                    new CMD_SetElbowAngle(m_robot.m_elbow, 55)
                                    ,new CMD_SetWristPosition(m_robot.m_wrist, .5)
                            )
                            ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
                            ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
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
          Trajectory dropSpot1 = m_robot.drivetrain.trajectoryBuilder(new Pose2d(-34, -9, Math.toRadians(-30)), true)
                  .splineTo(new Vector2d(-45, 75), Math.toRadians(90))
                  .splineTo(new Vector2d(-33, 84), Math.toRadians(90))
                  .build();

          Trajectory park1 = m_robot.drivetrain.trajectoryBuilder(dropSpot1.end(), true)
                  .strafeRight(18)
                  .build();

          switch (m_Analysis){
               case 1:
                    cmds.addCommands(
                            new RR_TrajectoryFollowerCommand(m_robot.drivetrain, dropSpot1)
                            ,new CMD_ArmSetLevelOne(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                            ,new Sleep(500)
                            ,new CMD_WristReleaseClaw(m_robot.m_wrist)
                            ,new ParallelCommandGroup(
                               new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                               ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, park1)
                            )
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
