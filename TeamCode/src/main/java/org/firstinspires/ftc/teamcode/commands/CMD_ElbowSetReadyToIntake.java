package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;

public class CMD_ElbowSetReadyToIntake extends CommandBase {
     SUB_Elbow m_elbow;
     GlobalVariables m_variables;
     public CMD_ElbowSetReadyToIntake(SUB_Elbow p_elbow, GlobalVariables p_variables){
          m_elbow = p_elbow;
          m_variables = p_variables;
     }

     @Override
     public void initialize(){
          m_elbow.setTargetAngle(Constants.ElbowConstants.kReadyToIntakePosition);
     }

     @Override
     public boolean isFinished(){
          return true;
     }
}
