package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Helper.AutoAim;
import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Turret;

@TeleOp(name = "Auto-aim test")
public class AutoAimTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Turret t = new Turret();
        AutoAim aim = new AutoAim(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0), new Pose2D(DistanceUnit.INCH, 12, 12, AngleUnit.DEGREES, 0), t, true);
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Turret Angle", t.getTurretAngle());
            telemetry.addData("Estimated Position", aim.pos);
            aim.Update();
        }
    }
}
