package org.firstinspires.ftc.teamcode.commands.button;
import org.firstinspires.ftc.teamcode.ftclib.command.button.Trigger;
// import org.firstinspires.ftc.teamcode.ftclib.command.SubsystemBase;
import org.firstinspires.ftc.teamcode.subsystems.TriggerSubsystemBase;

public class TRG_Subsystem extends Trigger {
    private final TriggerSubsystemBase m_triggerSubsystem;
    private final String m_state;
    
    public TRG_Subsystem(TriggerSubsystemBase p_subsystem, String p_state) {
        if (p_subsystem == null) {throw new RuntimeException();}
        m_triggerSubsystem = p_subsystem;
        m_state = p_state;
        
    }

    @Override
    public boolean get() {
        return (m_triggerSubsystem.getState(m_state));
    }
}
