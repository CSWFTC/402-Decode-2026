package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Driver Control RED")
public class DriverControlRed extends DriverControlBase {
    @Override
    AutonBase.Config getMatchingAuton() {
        return AutonRed.c;
    }
}
