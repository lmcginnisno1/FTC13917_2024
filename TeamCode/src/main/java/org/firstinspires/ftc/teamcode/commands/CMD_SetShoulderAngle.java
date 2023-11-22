package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;

public class CMD_SetShoulderAngle extends CommandBase {
    SUB_Shoulder m_shoulder;
    double m_angle;
    double m_tolerance = 5;
    boolean m_noWait;
    boolean m_isFinished;

    public CMD_SetShoulderAngle(SUB_Shoulder p_shoulder, double p_angle) {
        m_shoulder = p_shoulder;
        m_angle = p_angle;
//        addRequirements(m_shoulder);
    }

    public CMD_SetShoulderAngle setTolerance(int p_tolerance) {
        m_tolerance = p_tolerance;
        return this;
    }

    public CMD_SetShoulderAngle noWait() {
        m_noWait = true;
        return this;
    }

    @Override
    public void initialize(){
        m_shoulder.setTargetAngle(m_angle);
    }

    @Override
    public boolean isFinished() {

        m_isFinished = (boolean) (Math.abs(m_shoulder.getAngle() - m_angle) <= m_tolerance);
        return m_isFinished || m_noWait;
    }
}
