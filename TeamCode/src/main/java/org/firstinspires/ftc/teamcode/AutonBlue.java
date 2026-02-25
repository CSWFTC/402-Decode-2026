package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Configurable
@TeleOp(name = "BLUE Auton")
public class AutonBlue extends AutonBase {
    public static Config c; // TODO: add definitions for all the values

    @Override
    Config getConfig() {
        return c;
    }
}
