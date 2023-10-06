package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.util.TrapezoidMotionProfile;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;

public class CMD_TrapezoidProfileCommandElbow extends CommandBase {
     SUB_Elbow m_elbow;
     ElapsedTime m_elapsedTime;
     TrapezoidMotionProfile m_profile = new TrapezoidMotionProfile();
     double m_position;
     double m_tolerance;
     public CMD_TrapezoidProfileCommandElbow(SUB_Elbow p_elbow, double p_position, double p_tolerance){
          m_elbow = p_elbow;
          m_position = p_position;
          m_tolerance = p_tolerance;
     }

     @Override
     public void initialize(){
          m_elapsedTime.reset();
     }

     @Override
     public void execute(){
          m_elbow.setTargetPosition((int)m_profile.motion_profile(1, 1,
                  m_position, m_elapsedTime.time()));
          m_elbow.setPower(0.5);
     }
     @Override
     public boolean isFinished(){
          return Math.abs(m_position - m_elbow.getPosition()) <= m_tolerance;
     }

}
