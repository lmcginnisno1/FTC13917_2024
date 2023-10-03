package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Arm;
import org.firstinspires.ftc.teamcode.Constants.WristConstants;

public class CMD_WristSetClosed extends CommandBase {
     SUB_Arm m_arm;
     public CMD_WristSetClosed(SUB_Arm p_arm){
          m_arm = p_arm;
     }

     @Override
     public void initialize(){
          m_arm.setWristPosition(WristConstants.m_closedPosition);
     }
     @Override
     public boolean isFinished(){
          return true;
     }

}
