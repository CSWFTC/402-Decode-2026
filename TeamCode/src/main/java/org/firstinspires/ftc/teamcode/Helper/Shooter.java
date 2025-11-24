package org.firstinspires.ftc.teamcode.Helper;

public class Shooter {
    public boolean intakeOn;
    public boolean outtakeOn;
    public Shooter(){
        SetIntake(false);
        SetOuttake(false);
    }

    private void SetIntake(boolean status) {
        intakeOn = status;
        Hardware.intake.setPower(status ? 1.0 : 0.0);
    }
    private void SetOuttake(boolean status) {
        outtakeOn = status;
        Hardware.outtakeBottom.setPower(status ? 1.0 : 0.0);
        Hardware.outtakeMiddle.setPower(status ? 1.0 : 0.0);
        Hardware.outtakeTop.setPower(status ? 1.0 : 0.0);
    }

    public void ToggleIntake() {
        SetIntake(!intakeOn);
    }
    public void ToggleOuttake() {
        SetOuttake(!outtakeOn);
    }
}
