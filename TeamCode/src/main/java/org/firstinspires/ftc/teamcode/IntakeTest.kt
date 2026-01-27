/* Copyright (c) 2023 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.teamcode.helper.GamePad
import org.firstinspires.ftc.teamcode.helper.Hardware

@TeleOp(name = "Intake Test")
class IntakeTest : LinearOpMode() {
    override fun runOpMode() {
        Hardware.init(hardwareMap)
        Hardware.intake.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        Hardware.intake.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

        waitForStart()
        val gpIn1 = GamePad(gamepad1)
        var power = 0.0

        Hardware.intake.power = power

        while (opModeIsActive()) {
            val inpType1 = gpIn1.WaitForGamepadInput(30)
            when (inpType1) {
                GamePad.GameplayInputType.BUTTON_A -> power = 1.0
                GamePad.GameplayInputType.BUTTON_B -> power = 0.75
                GamePad.GameplayInputType.BUTTON_X -> power = 0.5
                GamePad.GameplayInputType.BUTTON_Y -> power = 0.25
                GamePad.GameplayInputType.BUTTON_L_BUMPER -> Hardware.intake.direction =
                    DcMotorSimple.Direction.REVERSE

                GamePad.GameplayInputType.BUTTON_R_BUMPER -> Hardware.intake.direction =
                    DcMotorSimple.Direction.FORWARD

                GamePad.GameplayInputType.DPAD_DOWN -> power = 0.0
                else -> {}
            }

            Hardware.intake.power = power
            telemetry.addLine().addData("Current Power", Hardware.intake.power)
            telemetry.addLine().addData("Direction", Hardware.intake.direction)
            telemetry.update()
        }
    }
}