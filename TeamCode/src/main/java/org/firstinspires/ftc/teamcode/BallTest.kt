package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Gamepad
import org.firstinspires.ftc.teamcode.Helper.Hardware

@TeleOp(name = "BallTest")
class BallTest : LinearOpMode() {
    override fun runOpMode() {
        Hardware.init(hardwareMap)

        Hardware.outtake.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        Hardware.intake.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        waitForStart()
        val old = Gamepad()
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
            //Hardware.frontLeft.setPower(fl ? 1 : 0);
            //Hardware.frontRight.setPower(fr ? 1 : 0);
            //Hardware.rearLeft.setPower(rl ? 1 : 0);
            // Hardware.rearRight.setPower(rr ? 1 : 0);
            Hardware.outtake.power = (if (fl) 1 else 0).toDouble()
            Hardware.intake.power = (if (rr) 1 else 0).toDouble()
            telemetry.addData("Outtake Bottom (A)", rl)
            telemetry.addLine()
            telemetry.addData("Intake (B)", rr)
            telemetry.addLine()
            telemetry.addData("Outtake Top (X)", fl)
            telemetry.addLine()
            telemetry.addData("Outtake Middle (Y)", fr)
            telemetry.addLine()
            telemetry.update()
        }
    }
}
