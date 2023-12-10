package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Robot_Auto;
import org.firstinspires.ftc.teamcode.commands.RR_TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.commands.RR_TrajectoryLineToConstantHeadingFromCurrent;
import org.firstinspires.ftc.teamcode.commands.Sleep;
import org.firstinspires.ftc.teamcode.commands.RR_VisionUpdatePose;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;

@Disabled

@Autonomous(name = "Blue Left new copy 2", group = "Auto Blue", preselectTeleOp = "Robot Teleop")
public class AUTO_Blue_Left_Copy_2 extends Robot_Auto {
     int m_Analysis;
     public AUTO_Blue_Left_Copy_2() {
          super(false);
     }

     @Override
     public void prebuildTasks() {
          //run these tasks now
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
                  .splineTo(new Vector2d(31,35), Math.toRadians(0))
                  .build();

          Trajectory m_dropSpot1 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel1.end(), true)
                  .lineToConstantHeading(new Vector2d(44, 40))
                  .build();

          Trajectory m_park1 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot1.end(), true)
                  .lineToConstantHeading(new Vector2d(50, 60))
                  .build();


          Trajectory m_purplePixel2 = m_robot.drivetrain.trajectoryBuilder(getStartingPose(), true)
                  .splineTo(new Vector2d(24,25), Math.toRadians(0))
                  .build();

          Trajectory m_dropSpot2 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel2.end(), true)
                  .lineToConstantHeading(new Vector2d(43, 37.5))
                  .build();

          Trajectory m_park2 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot2.end(), true)
                  .lineToConstantHeading(new Vector2d(50, 60))
                  .build();


          Trajectory m_prePurplePixel3 = m_robot.drivetrain.trajectoryBuilder(getStartingPose(), true)
                  .lineTo(new Vector2d(12, 40))
                  .build();

          Trajectory m_purplePixel3 = m_robot.drivetrain.trajectoryBuilder(m_prePurplePixel3.end(), true)
                  .lineToLinearHeading(new Pose2d(7, 30, Math.toRadians(150)))
                  .build();

          Trajectory m_dropSpot3 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel3.end(), true)
                  .lineToLinearHeading(new Pose2d(43, 30.5, Math.toRadians(180)))
                  .build();

          Trajectory m_park3 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot3.end(), true)
                  .lineToConstantHeading(new Vector2d(50, 60))
                  .build();

          switch (m_Analysis){
               case 1:
                    cmds.addCommands(
                            new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel1)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot1)
                            ,new RR_VisionUpdatePose(m_robot.m_backCamera, m_robot.drivetrain)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryLineToConstantHeadingFromCurrent(m_robot, new Vector2d(53, 44), true)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_park1)
                    );
                    break;
               case 2:
                    cmds.addCommands(
                            new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel2)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot2)
                            ,new RR_VisionUpdatePose(m_robot.m_backCamera, m_robot.drivetrain)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryLineToConstantHeadingFromCurrent(m_robot, new Vector2d(52.5, 37), true)
                            ,new Sleep(1000)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_park2)
                    );
                    break;
               case 3:
                    cmds.addCommands(
                         new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_prePurplePixel3)
                         ,new Sleep(1000)
                         ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel3)
                         ,new Sleep(1000)
                         ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot3)
                         ,new Sleep(1000)
                         ,new RR_VisionUpdatePose(m_robot.m_backCamera, m_robot.drivetrain)
                         ,new RR_TrajectoryLineToConstantHeadingFromCurrent(m_robot, new Vector2d(52.5, 31), true)
                         ,new Sleep(1000)
                         ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_park3)
                    );
                    break;
          }
          return cmds;
     }

}
