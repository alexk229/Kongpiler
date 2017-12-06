import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
  
  	public static String someVal = "HelloWorld";
  	private static int someInt;
  	private static double someDouble;

	public static void main(String[] args) {
		
		while(someInt < 0) {
			someInt++;
		}
		someFunction(someVal, someInt);
	}
	
	public static String someFunction(String someString, int someInt) {
		return someVal;
	}
	
	public static int function(int num) {
		return num;
	}
	
	public static float functionFloat(float floater) {
		return floater;
	}

}