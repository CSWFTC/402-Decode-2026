package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Spindexer;

@TeleOp(name = "Spindexer Test")
public class SpindexerTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Spindexer s = new Spindexer();
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a)
                s.SetPosition(0);
            if (gamepad1.b)
                s.SetPosition(1);
            if (gamepad1.x)
                s.SetPosition(2);
        }
    }
}
