package org.firstinspires.ftc.teamcode.Helper;

public class Shooter {
    public boolean on;
    public boolean intakeOn;
    public boolean outtakeOn;
    public boolean slowOuttake;
    public Shooter(){
        SetStatus(false);
        SetStatusIntake(false);
        slowOuttake = false;
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
    public void setSlowOuttake() {
        slowOuttake = !slowOuttake;
    }
    public void SetStatus(boolean newStatus){
        on = newStatus;
        double power = on ? 1 : 0;
        double outtakePower = 1.0;
        if (power == 1) {
            if (slowOuttake) {
                outtakePower = 0.5;
            }
        } else {
            outtakePower = 0;
        }
        Hardware.outtakeBottom.setPower(outtakePower);
        Hardware.outtakeTop.setPower(outtakePower);
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
        double outtakePower = 1.0;
        if (power == 1) {
            if (slowOuttake) {
                outtakePower = 0.5;
            }
        } else {
            outtakePower = 0;
        }
        Hardware.outtakeBottom.setPower(outtakePower);
        Hardware.outtakeTop.setPower(outtakePower);
    }
}
