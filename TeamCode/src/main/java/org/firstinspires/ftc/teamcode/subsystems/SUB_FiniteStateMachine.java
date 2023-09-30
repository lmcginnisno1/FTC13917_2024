package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class SUB_FiniteStateMachine extends TriggerSubsystemBase {

    private OpMode m_opMode;

    public enum RobotState {
        Home
        ,Ready2Intake
        ,Intake
        ,Level1
        ,Level2
        ,Level3
        ,Level4
        ,Ready2Score
        ,Score
    }

    private RobotState m_currentRobotState = RobotState.Home;

    public SUB_FiniteStateMachine(OpMode p_opMode) {
        m_opMode = p_opMode;
        // m_currentRobotState = RobotState.CAPTURE;
    }

    public void setRobotState(RobotState p_robotState) {
        m_currentRobotState = p_robotState;
    }

    public RobotState getRobotState() {
        return m_currentRobotState;
    }

    public boolean getState(RobotState p_robotState) {
        return (m_currentRobotState == p_robotState);
    }

    public boolean getState(String p_robotState) {
        return (m_currentRobotState.toString().equals(p_robotState));
    }

    public void telemetry() {
        m_opMode.telemetry.addData("State", m_currentRobotState.name());
    }

    public void periodic() {
        telemetry();
    }
}

