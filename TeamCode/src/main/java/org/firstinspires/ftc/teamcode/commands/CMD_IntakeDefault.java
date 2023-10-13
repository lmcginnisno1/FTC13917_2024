package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.ftclib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Intake;

public class CMD_IntakeDefault extends CommandBase {
     SUB_Intake m_intake;
     GamepadEx m_driverOp;
     public CMD_IntakeDefault(SUB_Intake p_intake, GamepadEx p_driverOP) {
          m_intake = p_intake;
          m_driverOp = p_driverOP;
          addRequirements(m_intake);
     }

     @Override
     public void execute() {
          m_intake.setPower(m_driverOp.getRightY() * -1);
     }
}
