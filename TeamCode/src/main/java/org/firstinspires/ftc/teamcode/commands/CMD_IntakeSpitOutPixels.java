package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Intake;

public class CMD_IntakeSpitOutPixels extends CommandBase {
     SUB_Intake m_intake;
     ElapsedTime m_time = new ElapsedTime();
     public CMD_IntakeSpitOutPixels(SUB_Intake p_intake){
          m_intake = p_intake;
     }

     @Override
     public void initialize(){
          m_time.reset();
          m_intake.conveyorReverse();
     }

     @Override
     public boolean isFinished(){
          if(m_time.milliseconds() > 750){
               m_intake.conveyorOn();
               return true;
          }else{
               return false;
          }
     }
}
