package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_WristSetReadyToIntake extends CommandBase {
     SUB_Wrist m_wrist;
     GlobalVariables m_variables;
     public CMD_WristSetReadyToIntake(SUB_Wrist p_wrist, GlobalVariables p_variables){
          m_wrist = p_wrist;
          m_variables = p_variables;
     }

     @Override
     public void initialize(){
          m_wrist.setPosition(Constants.WristConstants.kReadyToIntakePosition);
     }

     @Override
     public boolean isFinished(){
          return true;
     }
}
