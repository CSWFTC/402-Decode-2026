package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Helper.BallTransfer;
import org.firstinspires.ftc.teamcode.Helper.GamePad;
import org.firstinspires.ftc.teamcode.Helper.Hardware;

@TeleOp(name = "Flap Test")
public class FlapTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        GamePad gpIn1 = new GamePad(gamepad1);
        BallTransfer bt = new BallTransfer();

        double position = 1.0;
        //starting position = <1.0;
        //Final position = 0.73;
        Hardware.flapServo.setPosition(position);
        waitForStart();
        while (opModeIsActive()) {
            GamePad.GameplayInputType inpType1 = gpIn1.WaitForGamepadInput(30);
            switch (inpType1) {
                case DPAD_UP:
                    position += 0.01;
                    break;
                case DPAD_RIGHT:
                    position += 0.05;
                    break;
                case DPAD_DOWN:
                    position -= 0.01;
                    break;
                case DPAD_LEFT:
                    position -= 0.05;
                    break;
                case BUTTON_A:
                    Hardware.flapServo.setPosition(position);
                    break;
            }
            telemetry.addLine().addData("Servo Current Position", Hardware.flapServo.getPosition());
            telemetry.addLine().addData("Position To Go To", position);
            telemetry.update();
        }
    }
}