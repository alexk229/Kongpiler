import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
  
  	String someVal = "HelloWorld";
  	private static int someInt;
  	private static double someDouble;

	public static void main(String[] args) {
		
		while(someInt < 0) {
			someInt++;
		}
	}
	
	public String method1(String someString, int someInt, float someFloat) {
		if(someInt == 0) {
			someVal = someString;
		}
		return someVal;
	}

}