package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;

public class CMD_HandleReadyToDeploy extends CommandBase {
        RobotContainer m_robot;
        private boolean m_isRedSide;
        public CMD_HandleReadyToDeploy(RobotContainer p_robot, boolean p_isRedSide){
            m_robot=p_robot;
            m_isRedSide = p_isRedSide;
        }

        @Override
        public void initialize() {

            // check for allowable robot stats; exit if not
            if (m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake)
                    || m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Stow)
                    || m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToDeploy)
                    || m_robot.m_variables.isRobotState(GlobalVariables.RobotState.TransitioningToDeploy)
                    )
            {  /* these are valid states, allow to continue */ } else return;


            if (m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake)) {
                // if ReadyToIntake, then stow
                m_robot.schedule(
                        new SequentialCommandGroup(
                                new CMD_SetReadyToIntakeOff(m_robot.m_shoulder, m_robot.m_elbow,
                                        m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables, m_robot.m_intake)
                                ,new InstantCommand(() -> m_robot.m_wrist.closePincher())
                                ,new Sleep(500)
                                ,new InstantCommand(()-> m_robot.m_wrist.setPosition(Constants.WristConstants.kHome + 0.05))
                                ,new InstantCommand(() -> m_robot.m_elbow.setTargetAngle(-15))
                                ,new InstantCommand(() -> m_robot.m_intake.conveyorReverse())
                                ,new Sleep(1000)
                                ,new InstantCommand(() -> m_robot.m_intake.conveyorOff())
                        )
                );
                return;

            } else if (m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToDeploy)) {
                // if ReadyToDeploy, Deploy
                m_robot.schedule(
                        new SequentialCommandGroup(
//                                new RR_VisionUpdatePose(m_robot.m_backCamera, m_robot.drivetrain)
//                                ,
                                new CMD_DriveAlignToBoard(m_robot.drivetrain)
                                , new CMD_AutoDropOff_Steps(m_robot.drivetrain, m_robot.m_shoulder, m_robot.m_elbow,
                                m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables, m_isRedSide)

                        )
                );
                return;
            }

            m_robot.schedule(
                    new ParallelCommandGroup(
                            new RR_VisionUpdatePose(m_robot.m_backCamera, m_robot.drivetrain)
                            , new CMD_SetReadyToDeploy(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist,
                            m_robot.m_blank, m_robot.m_variables, m_robot.m_intake)

                    )
            );

        };

        @Override
        public boolean isFinished(){
            return true;
        }
    }

