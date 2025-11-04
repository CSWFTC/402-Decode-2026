package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

// singleton for easy access to hardware peripherals
public class Hardware {
    // you can add more peripherals here
    // just declare a new field
    // the field name should match the name in the robot config
    // that's it!
//    public DcMotor frontLeft;
//    public DcMotor frontRight;
//    public DcMotor rearLeft;
//    public DcMotor rearRight;
    public static WebcamName camera;
    public static DcMotor intake;
    public static DcMotor outtakeTop;
    public static DcMotor outtakeBottom;
    // initialization code
    public static void init (HardwareMap map) {
        Field[] fields = Hardware.class.getDeclaredFields();
        for(Field field: fields){
            if(Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())){
                field.setAccessible(true);
                try {
                    field.set(null, map.get(field.getType(), field.getName()));
                }
                catch (IllegalAccessException ignored){
                    // maybe add something here
                }
            }
        }
    }
}
