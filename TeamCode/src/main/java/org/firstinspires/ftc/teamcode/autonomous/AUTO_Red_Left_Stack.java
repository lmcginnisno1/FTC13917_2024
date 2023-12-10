package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot_Auto;
import org.firstinspires.ftc.teamcode.commands.CMD_ArmSetLevelHome;
import org.firstinspires.ftc.teamcode.commands.CMD_SetReadyToDeploy;
import org.firstinspires.ftc.teamcode.commands.RR_TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.commands.RR_TrajectoryLineToLinearHeadingFromCurrent;
import org.firstinspires.ftc.teamcode.commands.RR_TurnCommand;
import org.firstinspires.ftc.teamcode.commands.Sleep;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;

@Autonomous(name="Red Left", group = "Auto Red", preselectTeleOp = "Robot Teleop")
public class AUTO_Red_Left_Stack extends Robot_Auto {

     private int m_Analysis;

     public AUTO_Red_Left_Stack(){
          super(true);
     }

     @Override
     public void prebuildTasks() {
          //run these tasks now
          setStartingPose(new Pose2d(-36, -61, Math.toRadians(-90)));
//          m_robot.m_wrist.closeClawA();
//          m_robot.m_wrist.closeClawB();
//          m_robot.m_wrist.setPosition(0);
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
          int m_delayYellowPixel = 10000;//10000

          Trajectory m_purplePixel1 = m_robot.drivetrain.trajectoryBuilder(m_initalPose, true)
                  .splineTo(new Vector2d(-47, -20), Math.toRadians(90))
                  .build();

          Trajectory m_stackPixelLineUp1 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel1.end(), true)
                  .lineTo(new Vector2d(-48, -12))
                  .build();

          Trajectory m_getWhitePixel1 = m_robot.drivetrain.trajectoryBuilder(
                  new Pose2d(m_stackPixelLineUp1.end().getX(), m_stackPixelLineUp1.end().getY(),
                          Math.toRadians(180)), false)
                  .lineToLinearHeading(new Pose2d(-56.5, -12, Math.toRadians(180)))
                  .build();

          Trajectory m_dropSpot1 = m_robot.drivetrain.trajectoryBuilder(m_getWhitePixel1.end(), true)
                  .splineTo(new Vector2d(0, -12), Math.toRadians(0))
                  .splineTo(new Vector2d(40, -29), Math.toRadians(0))
                  .build();

          Trajectory m_park1 = m_robot.drivetrain.trajectoryBuilder(new Pose2d(46, -21, Math.toRadians(180)), true)
                  .lineToConstantHeading(new Vector2d(50, -12))
                  .build();


          Trajectory m_purplePixel2 = m_robot.drivetrain.trajectoryBuilder(m_initalPose, true)
                  .splineTo(new Vector2d(-36,-13), Math.toRadians(90))
                  .build();

          Trajectory m_stackPixelLineUp2 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel2.end(), true)
                  .lineTo(new Vector2d(-36, -12))
                  .build();

          Trajectory m_getWhitePixel2 = m_robot.drivetrain.trajectoryBuilder(
                  new Pose2d(m_stackPixelLineUp2.end().getX(), m_stackPixelLineUp2.end().getY(),
                          Math.toRadians(180)), false)
                  .lineToLinearHeading(new Pose2d(-56.5, -12, Math.toRadians(180)))
                  .build();

          Trajectory m_dropSpot2 = m_robot.drivetrain.trajectoryBuilder(m_getWhitePixel2.end(), true)
                  .splineTo(new Vector2d(18, -12), Math.toRadians(0))
                  .splineTo(new Vector2d(40, -27), Math.toRadians(0))
                  .build();

          Trajectory m_park2 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot2.end(), true)
                  .lineToConstantHeading(new Vector2d(50, -18))
                  .build();

          Trajectory m_prePurplePixel3 = m_robot.drivetrain.trajectoryBuilder(m_initalPose, true)
                  .lineTo(new Vector2d(-36, -38))
                  .build();

          Trajectory m_purplePixel3 = m_robot.drivetrain.trajectoryBuilder(m_prePurplePixel3.end(), true)
                  .lineToLinearHeading(new Pose2d(-35, -29, Math.toRadians(-35)))
                  .build();

          Trajectory m_stackPixelLineUp3 = m_robot.drivetrain.trajectoryBuilder(m_purplePixel3.end(), true)
                  .splineTo(new Vector2d(-48, -12), Math.toRadians(90))
                  .build();

          Trajectory m_getWhitePixel3 = m_robot.drivetrain.trajectoryBuilder(
                  new Pose2d(m_stackPixelLineUp3.end().getX(), m_stackPixelLineUp3.end().getY(),
                          Math.toRadians(180)), false)
                  .lineTo(new Vector2d(-56.5, -12))
                  .build();

          Trajectory m_dropSpot3 = m_robot.drivetrain.trajectoryBuilder(m_getWhitePixel3.end(), true)
                  .splineTo(new Vector2d(0, -10), Math.toRadians(0))
                  .splineTo(new Vector2d(20, -12), Math.toRadians(-30))
                  .splineTo(new Vector2d(46, -27), Math.toRadians(0))
                  .build();

          Trajectory m_park3 = m_robot.drivetrain.trajectoryBuilder(m_dropSpot3.end(), true)
                  .lineToConstantHeading(new Vector2d(50, -12))
                  .build();

          switch(m_Analysis){
               case 1:
                    cmds.addCommands(
                            new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel1)
                            ,new InstantCommand(()-> m_robot.m_wrist.setPosition(Constants.WristConstants.kHome + 0.05))
                            ,new InstantCommand(()->m_robot.m_elbow.setTargetAngle(Constants.ElbowConstants.kLiftOffConveyor))
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorReverse())
                            ,new Sleep(500)
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorOff())
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_stackPixelLineUp1)
                            ,new RR_TurnCommand(m_robot.drivetrain, Math.toRadians(-90))
                            ,new InstantCommand(()-> m_robot.m_intake.setPivotPosition(Constants.IntakeConstants.kPivotServoStackPosition[5]))
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_getWhitePixel1)
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorOn())
                            ,new InstantCommand(()-> m_robot.m_elbow.setTargetAngle(Constants.ElbowConstants.kReadyToIntakePosition))
                            ,new InstantCommand(()-> m_robot.m_wrist.openPincher())
                            ,new InstantCommand(()-> m_robot.m_wrist.setPosition(Constants.WristConstants.kReadyToIntakePosition))
                            ,new Sleep(1000)
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorOff())
                            ,new InstantCommand(()-> m_robot.m_elbow.setTargetAngle(Constants.ElbowConstants.kHome))
                            ,new InstantCommand(()-> m_robot.m_wrist.setPosition(Constants.WristConstants.kHome))
                            ,new Sleep(250)
                            ,new InstantCommand(()-> m_robot.m_wrist.closePincher())
                            ,new Sleep(500)
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorReverse())
                            ,new Sleep(500)
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorOff())
                            ,new ParallelCommandGroup(
                               new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot1)
                               ,new SequentialCommandGroup(
                                   new Sleep(750)
                                   ,new InstantCommand(()-> m_robot.m_wrist.setPosition(Constants.WristConstants.kHome + 0.05))
                                   ,new InstantCommand(() -> m_robot.m_elbow.setTargetAngle(Constants.ElbowConstants.kLiftOffConveyor))
                                   ,new InstantCommand(()-> m_robot.m_variables.setScoringLevel(1))
                                   ,new Sleep(3000)
                                   ,new CMD_SetReadyToDeploy(m_robot.m_shoulder, m_robot.m_elbow,
                                        m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables, m_robot.m_intake)
                                   ,new InstantCommand(()-> m_robot.m_wrist.movePivotServo(Constants.WristConstants.kPivotRotation[5]))
                               )
                            )
                            ,new Sleep(500)
                            ,new InstantCommand(()-> m_robot.m_shoulder.setTargetAngle(125))
                            ,new InstantCommand(()-> m_robot.m_wrist.setPosition(.21))
                            ,new RR_TrajectoryLineToLinearHeadingFromCurrent(m_robot, new Pose2d(45, -38, Math.toRadians(180)), true)
                            ,new Sleep(250)
                            ,new RR_TrajectoryLineToLinearHeadingFromCurrent(m_robot, new Pose2d(46, -29, Math.toRadians(180)))
                            ,new Sleep(250)
                            ,new InstantCommand(()-> m_robot.m_wrist.openPincher())
                            ,new Sleep(250)
                            ,new InstantCommand(()-> m_robot.m_elbow.setTargetAngle(-20))
                            ,new Sleep(750)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_park1)
                            ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow,
                              m_robot.m_wrist, m_robot.m_blank)
                    );
                    break;
               case 2:
                    cmds.addCommands(
                            new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel2)
                            ,new InstantCommand(()-> m_robot.m_wrist.setPosition(Constants.WristConstants.kHome + 0.05))
                            ,new InstantCommand(()->m_robot.m_elbow.setTargetAngle(Constants.ElbowConstants.kLiftOffConveyor))
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorReverse())
                            ,new Sleep(1000)
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorOff())
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_stackPixelLineUp2)
                            ,new RR_TurnCommand(m_robot.drivetrain, Math.toRadians(-90))
                            ,new InstantCommand(()-> m_robot.m_intake.setPivotPosition(Constants.IntakeConstants.kPivotServoStackPosition[5]))
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_getWhitePixel2)
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorOn())
                            ,new InstantCommand(()-> m_robot.m_elbow.setTargetAngle(Constants.ElbowConstants.kReadyToIntakePosition))
                            ,new InstantCommand(()-> m_robot.m_wrist.openPincher())
                            ,new InstantCommand(()-> m_robot.m_wrist.setPosition(Constants.WristConstants.kReadyToIntakePosition))
                            ,new Sleep(750)
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorOff())
                            ,new InstantCommand(()-> m_robot.m_elbow.setTargetAngle(Constants.ElbowConstants.kHome))
                            ,new InstantCommand(()-> m_robot.m_wrist.setPosition(Constants.WristConstants.kHome))
                            ,new Sleep(250)
                            ,new InstantCommand(()-> m_robot.m_wrist.closePincher())
                            ,new Sleep(500)
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorReverse())
                            ,new Sleep(500)
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorOff())
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot2)
                                    ,new SequentialCommandGroup(
                                         new Sleep(750)
                                         ,new InstantCommand(()-> m_robot.m_wrist.setPosition(Constants.WristConstants.kHome + 0.05))
                                         ,new InstantCommand(() -> m_robot.m_elbow.setTargetAngle(Constants.ElbowConstants.kLiftOffConveyor))
                                         ,new InstantCommand(()-> m_robot.m_variables.setScoringLevel(1))
                                         ,new Sleep(3000)
                                         ,new CMD_SetReadyToDeploy(m_robot.m_shoulder, m_robot.m_elbow,
                                         m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables, m_robot.m_intake)
                                         ,new InstantCommand(()-> m_robot.m_wrist.movePivotServo(Constants.WristConstants.kPivotRotation[1]))
                                    )
                            )
                            ,new Sleep(500)
                            ,new InstantCommand(()-> m_robot.m_shoulder.setTargetAngle(125))
                            ,new InstantCommand(()-> m_robot.m_wrist.setPosition(.21))
                            ,new RR_TrajectoryLineToLinearHeadingFromCurrent(m_robot, new Pose2d(46, -27, Math.toRadians(180)), true)
                            ,new Sleep(250)
                            ,new RR_TrajectoryLineToLinearHeadingFromCurrent(m_robot, new Pose2d(47, -31, Math.toRadians(180)))
                            ,new Sleep(250)
                            ,new InstantCommand(()-> m_robot.m_wrist.openPincher())
                            ,new Sleep(250)
                            ,new InstantCommand(()-> m_robot.m_elbow.setTargetAngle(-20))
                            ,new Sleep(750)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_park1)
                            ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow,
                                    m_robot.m_wrist, m_robot.m_blank)
                    );
                    break;
               case 3:
                    cmds.addCommands(
                            new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_prePurplePixel3)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_purplePixel3)
                            ,new InstantCommand(()-> m_robot.m_wrist.setPosition(Constants.WristConstants.kHome + 0.05))
                            ,new InstantCommand(()->m_robot.m_elbow.setTargetAngle(Constants.ElbowConstants.kLiftOffConveyor))
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorReverse())
                            ,new Sleep(1000)
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorOff())
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_stackPixelLineUp3)
                            ,new RR_TurnCommand(m_robot.drivetrain, Math.toRadians(-90))
                            ,new InstantCommand(()-> m_robot.m_intake.setPivotPosition(Constants.IntakeConstants.kPivotServoStackPosition[5]))
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_getWhitePixel3)
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorOn())
                            ,new InstantCommand(()-> m_robot.m_elbow.setTargetAngle(Constants.ElbowConstants.kReadyToIntakePosition))
                            ,new InstantCommand(()-> m_robot.m_wrist.openPincher())
                            ,new InstantCommand(()-> m_robot.m_wrist.setPosition(Constants.WristConstants.kReadyToIntakePosition))
                            ,new Sleep(750)
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorOff())
                            ,new InstantCommand(()-> m_robot.m_elbow.setTargetAngle(Constants.ElbowConstants.kHome))
                            ,new InstantCommand(()-> m_robot.m_wrist.setPosition(Constants.WristConstants.kHome))
                            ,new Sleep(250)
                            ,new InstantCommand(()-> m_robot.m_wrist.closePincher())
                            ,new Sleep(500)
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorReverse())
                            ,new Sleep(500)
                            ,new InstantCommand(()-> m_robot.m_intake.conveyorOff())
                            ,new ParallelCommandGroup(
                                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_dropSpot3)
                                    ,new SequentialCommandGroup(
                                         new Sleep(750)
                                         ,new InstantCommand(()-> m_robot.m_wrist.setPosition(Constants.WristConstants.kHome + 0.05))
                                         ,new InstantCommand(() -> m_robot.m_elbow.setTargetAngle(Constants.ElbowConstants.kLiftOffConveyor))
                                         ,new InstantCommand(()-> m_robot.m_variables.setScoringLevel(1))
                                         ,new Sleep(3000)
                                         ,new CMD_SetReadyToDeploy(m_robot.m_shoulder, m_robot.m_elbow,
                                         m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables, m_robot.m_intake)
                                         ,new InstantCommand(()-> m_robot.m_wrist.movePivotServo(Constants.WristConstants.kPivotRotation[1]))
                                    )
                            )
                            ,new Sleep(500)
                            ,new InstantCommand(()-> m_robot.m_shoulder.setTargetAngle(125))
                            ,new InstantCommand(()-> m_robot.m_wrist.setPosition(.21))
                            ,new RR_TrajectoryLineToLinearHeadingFromCurrent(m_robot, new Pose2d(46, -27, Math.toRadians(180)), true)
                            ,new Sleep(250)
                            ,new RR_TrajectoryLineToLinearHeadingFromCurrent(m_robot, new Pose2d(47, -37.5, Math.toRadians(180)))
                            ,new Sleep(250)
                            ,new InstantCommand(()-> m_robot.m_wrist.openPincher())
                            ,new Sleep(250)
                            ,new InstantCommand(()-> m_robot.m_elbow.setTargetAngle(-20))
                            ,new Sleep(750)
                            ,new RR_TrajectoryFollowerCommand(m_robot.drivetrain, m_park3)
                            ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow,
                                    m_robot.m_wrist, m_robot.m_blank)
                    );
                    break;
          }

          return cmds;
     }
}
