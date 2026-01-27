package org.firstinspires.ftc.teamcode

import com.bylazar.gamepad.PanelsGamepad.firstManager
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.helper.GamePad
import org.firstinspires.ftc.teamcode.helper.GamePad.GameplayInputType
import org.firstinspires.ftc.teamcode.helper.Hardware
import org.firstinspires.ftc.teamcode.helper.Spindexer


@TeleOp(name = "Spindexer Test")
class SpindexerTest : LinearOpMode() {
    override fun runOpMode() {
        Hardware.init(hardwareMap)
        val s = Spindexer()
        val gpIn1 = GamePad(firstManager.asCombinedFTCGamepad(gamepad1))
        waitForStart()
        while (opModeIsActive()) {
            when (gpIn1.WaitForGamepadInput(30)) {
                GameplayInputType.BUTTON_A -> s.pickupNextBall()
                GameplayInputType.BUTTON_Y -> s.launchNextBall()
                else -> {}
            }
        }
    }
}
