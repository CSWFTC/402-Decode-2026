package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Helper.GamePad;
import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Sorter;

@TeleOp(name = "SorterTest")
public class SorterTest extends LinearOpMode {
    @Override
    public void runOpMode(){
        Hardware.init(hardwareMap);
        Hardware.sorter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Sorter sorter = new Sorter();
        GamePad gpIn1 = new GamePad(gamepad1);

        telemetry.clear();

        waitForStart();

        while(opModeIsActive()){
            Sorter.currentPosition = Hardware.sorter.getCurrentPosition();
            update_telemetry(Sorter.currentPosition);

            GamePad.GameplayInputType inpType1 = gpIn1.WaitForGamepadInput(30);
            switch (inpType1) {
                case BUTTON_L_BUMPER:
                    sorter.changeSorterPower(-0.05);
                    break;
                case BUTTON_R_BUMPER:
                    sorter.changeSorterPower(0.05);
                    break;
                case BUTTON_A:
                    sorter.setSorterPower(1.0);
                    break;
                case BUTTON_X:
                    sorter.setSorterPower(0.0);
                    break;
            }
        }
    }

    private void update_telemetry(double position) {
        telemetry.addLine().addData("OpMode", "SorterTest");
        telemetry.addLine();
        telemetry.addLine().addData("Current Position", position);
    }
}
