package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Turret;

@TeleOp(name = "Turret Test")
public class TurretTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Turret t = new Turret();
        waitForStart();
        long prev = System.currentTimeMillis();
        while (opModeIsActive()) {
            double pos = t.getHoodPosition();
            long now = System.currentTimeMillis();
            double delta = (double) (now - prev) / 1e3;
            telemetry.addData("Current turret position", pos);
            telemetry.update();
            t.setHoodPosition(pos + gamepad1.right_stick_y * delta / (Turret.hoodMax - Turret.hoodMin));
            prev = now;
        }
    }
}
