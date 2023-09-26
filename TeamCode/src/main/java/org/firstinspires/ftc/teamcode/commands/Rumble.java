package org.firstinspires.ftc.teamcode.commands;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.teamcode.ftclib.gamepad.GamepadEx;

public class Rumble extends CommandBase {
    private final GamepadEx m_subsystem;
    double m_rumbleLeft;
    double m_rumbleRight; 
    int m_duration;

    public Rumble(GamepadEx subsystem, double rumbleLeft, double rumbleRight, int duration) {
        m_subsystem = subsystem;
        m_rumbleLeft = rumbleLeft;
        m_rumbleRight = rumbleRight;
        m_duration = duration;
        
        // Use addRequirements() here to declare subsystem dependencies.
    }

    @Override
    public void initialize() {
        m_subsystem.gamepad.rumble(m_rumbleLeft, m_rumbleRight, m_duration);
    }
    
    @Override
    public boolean isFinished() {
        return true;
    }  
}
