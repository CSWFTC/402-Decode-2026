package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.helper.BallTransfer
import org.firstinspires.ftc.teamcode.helper.GamePad
import org.firstinspires.ftc.teamcode.helper.Hardware

@TeleOp(name = "Flap Test")
class FlapTest : LinearOpMode() {
    override fun runOpMode() {
        Hardware.init(hardwareMap)
        val gpIn1 = GamePad(gamepad1)
        BallTransfer()

        var position = 0.73

        //final position = 0.73;
        //starting position = <1.0;
        val inpType1 = gpIn1.WaitForGamepadInput(30)
        when (inpType1) {
            GamePad.GameplayInputType.DPAD_UP -> position += 0.01
            GamePad.GameplayInputType.DPAD_RIGHT -> position += 0.05
            GamePad.GameplayInputType.DPAD_DOWN -> position -= 0.01
            GamePad.GameplayInputType.DPAD_LEFT -> position -= 0.05
            GamePad.GameplayInputType.BUTTON_A -> Hardware.flapServo.position = position
            else -> {}
        }
        telemetry.addLine().addData("Servo Current Position", Hardware.flapServo.position)
        telemetry.addLine().addData("Position To Go To", position)
        telemetry.update()
    }
}