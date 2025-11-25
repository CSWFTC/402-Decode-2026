package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.Helper.AprilTagConfig
import org.firstinspires.ftc.teamcode.Helper.DriveTrainV2
import org.firstinspires.ftc.teamcode.Helper.GamePad
import org.firstinspires.ftc.teamcode.Helper.GamePad.GameplayInputType
import org.firstinspires.ftc.teamcode.Helper.Hardware
import org.firstinspires.ftc.teamcode.Helper.Shooter
import java.text.SimpleDateFormat
import java.util.Locale


@TeleOp(name = "Driver Control", group = "Competition")
class DriverControl : LinearOpMode() {
    override fun runOpMode() {
        Hardware.init(hardwareMap)
        // Load Introduction and Wait for Start
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE)
        telemetry.addLine("Driver Control")
        telemetry.addData("Version Number", version)
        telemetry.addLine()
        telemetry.addData(">", "Press Start to Launch")
        telemetry.update()

        val gpIn1 = GamePad(gamepad1)
        val gpIn2 = GamePad(gamepad2)
        val shooter = Shooter()
        val drvTrain = DriveTrainV2()
        val atConf = AprilTagConfig()
        var setReversed = false

        waitForStart()
        if (isStopRequested) {
            return
        }

        telemetry.clear()

        var speedMultiplier = 0.5

        while (opModeIsActive()) {
            atConf.Update()

            val inpType1 = gpIn1.WaitForGamepadInput(30)
            when (inpType1) {
                GameplayInputType.BUTTON_BACK -> setReversed = !setReversed
                GameplayInputType.DPAD_DOWN -> speedMultiplier = 0.25
                GameplayInputType.DPAD_LEFT -> speedMultiplier = 0.75
                GameplayInputType.DPAD_RIGHT -> speedMultiplier = 0.5
                GameplayInputType.DPAD_UP -> speedMultiplier = 1.0
                GameplayInputType.JOYSTICK -> drvTrain.setDriveVectorFromJoystick(
                    gamepad1.left_stick_x * speedMultiplier.toFloat(),
                    gamepad1.right_stick_x * speedMultiplier.toFloat(),
                    gamepad1.left_stick_y * speedMultiplier.toFloat(), setReversed
                )

                else -> {}
            }

            val inpType2 = gpIn2.WaitForGamepadInput(30)
            when (inpType2) {
                GameplayInputType.BUTTON_A -> shooter.ToggleIntake()
                GameplayInputType.BUTTON_Y -> shooter.ToggleOuttake()
                else -> {}
            }
            telemetry.addLine("Gamepad #1")

            val inpTime1 = SimpleDateFormat(
                "yyyy.MM.dd HH:mm:ss.SSS",
                Locale.US
            ).format(gpIn1.telemetry_InputLastTimestamp)
            telemetry.addLine().addData("Current Speed Multiplier", speedMultiplier)
            telemetry.addLine()
            telemetry.addLine().addData("GP1 Time", inpTime1)
            telemetry.addLine().addData("GP1 Cnt", gpIn1.telemetry_InputCount)
            telemetry.addLine()
                .addData("GP1 Input", gpIn1.telemetry_InputLastType.toString())
            telemetry.addLine()
                .addData(
                    "Apriltag Status:",
                    if (atConf.order == null) "Not found yet" else atConf.order
                )
            telemetry.addLine()
            telemetry.update()
        }
    }

    companion object {
        private const val version = "1.1"
    }
}