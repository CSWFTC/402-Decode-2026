package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Hardware {
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor rearLeft;
    public DcMotor rearRight;
    public WebcamName camera;
    public static Hardware instance;
    private Hardware(){}
    public static void init (HardwareMap map) {
        if(instance != null)
            return;
        instance = new Hardware();
        Field[] fields = Hardware.class.getDeclaredFields();
        for(Field field: fields){
            if(Modifier.isPublic(field.getModifiers())){
                field.setAccessible(true);
                try {
                    field.set(instance, map.get(field.getType(), field.getName()));
                }
                catch (IllegalAccessException ignored){

                }
            }
        }
    }
}
