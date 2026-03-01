package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Helper.Hardware;

@Autonomous(name = "Move Forward and Right")
public class JustMoveRight extends LinearOpMode {
    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Hardware.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hardware.rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();
        Hardware.frontLeft.setPower(1);
        Hardware.rearLeft.setPower(1);
        sleep(500);
        Hardware.frontLeft.setPower(0);
        Hardware.rearLeft.setPower(0);
    }
}
