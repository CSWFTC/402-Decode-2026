package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Helper.Hardware;


@TeleOp(name = "TurretMotorTest")
public class TurretMotorTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Hardware.TurretMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Hardware.TurretMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();
        while (opModeIsActive()) {

            Hardware.TurretMotor.setPower(gamepad1.a ? 1.0 : 0.0);
            Hardware.TurretMotor.setPower(gamepad1.b ? -1.0 : 0.0);

            telemetry.addLine().addData("Controls", "A for Forward, B for Backword");
            telemetry.addLine().addData("Forward", gamepad1.a );
            telemetry.addLine().addData("Backword", gamepad1.b );
            telemetry.update();


        }
    }
}
