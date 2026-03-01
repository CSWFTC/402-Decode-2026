package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Helper.Hardware;

@Autonomous(name = "Move Forward and Left")
public class JustMoveLeft extends LinearOpMode {
    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Hardware.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();
        Hardware.frontRight.setPower(1);
        Hardware.rearRight.setPower(1);
        sleep(500);
        Hardware.frontRight.setPower(0);
        Hardware.rearRight.setPower(0);
    }
}
