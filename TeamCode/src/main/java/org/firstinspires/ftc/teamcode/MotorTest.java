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

import org.firstinspires.ftc.teamcode.Helper.Hardware;

// stolen from the ftc samples
@TeleOp(name = "Motor Test")
public class MotorTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Hardware.intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Hardware.intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.intake.setDirection(DcMotorSimple.Direction.FORWARD);
        /*
        Hardware.outtakeTop.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Hardware.outtakeTop.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.outtakeTop.setDirection(DcMotorSimple.Direction.FORWARD);
         */
        Hardware.shooter1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Hardware.shooter1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.shooter1.setDirection(DcMotorSimple.Direction.FORWARD);
        Hardware.shooter2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Hardware.shooter2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.shooter2.setDirection(DcMotorSimple.Direction.FORWARD);
        waitForStart();
        while (opModeIsActive()) {

            Hardware.intake.setPower(gamepad1.a || gamepad1.x ? 1.0 : 0.0);
            Hardware.shooter1.setPower(gamepad1.b || gamepad1.x ? 1.0 : 0.0);
            Hardware.shooter2.setPower(gamepad1.b || gamepad1.x ? 1.0 : 0.0);
            //Hardware.outtakeTop.setPower(gamepad1.y || gamepad1.x  ? 1.0 : 0.0);

            telemetry.addData("Controls", "A for intake, B for bottom, Y for top, X for all");
            telemetry.addLine();
            telemetry.addData("Intake", gamepad1.a || gamepad1.x);
            telemetry.addLine();
            telemetry.addData("Outtake ", gamepad1.b || gamepad1.x);
            telemetry.addLine();
            telemetry.addLine();
            telemetry.addData("Outtake Top", gamepad1.y || gamepad1.x);
            telemetry.addLine();
            telemetry.update();
        }
    }

}
