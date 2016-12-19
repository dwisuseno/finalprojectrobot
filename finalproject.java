import lejos.util.Delay;
import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;


public class Finalproject {
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
		
		// inisiasi sensor
		UltrasonicSensor sonicdepan = new UltrasonicSensor( SensorPort.S2);
		LightSensor light = new LightSensor(SensorPort.S3);
		UltrasonicSensor sonicsamping = new UltrasonicSensor( SensorPort.S4);
		
		int speed = 200;
		
		LCD.drawString("Press Any Key",1,1);
		
		Button.waitForAnyPress();
		LCD.clear(); 
		Motor.A.setSpeed(speed);
		Motor.C.setSpeed(speed);
		
		do {
			if(sonicsamping.getDistance() < 30){
				if(sonicdepan.getDistance() < 30){
					//Motor.A.rotate(-300) && Motor.C.rotate(-300);
					Motor.A.rotate(300);
					Motor.C.rotate(-300);
				} else {
					Motor.A.forward();
					Motor.C.forward();
				}
			}
		}while( Button.readButtons() != Button.ID_ESCAPE );
		
	}
}
