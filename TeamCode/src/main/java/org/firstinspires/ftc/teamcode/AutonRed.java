package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Configurable
@TeleOp(name = "RED auton")
public class AutonRed extends AutonBase {
    public static Config c; // TODO: add definitions for all the values

    @Override
    Config getConfig() {
        return c;
    }
}
