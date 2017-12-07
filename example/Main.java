import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Main {
  
  	public static String someVal = "HelloWorld";
  	public static String someString;
  	private static int someInt;
  	private static double someDouble;
  	private static float someFloat;
  	private ArrayList<String> doubleList;

	public static void main(String[] args) {
		
		someDouble = 13.2;
		
		someFloat = 13.3F;
		
		int num = 32;
		
		while(someInt < 13) {
			someInt++;
		}
		
		int index = 0;
		if(index == 0) {
			someInt++;
		}
		
		if (someDouble < 13.23) {
			someInt++;
		}
		
		someString = someFunction(someVal, someInt);
	}
	
	public static String someFunction(String someString, int someInt) {
		someString = "Hello";
		String aString = "World";
		int num = 32;
		double num2 = 42;
		System.out.println(someDouble);
		num2 = 1 % 2;
		return someVal;
	}
}