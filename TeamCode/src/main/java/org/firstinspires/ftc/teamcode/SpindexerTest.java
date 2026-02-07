package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Helper.BallColorDetector;
import org.firstinspires.ftc.teamcode.Helper.BallTransfer;
import org.firstinspires.ftc.teamcode.Helper.GamePad;
import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Shooter;
import org.firstinspires.ftc.teamcode.Helper.Spindexer;

@TeleOp(name = "Spindexer Test")
public class SpindexerTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Shooter shooter = new Shooter();
        Spindexer spin = new Spindexer(shooter, new BallColorDetector(), new BallTransfer());
        GamePad gpIn1 = new GamePad(gamepad1);
        waitForStart();
        while (opModeIsActive()) {
            switch (gpIn1.WaitForGamepadInput(30)) {
                case BUTTON_A:
                    spin.nextPickupLocation();
                    break;
                case BUTTON_Y:
                    spin.nextShootingLocation();
                    break;
                case BUTTON_B:
                    Hardware.intake.setPower(0.5);
                    break;
                case BUTTON_X:
                    Hardware.intake.setPower(0);
                    break;
                case BUTTON_L_BUMPER:
                    Hardware.flapServo.setPosition(1.0);
                    break;
                case BUTTON_R_BUMPER:
                    Hardware.flapServo.setPosition(0.83);
                    break;
                case LEFT_TRIGGER:
                    shooter.SetOuttake(false);
                    break;
                case RIGHT_TRIGGER:
                    shooter.SetOuttake(true);
                    break;
                case DPAD_UP:
                    spin.nextPickupLocation();
                    break;
                case DPAD_DOWN:
                    spin.nextShootingLocation();
                    break;
            }

            telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE);
            telemetry.addLine().addData("Directions", "DPAD_UP for next pickup location; DPAD_DOWN for next shooting position; B & X for Intake; Bumpers for flap; Trigger for outtake");
            telemetry.addLine().addData("Current Position", Hardware.spindex.getCurrentPosition());
            telemetry.addLine().addData("Target Position", Hardware.spindex.getTargetPosition());
            telemetry.update();
        }
    }
}
