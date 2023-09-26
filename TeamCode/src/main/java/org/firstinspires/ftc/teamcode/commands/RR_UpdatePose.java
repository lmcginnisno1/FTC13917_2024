package org.firstinspires.ftc.teamcode.commands;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;


public class RR_UpdatePose extends CommandBase {
    private final MecanumDriveSubsystem m_drive;
    double m_x, m_y;
    double m_heading;
    boolean m_updateX = false, m_updateY=false, m_updateHeading =false;

    // @param subsystem The subsystem used by this command.
    public RR_UpdatePose(MecanumDriveSubsystem p_drive) {
        m_drive = p_drive;
    }

    public RR_UpdatePose setX(double x) {
        m_x = x;
        m_updateX = true;
        return this;
    }

    public RR_UpdatePose setY(double y) {
        m_y = y;
        m_updateY = true;
        return this;
    }

    public RR_UpdatePose setHeading(double heading) {
        m_heading = heading;
        m_updateHeading = true;
        return this;
    }


    @Override
    public void initialize() {
        Pose2d poseEstimate = m_drive.getPoseEstimate();
        if (!m_updateX) m_x = poseEstimate.getX();
        if (!m_updateY) m_y = poseEstimate.getY();
        if (!m_updateHeading) m_heading = poseEstimate.getHeading();

        Pose2d pose = new Pose2d(m_x,m_y,m_heading);
        m_drive.setPoseEstimate(pose);
    }

    @Override
    public boolean isFinished() {
        return true;
    }


}