package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.util.TrapezoidMotionProfile;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;

public class CMD_TrapezoidProfileCommandShoulder extends CommandBase {
     SUB_Shoulder m_shoulder;
     ElapsedTime m_elapsedTime;
     TrapezoidMotionProfile m_profile = new TrapezoidMotionProfile();
     double m_position;
     double m_tolerance;
     public CMD_TrapezoidProfileCommandShoulder(SUB_Shoulder p_shoulder, double p_position, double p_tolerance){
          m_shoulder = p_shoulder;
          m_position = p_position;
          m_tolerance = p_tolerance;
     }

     @Override
     public void initialize(){
          m_elapsedTime.reset();
     }

     @Override
     public void execute(){
          m_shoulder.setTargetPosition((int)m_profile.motion_profile(1, 1,
                  m_position, m_elapsedTime.time()));
          m_shoulder.setPower(0.5);
     }
     @Override
     public boolean isFinished(){
          return Math.abs(m_position - m_shoulder.getPosition()) <= m_tolerance;
     }

}
