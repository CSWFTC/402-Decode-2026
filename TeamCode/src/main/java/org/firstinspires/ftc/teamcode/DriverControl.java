package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Helper.Hardware;

@TeleOp(name = "Driver Control")
public class DriverControl extends LinearOpMode {
    @Override
    public void runOpMode(){
        Hardware.init(hardwareMap);
        Hardware.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();
        while(opModeIsActive()){
            double forward = gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;
            double scale = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(rotate), 1);
            Hardware.frontLeft.setPower((forward + strafe + rotate) / scale);
            Hardware.frontRight.setPower((forward - strafe - rotate) / scale);
            Hardware.rearLeft.setPower((forward - strafe + rotate) / scale);
            Hardware.rearRight.setPower((forward + strafe - rotate) / scale);
        }
    }
}
