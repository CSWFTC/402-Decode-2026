package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Driver Control BLUE")
public class DriverControlBlue extends DriverControlBase {
    @Override
    AutonBase.Config getMatchingAuton() {
        return AutonBlue.c;
    }
}
