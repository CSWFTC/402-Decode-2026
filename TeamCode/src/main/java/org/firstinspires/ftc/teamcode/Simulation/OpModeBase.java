package org.firstinspires.ftc.teamcode.Simulation;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class OpModeBase extends LinearOpMode {
    @Override
    public void runOpMode(){
        Run(Constants.createFollower(hardwareMap));
    }

    public void Run(Follower follower){

    }
}
