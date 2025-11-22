package org.firstinspires.ftc.teamcode.Helper;

public class Shooter {
    public boolean on;
    public boolean intakeOn;
    public boolean outtakeOn;
    public Shooter(){
        SetStatus(false);
        SetStatusIntake(false);
    }
    public void Toggle(){
        SetStatus(!on);
    }
    public void ToggleIntake() {
        SetStatusIntake(!intakeOn);
    }
    public void ToggleOuttake() {
        SetStatusOuttake(!outtakeOn);
    }
    public void SetStatus(boolean newStatus){
        on = newStatus;
        double power = on ? 1 : 0;
        Hardware.outtakeBottom.setPower(power);
        Hardware.outtakeTop.setPower(power);
        Hardware.intake.setPower(power);
    }

    public void SetStatusIntake(boolean newStatus){
        intakeOn = newStatus;
        double power = on ? 1 : 0;
        Hardware.intake.setPower(power);
    }

    public void SetStatusOuttake (boolean newStatus) {
        outtakeOn = newStatus;
        double power = on ? 1 : 0;
        Hardware.outtakeBottom.setPower(power);
        Hardware.outtakeTop.setPower(power);
    }
}
