package org.firstinspires.ftc.teamcode.Helper;

public class Sorter {
    public static double sorterPower = 1.0;
    public static double currentPosition = 0.0;

    public Sorter() {
        currentPosition = Hardware.sorter.getCurrentPosition();
    }

    public void changeSorterPower(double change) {
        sorterPower += change;
        setSorterPower(Sorter.sorterPower);
    }

    public void setSorterPower(double power) {
        Hardware.sorter.setPower(power);
    }
}
