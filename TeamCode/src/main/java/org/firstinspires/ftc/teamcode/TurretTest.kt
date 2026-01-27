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
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.Helper.GamePad
import org.firstinspires.ftc.teamcode.Helper.GamePad.GameplayInputType
import org.firstinspires.ftc.teamcode.Helper.Hardware

@TeleOp(name = "Turret Test")
class TurretTest : LinearOpMode() {
    override fun runOpMode() {
        Hardware.init(hardwareMap)

        waitForStart()

        val gpIn1 = GamePad(gamepad1)

        var position = 0.5
        Hardware.turretServo.position = position
        Hardware.turretServo.direction = Servo.Direction.FORWARD

        while (opModeIsActive()) {
            val inpType1 = gpIn1.WaitForGamepadInput(30)
            when (inpType1) {
                GameplayInputType.DPAD_UP -> position += 0.05
                GameplayInputType.DPAD_RIGHT -> position += 0.01
                GameplayInputType.DPAD_DOWN -> position -= 0.05
                GameplayInputType.DPAD_LEFT -> position -= 0.01
                GameplayInputType.BUTTON_A -> Hardware.turretServo.position = position
                GameplayInputType.BUTTON_L_BUMPER -> Hardware.turretServo.direction =
                    Servo.Direction.REVERSE

                GameplayInputType.BUTTON_R_BUMPER -> Hardware.turretServo.direction =
                    Servo.Direction.REVERSE

                else -> {}
            }
            telemetry.addLine().addData("Position", position)
            telemetry.update()
        }
    }
}
