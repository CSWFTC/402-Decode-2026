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

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Helper.GamePad;
import org.firstinspires.ftc.teamcode.Helper.Hardware;


@TeleOp(name = "Intake Test")
public class IntakeTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Hardware.intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Hardware.intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        GamePad gpIn1 = new GamePad(gamepad1);
        double power = 0.0;

        Hardware.intake.setPower(power);

        while (opModeIsActive()) {

            GamePad.GameplayInputType inpType1 = gpIn1.WaitForGamepadInput(30);
            switch (inpType1) {
                case BUTTON_A:
                    power = 1.0;
                    break;
                case BUTTON_B:
                    power = 0.75;
                    break;
                case BUTTON_X:
                    power = 0.5;
                    break;
                case BUTTON_Y:
                    power = 0.25;
                    break;
                case BUTTON_L_BUMPER:
                    Hardware.intake.setDirection(DcMotorSimple.Direction.REVERSE);
                    break;
                case BUTTON_R_BUMPER:
                    Hardware.intake.setDirection(DcMotorSimple.Direction.FORWARD);
                    break;
                case DPAD_DOWN:
                    power = 0.0;
            }

            Hardware.intake.setPower(power);
            telemetry.addLine().addData("Current Power", Hardware.intake.getPower());
            telemetry.addLine().addData("Direction", Hardware.intake.getDirection());
            telemetry.update();
        }
    }
}