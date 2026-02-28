package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@Configurable
@TeleOp(name = "RED auton")
public class AutonRed extends AutonBase {
    //public static Config c = new Config(); // TODO: add definitions for all the values
    //THIS C IS INCORRECT, this is the values for AUTONRED, this needs to be changed
    public static Config c = new AutonBase.Config();
    @Override
    Config getConfig() {
        return c;
    }
}
