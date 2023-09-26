package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;

/**
 * An example command that uses an example subsystem.
 */
public class Sleep extends CommandBase {

    private ElapsedTime runtime;
    private boolean m_isFinished = false;
    private double m_Milliseconds=0;


    /**
    * Creates a new ExampleCommand.
    *
    * @param subsystem The subsystem used by this command.
    */
    public Sleep (double p_Milliseconds) {
        m_Milliseconds = p_Milliseconds;
    }

    @Override
    public void initialize() {

        runtime = new ElapsedTime();
        runtime.reset();
        m_isFinished = false;
    }


    @Override
    public void execute(){
        // m_isFinished = (m_Milliseconds<runtime.milliseconds());
    }

    @Override
    public boolean isFinished(){
        return (m_Milliseconds<runtime.milliseconds());
    }



}
