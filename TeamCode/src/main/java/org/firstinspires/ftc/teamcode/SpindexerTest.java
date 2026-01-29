package org.firstinspires.ftc.teamcode;


import com.bylazar.gamepad.PanelsGamepad;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Helper.GamePad;
import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Spindexer;

@TeleOp(name = "Spindexer Test")
public class SpindexerTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Spindexer s = new Spindexer();
        GamePad gpIn1 = new GamePad(gamepad1);
        waitForStart();
        while (opModeIsActive()) {
            switch (gpIn1.WaitForGamepadInput(30)) {
                case BUTTON_A:
                    s.PickupNextBall();
                    break;
                case BUTTON_Y:
                    s.LaunchNextBall();
                    break;
            }
        }
    }
}
