package org.firstinspires.ftc.teamcode.Helper;

import com.bylazar.configurables.annotations.Configurable;

@Configurable
public class Shooter {
    public static double intakePower = 1;
    public static double outtakeTopPower = 0.5;
    public static double outtakeMiddlePower = 1;
    public static double outtakeBottomPower = 1;
    public boolean intakeOn;
    public boolean outtakeOn;
    public Shooter(){
        SetIntake(false);
        SetOuttake(false);
    }

    public void SetIntake(boolean status) {
        intakeOn = status;
        Hardware.intake.setPower(status ? intakePower : 0.0);
    }
    public void SetOuttake(boolean status) {
        outtakeOn = status;
        Hardware.outtakeBottom.setPower(status ? outtakeBottomPower : 0.0);
        Hardware.outtakeMiddle.setPower(status ? outtakeMiddlePower : 0.0);
        Hardware.outtakeTop.setPower(status ? outtakeTopPower : 0.0);
    }

    public void ToggleIntake() {
        SetIntake(!intakeOn);
    }
    public void ToggleOuttake() {
        SetOuttake(!outtakeOn);
    }
}
