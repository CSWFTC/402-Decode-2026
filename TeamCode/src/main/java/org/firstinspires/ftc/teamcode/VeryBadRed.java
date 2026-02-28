package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Shooter;

@Autonomous(name = "VERY BAD RED AUTON")
public class VeryBadRed extends LinearOpMode {

    @Override
    public void runOpMode() {
        Shooter s = new Shooter();
        Hardware.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();
        s.SetOuttake(true);
        Hardware.frontRight.setPower(-1);
        Hardware.rearRight.setPower(-1);
        Hardware.frontLeft.setPower(-1);
        Hardware.rearLeft.setPower(-1);
        sleep(250);
        Hardware.frontRight.setPower(0);
        Hardware.rearRight.setPower(0);
        Hardware.frontLeft.setPower(0);
        Hardware.rearLeft.setPower(0);
        s.SetRamp(true);
        sleep(10000);
        s.SetOuttake(false);
        s.SetRamp(false);
        Hardware.frontRight.setPower(-1);
        Hardware.rearRight.setPower(-1);
        sleep(100);
        Hardware.frontLeft.setPower(-1);
        Hardware.rearLeft.setPower(-1);
        sleep(250);
        Hardware.frontRight.setPower(0);
        Hardware.frontLeft.setPower(0);
        Hardware.rearLeft.setPower(0);
        Hardware.rearLeft.setPower(0);
    }
}
