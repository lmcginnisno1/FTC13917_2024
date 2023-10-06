package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Constants.WristConstants;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_WristSetClosed extends CommandBase {
     SUB_Wrist m_wrist;
     public CMD_WristSetClosed(SUB_Wrist p_wrist){
          m_wrist = p_wrist;
     }

     @Override
     public void initialize(){
          m_wrist.setPosition(WristConstants.m_closedPosition);
     }
     @Override
     public boolean isFinished(){
          return true;
     }

}
