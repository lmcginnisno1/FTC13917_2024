package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;

public class CMD_ShoulderSetReadyToIntake extends CommandBase {
     SUB_Shoulder m_shoulder;
     GlobalVariables m_variables;
     public CMD_ShoulderSetReadyToIntake(SUB_Shoulder p_shoulder, GlobalVariables p_variables){
          m_shoulder = p_shoulder;
          m_variables = p_variables;
     }

     @Override
     public void initialize(){
          m_shoulder.setTargetAngle(Constants.ShoulderConstants.kReadyToIntakePosition);
     }

     @Override
     public boolean isFinished(){
          return true;
     }
}
