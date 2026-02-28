package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@Configurable
@TeleOp(name = "BLUE Auton")
public class AutonBlue extends AutonBase {
    public static Config c = new AutonBase.Config(new Pose(79.170, 18.581, 90),new Pose(81.997, 81.391),new Pose(84.623, 34.738),new Pose(84.623, 60.387), new Pose(84.623, 80.785),new Pose(42.6143057504,0 ),new Pose(81.795, 81.593), new Pose2D(DistanceUnit.INCH, 12.926, 135.921, AngleUnit.DEGREES, 60));
    @Override
    Config getConfig() {
        return c;
    }
}
