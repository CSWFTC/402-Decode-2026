package org.firstinspires.ftc.teamcode.Simulation;

import com.pedropathing.Drivetrain;
import com.pedropathing.math.Vector;

public class SimDrivetrain extends Drivetrain {
    double xVel = 0, yVel = 0;
    @Override
    public double[] calculateDrive(Vector correctivePower, Vector headingPower, Vector pathingPower, double robotHeading) {
        return new double[0];
    }

    @Override
    public void updateConstants() {
        // unused
    }

    @Override
    public void breakFollowing() {

    }

    @Override
    public void runDrive(double[] drivePowers) {

    }

    @Override
    public void startTeleopDrive() {
        System.out.println("Starting");
    }

    @Override
    public void startTeleopDrive(boolean brakeMode) {
        startTeleopDrive();
    }

    @Override
    public double xVelocity() {
        return xVel;
    }

    @Override
    public double yVelocity() {
        return yVel;
    }

    @Override
    public void setXVelocity(double xMovement) {
        xVel = xMovement;
    }

    @Override
    public void setYVelocity(double yMovement) {
        yVel = yMovement;
    }

    @Override
    public double getVoltage() {
        return 6.7; // unused
    }

    @Override
    public String debugString() {
        return "nothing to see here"; // unused
    }
}
