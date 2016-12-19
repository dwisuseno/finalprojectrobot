import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.util.Delay;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
public class finalproject {
public static void turnLeft(){
		Motor.C.backward();
		Motor.A.backward();
		try {Thread.sleep(700); } catch (Exception e) {}
		Motor.A.stop();
		Motor.C.stop();
		Motor.A.rotate(340);
	}
	public static void turnRight(){
		Motor.C.rotate(300);
		Motor.C.forward();
		Motor.A.forward();
		try {Thread.sleep(1500); } catch (Exception e) {}
	}
	

	public static void main(String[] args) 
		throws Exception {
		int backward=1;
		DifferentialPilot pilot = new DifferentialPilot(2.25f, 5.5f, Motor.C, Motor.A, true);
		pilot.setRotateSpeed(10);
		
		TouchSensor touch = new TouchSensor(SensorPort.S2);

		UltrasonicSensor sonic = new UltrasonicSensor( SensorPort.S3);

		int jarak = sonic.getDistance();
		int distance_in_cm = 30;
		int flag_turn = 0;
		int speed = 200;
		
		LCD.drawString("Press Any Key",1,1);
		
		Button.waitForAnyPress();
		LCD.clear(); 
		Motor.A.setSpeed(speed);
		Motor.C.setSpeed(speed);
		
		do {
			if(sonic.getDistance() < 30 ){
				if(touch.isPressed() == true){
					flag_turn = 1;
					if(flag_turn == 1){
						Motor.A.stop();
						Motor.C.stop();
						turnLeft();
						flag_turn = 0;
					} else {
						Motor.A.forward();
						Motor.C.forward();
					}
				} else {
					Motor.A.forward();
					Motor.C.forward();
				}
				
				
			} else if(sonic.getDistance() > 60 ){
				flag_turn = 1;
				Motor.A.stop();
				Motor.C.stop();
				if(touch.isPressed() == true && flag_turn == 0){
					if(flag_turn == 1){
						turnRight();
						flag_turn = 0;
						Motor.A.stop();
						Motor.C.stop();
					} else {
						Motor.A.forward();
						Motor.C.forward();
					}
				} else if(touch.isPressed() == false){
					if(flag_turn == 1){
						turnRight();
						flag_turn = 0;
						Motor.A.stop();
						Motor.C.stop();
					} else {
						Motor.A.forward();
						Motor.C.forward();
					}
				} else {
					Motor.A.forward();
					Motor.C.forward();
				}
			}
			
			 
		}
		while( Button.readButtons() != Button.ID_ESCAPE );
	}

}
