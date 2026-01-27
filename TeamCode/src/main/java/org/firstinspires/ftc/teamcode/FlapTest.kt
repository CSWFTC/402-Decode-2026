package org.firstinspires.ftc.teamcode

import com.bylazar.gamepad.PanelsGamepad.firstManager
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.helper.BallTransfer
import org.firstinspires.ftc.teamcode.helper.Hardware


@TeleOp(name = "Flap Test")
class FlapTest : LinearOpMode() {
    override fun runOpMode() {
        Hardware.init(hardwareMap)
        val gpIn2 = firstManager.asCombinedFTCGamepad(gamepad2)
        val bt = BallTransfer()

        waitForStart()
        if (isStopRequested) {
            return
        }

        telemetry.clear()


        while (opModeIsActive()) {
            bt.isLaunching = gpIn2.circle
            telemetry.addData("On", gpIn2.circle)
            telemetry.update()
            sleep(30)
        }
    }
}