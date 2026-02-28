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
    public static Config c = new AutonBase.Config(new Pose(61.599, 19.187), new Pose(86.269, 85.520),new Pose(58.928, 34.456), new Pose(58.928, 59.962),new Pose(58.928, 85.411),new Pose(-39.6157082749,0),new Pose(86.286, 85.499), new Pose2D(DistanceUnit.INCH,131.781, 133.961, AngleUnit.DEGREES, 20));
    @Override
    Config getConfig() {
        return c;
    }
}
