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
            double hoodPos = t.getHoodPosition();
            double turretPos = t.getTurretPosition();
            long now = System.currentTimeMillis();
            double delta = (double) (now - prev) / 1e3;
            telemetry.addData("Current hood position", hoodPos);
            telemetry.addData("Current turret position", turretPos);
            telemetry.update();
            t.setHoodPosition(hoodPos + gamepad1.right_stick_y * delta / (Turret.hoodMax - Turret.hoodMin));
            t.setTurretPosition(turretPos + gamepad1.left_stick_x * delta / (Turret.turretMax - Turret.turretMin));
            prev = now;
        }
    }
}
