package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Helper.Hardware;
import org.firstinspires.ftc.teamcode.Helper.Shooter;
import org.firstinspires.ftc.teamcode.PedroPathing.Constants;

enum State {
    LAUNCHING_PRELOAD,
    GOING_TO_PICKUP,
    PICKING_UP,
    GOING_TO_LAUNCH,
    LAUNCHING,
    LEAVE
}

public class AutonBase extends LinearOpMode {
    public static Pose launchRear;
    public static Pose launchForward;
    public static Pose pickup1;
    public static Pose pickup2;
    public static Pose pickup3;
    public static Pose pickupEndOffset;
    public static Pose endPosition;

    PathChain pathBetween(Follower f, Pose start, Pose end) {
        return f.pathBuilder().addPath(new BezierLine(start, end)).setLinearHeadingInterpolation(start.getHeading(), end.getHeading()).build();
    }

    Pose offset(Pose original) {
        return new Pose(original.getX() + pickupEndOffset.getX(), original.getY() + pickupEndOffset.getY(), original.getHeading());
    }

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap);
        Shooter shooter = new Shooter();
        State s = State.LAUNCHING_PRELOAD;
        Follower f = Constants.createFollower(hardwareMap);
        f.setStartingPose(launchRear);
        waitForStart();
        PathChain[] goTo = {pathBetween(f, launchRear, pickup1), pathBetween(f, launchRear, pickup2), pathBetween(f, launchForward, pickup3)};
        PathChain[] collect = {pathBetween(f, pickup1, offset(pickup1)), pathBetween(f, pickup2, offset(pickup2)), pathBetween(f, pickup3, offset(pickup3))};
        PathChain[] launch = {pathBetween(f, offset(pickup1), launchRear), pathBetween(f, offset(pickup2), launchForward), pathBetween(f, offset(pickup3), launchForward)};
        PathChain leave = pathBetween(f, launchForward, endPosition);
        int set = 0;
        shooter.SetOuttake(true); // TODO: may want to replace with continuously running outtake and instead toggling transfer
        while (opModeIsActive()) {
            f.update();
            switch (s) {
                case LAUNCHING_PRELOAD:
                    // TODO: Wait to be done launching
                    shooter.SetOuttake(false);
                    s = State.GOING_TO_PICKUP;
                    f.followPath(goTo[0]);
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
                    }
                    break;
                case LAUNCHING:
                    // TODO: Wait to be done launching
                    shooter.SetOuttake(false);
                    if (++set < 3) {
                        s = State.GOING_TO_PICKUP;
                        f.followPath(goTo[set]);
                    } else {
                        s = State.LEAVE;
                        f.followPath(leave, true);
                    }
                    break;
                case LEAVE:
                    // idk if we need to do anything here
                    break;
            }
        }
    }
}