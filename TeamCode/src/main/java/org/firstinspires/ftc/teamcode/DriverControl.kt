package org.firstinspires.ftc.teamcode

import com.bylazar.gamepad.PanelsGamepad.firstManager
import com.bylazar.gamepad.PanelsGamepad.secondManager
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.helper.BallTransfer
import org.firstinspires.ftc.teamcode.helper.DriveTrainV2
import org.firstinspires.ftc.teamcode.helper.GamePad
import org.firstinspires.ftc.teamcode.helper.GamePad.GameplayInputType
import org.firstinspires.ftc.teamcode.helper.Hardware
import org.firstinspires.ftc.teamcode.helper.Shooter
import java.text.SimpleDateFormat
import java.util.Locale


@TeleOp(name = "Driver Control", group = "Competition!!")
class DriverControl : LinearOpMode() {
    private var setReversed = false

    //    private AprilTagConfig atConf;
    override fun runOpMode() {
        Hardware.init(hardwareMap)
        // Load Introduction and Wait for Start
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE)
        telemetry.addLine("Driver Control")
        telemetry.addLine()
        telemetry.addData(">", "Press Start to Launch")
        telemetry.update()

        val gpIn1 = GamePad(firstManager.asCombinedFTCGamepad(gamepad1))
        val gpIn2 = GamePad(secondManager.asCombinedFTCGamepad(gamepad2))
        val shooter = Shooter()
        val drvTrain = DriveTrainV2()
        val bt = BallTransfer()

        //        atConf = new AprilTagConfig();
        waitForStart()
        if (isStopRequested) {
            return
        }

        telemetry.clear()

        var speedMultiplier = 0.5

        while (opModeIsActive()) {
//            atConf.Update();
            telemetry.addLine("Gamepad #1")

            val inpTime1 = SimpleDateFormat(
                "yyyy.MM.dd HH:mm:ss.SSS",
                Locale.US
            ).format(gpIn1.telemetry_InputLastTimestamp)
            telemetry.addLine().addData("Current Speed Multiplier", speedMultiplier)
            telemetry.addLine()
            telemetry.addLine().addData("GP1 Time", inpTime1)
            telemetry.addLine().addData("GP1 Cnt", gpIn1.telemetry_InputCount)
            telemetry.addLine().addData("GP1 Input", gpIn1.telemetry_InputLastType.toString())
            //        telemetry.addLine().addData("Apriltag Status:", atConf.order.map(Enum::toString).orElse("Not Found Yet"));
            telemetry.addLine()
            telemetry.addLine()
                .addData("Current Top Outtake Power Multiplier", Shooter.outtakePower)
            telemetry.addLine()
            telemetry.update()

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
                GameplayInputType.BUTTON_A -> shooter.toggleIntake()
                GameplayInputType.BUTTON_Y -> shooter.toggleOuttake()
                GameplayInputType.BUTTON_B -> bt.toggleLaunch()
                GameplayInputType.BUTTON_R_BUMPER -> shooter.increaseTopOuttakePower(0.05)
                GameplayInputType.BUTTON_L_BUMPER -> shooter.decreaseTopOuttakePower(0.05)
                GameplayInputType.DPAD_UP -> shooter.setOuttakeTopPowerMultiplier(1.00)
                GameplayInputType.DPAD_DOWN -> shooter.setOuttakeTopPowerMultiplier(0.25)
                GameplayInputType.DPAD_RIGHT -> shooter.setOuttakeTopPowerMultiplier(0.75)
                GameplayInputType.DPAD_LEFT -> shooter.setOuttakeTopPowerMultiplier(0.50)
                else -> {}
            }
        }
    }
}