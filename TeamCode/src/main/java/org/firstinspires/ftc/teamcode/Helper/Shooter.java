package org.firstinspires.ftc.teamcode.Helper;

public class Shooter {
    public boolean on;
    public Shooter(){
        SetStatus(false);
    }
    public void Toggle(){
        SetStatus(!on);
    }
    public void SetStatus(boolean newStatus){
        on = newStatus;
        double power = on ? 1 : 0;
        Hardware.outtakeBottom.setPower(power);
        //Hardware.outtakeTop.setPower(power);
        Hardware.intake.setPower(power);
    }
}
