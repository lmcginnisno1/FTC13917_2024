package org.firstinspires.ftc.teamcode.commands.button;
import org.firstinspires.ftc.teamcode.ftclib.command.button.Button;
import org.firstinspires.ftc.teamcode.subsystems.TriggerSensorBase;

public class TRG_Sensor extends Button  {
    private final TriggerSensorBase m_triggerSensor;

    public TRG_Sensor(TriggerSensorBase p_triggerSensor) {
        m_triggerSensor = p_triggerSensor;
    }

    @Override
    public boolean get() {
        return (m_triggerSensor.getState());
    }
}
