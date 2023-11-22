package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

public class CMD_Drive extends CommandBase {
     MecanumDriveSubsystem m_drive;
     double m_leftX;
     double m_leftY;
     double m_rightY;
     double m_time;
     ElapsedTime m_elapsedTime = new ElapsedTime();
     public CMD_Drive(MecanumDriveSubsystem p_drive, double p_leftY, double p_leftX, double p_rightY, double p_time){
          m_drive = p_drive;
          addRequirements(m_drive);
     }

     @Override
     public void initialize(){
          m_drive.drive(m_leftY, m_leftX, m_rightY);
          m_elapsedTime.reset();
     }

     @Override
     public boolean isFinished(){
          return m_elapsedTime.milliseconds() > m_time;
     }
}
