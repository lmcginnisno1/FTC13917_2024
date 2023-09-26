package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.ftclib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;

public class ShoulderSetPowerDefault extends CommandBase {
     SUB_Shoulder m_shoulder;
     GamepadEx m_driverOp;

     public ShoulderSetPowerDefault(SUB_Shoulder p_shoulder, GamepadEx p_driverOp){
          m_shoulder = p_shoulder;
          m_driverOp = p_driverOp;

          addRequirements(m_shoulder);
     }

     @Override
     public void execute(){
          m_shoulder.setPower(m_driverOp.getLeftY() * m_driverOp.getLeftY() * m_driverOp.getLeftY());
     }

     @Override
     public boolean isFinished(){
          return false;
     }

}
