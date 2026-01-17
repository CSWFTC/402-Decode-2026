package org.firstinspires.ftc.teamcode;


import com.bylazar.gamepad.PanelsGamepad;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Helper.BallTransfer;
import org.firstinspires.ftc.teamcode.Helper.Hardware;

@TeleOp(name = "Flap Test")
public class FlapTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Gamepad gpIn2 = PanelsGamepad.INSTANCE.getFirstManager().asCombinedFTCGamepad(gamepad2);
        BallTransfer bt = new BallTransfer();

        double position = 0.71;

        //final position = 0.40;
        //starting position = 0.71;
        Hardware.flapServo.setPosition(position);

        waitForStart();
        if (isStopRequested()) {
            return;
        }

        telemetry.clear();


        while (opModeIsActive()) {
            /*bt.SetLaunching(gpIn2.circle);
            telemetry.addData("On", gpIn2.circle);
            telemetry.update();*/
            position = 0.40;
            Hardware.flapServo.setPosition(position);
            telemetry.addLine().addData("Position", position);
            telemetry.update();
        }
    }
}