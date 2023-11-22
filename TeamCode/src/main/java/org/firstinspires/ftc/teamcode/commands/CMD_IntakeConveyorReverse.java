package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Intake;

public class CMD_IntakeConveyorReverse extends CommandBase {
     SUB_Intake m_intake;
     public CMD_IntakeConveyorReverse(SUB_Intake p_intake){
          m_intake = p_intake;
     }

     @Override
     public void initialize(){
          m_intake.conveyorReverse();
     }

     @Override
     public boolean isFinished(){
          return true;
     }
}
