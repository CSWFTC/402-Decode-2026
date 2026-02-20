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
            double hoodAngle = t.getHoodAngle();
            double turretAngle = t.getTurretAngle();
            long now = System.currentTimeMillis();
            double delta = (double) (now - prev) / 1e3;
            telemetry.addData("Current hood angle", hoodAngle);
            telemetry.addData("Current turret angle", turretAngle);
            telemetry.update();
            t.setHoodAngle(hoodAngle + gamepad1.right_stick_y * delta / (Turret.hoodMaxAngle - Turret.hoodMinAngle));
            t.setTurretAngle(turretAngle + gamepad1.left_stick_x * delta / (Turret.turretMaxAngle - Turret.turretMinAngle));
            prev = now;
        }
    }
}
