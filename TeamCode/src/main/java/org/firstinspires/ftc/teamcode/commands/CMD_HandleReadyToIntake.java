package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.GlobalVariables;

public class CMD_HandleReadyToIntake  extends CommandBase {
    RobotContainer m_robot;
    public CMD_HandleReadyToIntake(RobotContainer p_robot){
        m_robot=p_robot;
    }

    @Override
    public void initialize() {

        // check for allowable robot state; exit if not
        if (m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Home)
                  || m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Stow)
                  || m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake)
                || m_robot.m_variables.isRobotState(GlobalVariables.RobotState.TransitioningToIntake))
        {  /* these are valid states, allow to continue */ } else return;

        // is the intake currently on? turn off
        if (m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake)) {
            m_robot.schedule(new CMD_SetReadyToIntakeOff(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables, m_robot.m_intake));
            return;
        }

        // intake not on, turn  om
        m_robot.schedule(new CMD_SetReadyToIntake(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables, m_robot.m_intake));
    };

    @Override
    public boolean isFinished(){
        return true;
    }
}
