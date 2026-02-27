package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Helper.AutoAim;
import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Shooter;
import org.firstinspires.ftc.teamcode.Helper.Turret;
import org.firstinspires.ftc.teamcode.PedroPathing.Constants;

enum State {
    LAUNCHING_PRELOAD,
    GOING_TO_PICKUP,
    PICKING_UP,
    GOING_TO_LAUNCH,
    LAUNCHING,
    LEAVE
}

@Configurable
public abstract class AutonBase extends LinearOpMode {
    public static double launchDelay = 3;

    abstract Config getConfig();

    PathChain pathBetween(Follower f, Pose start, Pose end) {
        return f.pathBuilder().addPath(new BezierLine(start, end)).setLinearHeadingInterpolation(start.getHeading(), end.getHeading()).build();
    }

    Pose offset(Pose original) {
        Config c = getConfig();
        return new Pose(original.getX() + c.pickupEndOffset.getX(), original.getY() + c.pickupEndOffset.getY(), original.getHeading());
    }

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Shooter shooter = new Shooter();
        State s = State.LAUNCHING_PRELOAD;
        Follower f = Constants.createFollower(hardwareMap);
        Config c = getConfig();
        AutoAim aa = new AutoAim(AutoAim.convertPedroPose(c.launchRear), c.aimTarget, new Turret(), false);
        f.setStartingPose(c.launchRear);
        waitForStart();
        PathChain[] goTo = {pathBetween(f, c.launchRear, c.pickup1), pathBetween(f, c.launchRear, c.pickup2), pathBetween(f, c.launchForward, c.pickup3)};
        PathChain[] collect = {pathBetween(f, c.pickup1, offset(c.pickup1)), pathBetween(f, c.pickup2, offset(c.pickup2)), pathBetween(f, c.pickup3, offset(c.pickup3))};
        PathChain[] launch = {pathBetween(f, offset(c.pickup1), c.launchRear), pathBetween(f, offset(c.pickup2), c.launchForward), pathBetween(f, offset(c.pickup3), c.launchForward)};
        PathChain leave = pathBetween(f, c.launchForward, c.endPosition);
        int set = 0;
        ElapsedTime timer = new ElapsedTime();
        shooter.SetOuttake(true); // TODO: may want to replace with continuously running outtake and instead toggling transfer
        while (opModeIsActive()) {
            f.update();
            aa.Update();
            switch (s) {
                case LAUNCHING_PRELOAD:
                    // TODO: May want to replace with monitoring outtake and/or transfer velocity
                    if (timer.seconds() > launchDelay) {
                        shooter.SetOuttake(false);
                        s = State.GOING_TO_PICKUP;
                        f.followPath(goTo[0]);
                    }
                    break;
                case GOING_TO_PICKUP:
                    if (!f.isBusy()) {
                        s = State.PICKING_UP;
                        shooter.SetIntake(true);
                        f.followPath(collect[set]);
                    }
                    break;
                case PICKING_UP:
                    if (!f.isBusy()) {
                        s = State.GOING_TO_LAUNCH;
                        shooter.SetIntake(false);
                        f.followPath(launch[set], true);
                    }
                    break;
                case GOING_TO_LAUNCH:
                    if (!f.isBusy()) {
                        shooter.SetOuttake(true);
                        s = State.LAUNCHING;
                        timer.reset();
                    }
                    break;
                case LAUNCHING:
                    if (timer.seconds() > launchDelay) {
                        shooter.SetOuttake(false);
                        if (++set < 3) {
                            s = State.GOING_TO_PICKUP;
                            f.followPath(goTo[set]);
                        } else {
                            s = State.LEAVE;
                            f.followPath(leave, true);
                        }
                    }
                    break;
                case LEAVE:
                    // idk if we need to do anything here
                    break;
            }
        }
    }

    public class Config {
        public Pose launchRear;
        public Pose launchForward;
        public Pose pickup1;
        public Pose pickup2;
        public Pose pickup3;
        public Pose pickupEndOffset;
        public Pose endPosition;
        public Pose2D aimTarget;
    }
}