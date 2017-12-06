import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Main {
  
  	public static String someVal = "HelloWorld";
  	private static int someInt;
  	private static double someDouble;
  	private ArrayList<String> doubleList;

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
	
	public static int functionFloat(float floater, int integer) {
		return integer;
	}

}