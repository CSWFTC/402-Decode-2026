package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.Simulation.OpModeBase;

public class StraightLineTest extends OpModeBase {
    @Override
    public void Run(Follower follower) {
        follower.followPath(follower.pathBuilder().addPath(new BezierLine(new Pose(0, 0), new Pose(0, 12))).build());
    }
}
