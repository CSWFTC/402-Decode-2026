package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.helper.Hardware

@TeleOp(name = "Drivetrain Motor Test")
class DrivetrainMotorTest : LinearOpMode() {
    override fun runOpMode() {
        Hardware.init(hardwareMap)
        Hardware.frontLeft.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        Hardware.frontRight.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        Hardware.rearLeft.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        Hardware.rearRight.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        /*Hardware.outtakeBottom.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.outtakeMiddle.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.outtakeTop.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);*/
        waitForStart()
        val old = com.qualcomm.robotcore.hardware.Gamepad()
        var fl = false
        var fr = false
        var rl = false
        var rr = false
        while (opModeIsActive()) {
            if (gamepad1.a && !old.a) rl = !rl
            if (gamepad1.b && !old.b) rr = !rr
            if (gamepad1.x && !old.x) fl = !fl
            if (gamepad1.y && !old.y) fr = !fr
            gamepad1.copy(old)
            Hardware.frontLeft.power = if (fl) 1.0 else 0.0
            Hardware.frontRight.power = if (fr) 1.0 else 0.0
            Hardware.rearLeft.power = if (rl) 1.0 else 0.0
            Hardware.rearRight.power = if (rr) 1.0 else 0.0
            /*Hardware.outtakeTop.setPower(fl ? 1 : 0);
            Hardware.outtakeMiddle.setPower(fr ? 1 : 0);
            Hardware.outtakeBottom.setPower(rl ? 1 : 0);
            Hardware.intake.setPower(rr ? 1 : 0);*/
            telemetry.addData("Rear Left (A)", rl)
            telemetry.addLine()
            telemetry.addData("Rear Right (B)", rr)
            telemetry.addLine()
            telemetry.addData("Front Left (X)", fl)
            telemetry.addLine()
            telemetry.addData("Front Right (Y)", fr)
            telemetry.addLine()
            telemetry.update()
        }
    }
}
