package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Shooter;

@TeleOp(name = "Driver Control")
public class DriverControl extends LinearOpMode {
    @Override
    public void runOpMode(){
        Hardware.init(hardwareMap);
        Shooter shooter = new Shooter();
        waitForStart();
        Gamepad old = new Gamepad();
        double strafe = 0, forward = 0, rotate = 0;
        long lastJoystickTime = 0;
        while(opModeIsActive()){
            if(System.currentTimeMillis() > lastJoystickTime + 20){
                boolean newPosition = (gamepad1.left_stick_x != strafe) ||
                        (gamepad1.left_stick_y != forward) ||
                        (gamepad1.right_stick_x != rotate);

                // Check for Joystick at Rest
                boolean atRest = (gamepad1.left_stick_x == 0f) && (gamepad1.left_stick_y == 0f) &&
                        (gamepad1.right_stick_x == 0f);
                if (newPosition || !atRest) {
                    lastJoystickTime = System.currentTimeMillis();
                    strafe = gamepad1.left_stick_x;
                    forward = gamepad1.left_stick_y;
                    rotate = gamepad1.right_stick_x;
                }
            }
            double scale = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(rotate), 1);
            Hardware.frontLeft.setPower((forward + strafe + rotate) / scale);
            Hardware.frontRight.setPower((forward - strafe - rotate) / scale);
            Hardware.rearLeft.setPower((forward - strafe + rotate) / scale);
            Hardware.rearRight.setPower((forward + strafe - rotate) / scale);
            if(gamepad1.a && !old.a)
                shooter.Toggle();
            gamepad1.copy(old);
        }
    }
}
