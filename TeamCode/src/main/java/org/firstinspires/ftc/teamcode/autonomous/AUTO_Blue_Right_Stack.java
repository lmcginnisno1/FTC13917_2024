package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robot_Auto;
import org.firstinspires.ftc.teamcode.commands.RR_TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.commands.RR_TrajectoryLineToLinearHeadingFromCurrent;
import org.firstinspires.ftc.teamcode.commands.Sleep;
import org.firstinspires.ftc.teamcode.commands.RR_VisionUpdatePose;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;

@Autonomous(name = "Blue Right", group = "Auto Blue", preselectTeleOp = "Robot Teleop")
public class AUTO_Blue_Right_Stack extends Robot_Auto {
     int m_Analysis;
     public AUTO_Blue_Right_Stack(){
          super(false);
     }

     @Override
     public void prebuildTasks() {
          //run these tasks now
          //set robot starting pose
          setStartingPose(new Pose2d(-36, 61, Math.toRadians(90)));
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
          Pose2d m_initialPose = getStartingPose();
          int m_delayYellowPixel = 10000;

          Trajectory m_prePurplePixel1 = m_robot.drivetrain.trajectoryBuilder(m_initialPose, true)
                  .lineTo(new Vector2d(-36, 38))
                  .build();

          Trajectory m_purplePixel1 = m_robot.drivetrain.trajectoryBuilder(m_prePurplePixel1.end(), true)
                  .lineToLinearHeading(new Pose2d(-31, 28, Math.toRadians(35)))
                  .build();

          Trajectory m_stackPixelLineUp1 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel1.end(), true)
                  .lineToLinearHeading(new Pose2d(-52, 12, Math.toRadians(180)))
                  .build();

          Trajectory m_getWhitePixel1 = m_robot.drivetrain.trajectoryBuilder(m_stackPixelLineUp1.end(), false)
                  .lineToLinearHeading(new Pose2d(-66, 12, Math.toRadians(180)))
                  .build();

          Trajectory m_dropSpot1 = m_robot.drivetrain.trajectoryBuilder(m_getWhitePixel1.end(), true)
                  .splineTo(new Vector2d(0, 10), Math.toRadians(0))
                  .splineTo(new Vector2d(20, 12), Math.toRadians(30))
                  .splineTo(new Vector2d(46, 32), Math.toRadians(0))
                  .build();

          Trajectory m_park1 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot1.end(), true)
                  .lineToConstantHeading(new Vector2d(50, 18))
                  .build();


          Trajectory m_purplePixel2 = m_robot.drivetrain.trajectoryBuilder(m_initialPose, true)
                  .splineTo(new Vector2d(-34,18), Math.toRadians(-55))
                  .build();

          Trajectory m_stackPixelLineUp2 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel2.end(), true)
                  .splineTo(new Vector2d(-24, 12), Math.toRadians(0))
                  .build();

          Trajectory m_getWhitePixel2 = m_robot.drivetrain.trajectoryBuilder(m_stackPixelLineUp2.end(), false)
                  .lineToLinearHeading(new Pose2d(-66, 12, Math.toRadians(180)))
                  .build();

          Trajectory m_dropSpot2 = m_robot.drivetrain.trajectoryBuilder(m_getWhitePixel2.end(), true)
                  .splineTo(new Vector2d(18, 12), Math.toRadians(0))
                  .splineTo(new Vector2d(40, 31), Math.toRadians(0))
                  .build();

          Trajectory m_park2 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot2.end(), true)
                  .lineToConstantHeading(new Vector2d(50, 18))
                  .build();


          Trajectory m_purplePixel3 = m_robot.drivetrain.trajectoryBuilder(m_initialPose, true)
                  .splineTo(new Vector2d(-41, 26), Math.toRadians(-55))
                  .build();

          Trajectory m_stackPixelLineUp3 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel3.end(), true)
                  .splineTo(new Vector2d(-24, 12), Math.toRadians(0))
                  .build();

          Trajectory m_getWhitePixel3 = m_robot.drivetrain.trajectoryBuilder(m_stackPixelLineUp3.end(), false)
                  .lineToLinearHeading(new Pose2d(-66, 12, Math.toRadians(180)))
                  .build();


          Trajectory m_dropSpot3 = m_robot.drivetrain.trajectoryBuilder(m_getWhitePixel3.end(), true)
                  .splineTo(new Vector2d(0, 12), Math.toRadians(0))
                  .splineTo(new Vector2d(40, 26.5), Math.toRadians(0))
                  .build();

          Trajectory m_park3 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot3.end(), true)
                  .lineToConstantHeading(new Vector2d(50, 18))
                  .build();


          switch(m_Analysis){
               case 1:
                    cmds.addCommands(
                            new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_prePurplePixel1)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel1)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_stackPixelLineUp1)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_getWhitePixel1)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot1)
                            ,new Sleep(1000)
                            ,new RR_VisionUpdatePose(m_robot.m_backCamera, m_robot.drivetrain)
                            ,new RR_TrajectoryLineToLinearHeadingFromCurrent(m_robot, new Pose2d(52.5, 41, Math.toRadians(180)), true)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_park1)
                    );
                    break;
               case 2:
                    cmds.addCommands(
                            new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel2)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_stackPixelLineUp2)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_getWhitePixel2)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot2)
                            ,new Sleep(1000)
                            ,new RR_VisionUpdatePose(m_robot.m_backCamera, m_robot.drivetrain)
                            ,new RR_TrajectoryLineToLinearHeadingFromCurrent(m_robot, new Pose2d(52.5, 33.5, Math.toRadians(180)), true)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_park2)
                    );
                    break;
               case 3:
                    cmds.addCommands(
                            new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel3)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_stackPixelLineUp3)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_getWhitePixel3)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot3)
                            ,new RR_VisionUpdatePose(m_robot.m_backCamera, m_robot.drivetrain)
                            ,new RR_TrajectoryLineToLinearHeadingFromCurrent(m_robot, new Pose2d(52.5, 27.5, Math.toRadians(180)))
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_park3)
                    );
                    break;
          }


          return cmds;
     }

}
