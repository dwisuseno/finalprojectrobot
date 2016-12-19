import lejos.util.Delay;
import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

 

public class Finalproject {
	public static int status = 0;
	
	public static void turnLeft(){
		status = 1;
//		Motor.C.backward();
//		Motor.A.backward();
//		try {Thread.sleep(700); } catch (Exception e) {}
		Motor.A.stop();
		Motor.C.stop();
		Motor.A.rotate(380);
		Motor.A.forward();
		Motor.C.forward();
		try {Thread.sleep(1000); } catch (Exception e) {}
	}
	
	public static void turnRight(){
		Motor.C.rotate(300);
		Motor.C.forward();
		Motor.A.forward();
		try {Thread.sleep(1000); } catch (Exception e) {}
	}
	
	public static void turnAtret(){
		Motor.A.stop();
		Motor.C.stop();
		Motor.A.rotate(-380);
		Motor.A.forward();
		Motor.C.forward();
		try {Thread.sleep(2500); } catch (Exception e) {}
	}
	
	public static void main(String[] args) 
			throws Exception {
		
		// inisiasi sensor
		UltrasonicSensor sonicdepan = new UltrasonicSensor( SensorPort.S3);
		LightSensor light = new LightSensor(SensorPort.S4);
		UltrasonicSensor sonicsamping = new UltrasonicSensor( SensorPort.S1);
		
		int speed = 200;
		
		LCD.drawString("Press Any Key",1,1);
		
		Button.waitForAnyPress();
		LCD.clear(); 
		Motor.A.setSpeed(speed);
		Motor.C.setSpeed(speed);
		
		LCD.drawInt(light.readValue(), 3, 9, 0);
		
		do {
			if(sonicsamping.getDistance() < 30){
				if(sonicdepan.getDistance() < 10 ){
					Motor.A.stop();
					Motor.C.stop();
					if(status == 1){
						Motor.A.stop();
						Motor.C.stop();
					} else {
						turnRight();
					}
				} else {
					if(light.readValue() < 30 ){
						status = 0;
						Motor.A.stop();
						Motor.C.stop();
						Motor.C.backward();
						Motor.A.backward();
						try {Thread.sleep(1900); } catch (Exception e) {}
						Motor.A.stop();
						Motor.C.stop();
						turnAtret();
					} else {
						Motor.A.forward();
						Motor.C.forward();
					}
				}
			} else {
				turnLeft();	
			} 
		}while( Button.readButtons() != Button.ID_ESCAPE );
		
	}
}
