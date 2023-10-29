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

@Autonomous(name="Auto Red Left", group = "Auto Red", preselectTeleOp = "Robot Teleop")
public class AUTO_Red_Left extends Robot_Auto {

     private int m_Analysis;

     public AUTO_Red_Left(){
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

          Trajectory m_prePurplePixel1 = m_robot.drivetrain.trajectoryBuilder(m_initalPose, true)
                  .lineTo(new Vector2d(-36, -40))
                  .build();

          Trajectory m_purplePixel1 = m_robot.drivetrain.trajectoryBuilder(m_prePurplePixel1.end(), true)
                  .lineToLinearHeading(new Pose2d(-32, -30, Math.toRadians(-40)))
                  .build();

          Trajectory m_readyToDropSpot1 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel1.end(), true)
                  .back(3)
                  .splineTo(new Vector2d(-54, -24), Math.toRadians(60))
                  .build();

          Trajectory m_dropSpot1 = m_robot.drivetrain.trajectoryBuilder(m_readyToDropSpot1.end(), true)
                  .splineTo(new Vector2d(0, -10), Math.toRadians(0))
                  .splineTo(new Vector2d(50, -39), Math.toRadians(0))
                  .build();


          Trajectory m_purplePixel2 = m_robot.drivetrain.trajectoryBuilder(m_initalPose, true)
                  .splineTo(new Vector2d(-34,-18), Math.toRadians(55))
                  .build();

          Trajectory m_dropSpot2 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel2.end(), true)
                  .splineTo(new Vector2d(50, -31), Math.toRadians(0))
                  .build();

          Trajectory m_purplePixel3 = m_robot.drivetrain.trajectoryBuilder(m_initalPose, true)
                  .splineTo(new Vector2d(-41, -26), Math.toRadians(55))
                  .build();

          Trajectory m_dropSpot3 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel3.end(), true)
                  .splineTo(new Vector2d(50, -26.5), Math.toRadians(0))
                  .build();


          switch(m_Analysis){
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
                                    new SequentialCommandGroup(
                                            new Sleep(1000)
                                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_readyToDropSpot1)
                                    )
                                    ,new SequentialCommandGroup(
                                    new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                                    ,new CMD_SetElbowAngle(m_robot.m_elbow, -2)
                                    ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 9)
                            )
                            )
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot1)
                                    ,new SequentialCommandGroup(
                                         new Sleep(1500)
                                         ,new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
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
                            ,new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                            ,new CMD_SetElbowAngle(m_robot.m_elbow, -2)
                            ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 9)
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot2)
                                    ,new SequentialCommandGroup(
                                         new Sleep(1500)
                                         ,new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
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
                                    ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel3)
                            )
                            ,new Sleep(1000)
                            ,new InstantCommand(()-> m_robot.m_wrist.openClawB())
                            ,new Sleep(500)
                            ,new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
                            ,new CMD_SetElbowAngle(m_robot.m_elbow, -2)
                            ,new CMD_SetShoulderAngle(m_robot.m_shoulder, 9)
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot3)
                                    ,new SequentialCommandGroup(
                                         new Sleep(1500)
                                         ,new CMD_SetWristPosition(m_robot.m_wrist, 0.0)
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
