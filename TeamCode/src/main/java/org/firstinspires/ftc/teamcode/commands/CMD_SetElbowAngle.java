package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;

public class CMD_SetElbowAngle extends CommandBase {
    SUB_Elbow m_elbow;
    double m_angle;
    double m_tolerance = 5;
    boolean m_noWait;
    boolean m_isFinish= false;

    public CMD_SetElbowAngle(SUB_Elbow p_elbow, double p_angle) {
        m_elbow = p_elbow;
        m_angle = p_angle;
//        addRequirements(m_elbow);
    }

    public CMD_SetElbowAngle setTolerance(int p_tolerance) {
        m_tolerance = p_tolerance;
        return this;
    }

    public CMD_SetElbowAngle noWait() {
        m_noWait = true;
        return this;
    }

    @Override
    public void initialize(){
        m_elbow.setTargetAngle(m_angle);
    }

    @Override
    public boolean isFinished() {
        m_isFinish = (Math.abs(m_elbow.getAngle() - m_angle) <= m_tolerance);
        return m_isFinish || m_noWait;
    }
}
