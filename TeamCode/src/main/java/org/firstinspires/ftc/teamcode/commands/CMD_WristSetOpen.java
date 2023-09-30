package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_WristSetOpen extends CommandBase {
     SUB_Wrist m_wrist;
     public CMD_WristSetOpen(SUB_Wrist p_wrist){
          m_wrist = p_wrist;
     }

     @Override
     public void initialize(){
          m_wrist.setPosition(0.6);
     }
     @Override
     public boolean isFinished(){
          return true;
     }

}
