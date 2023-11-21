package utilities;

import java.util.Random;

public class DynamicDataGenerator {


	public static int randomTwoDigitGenerator() {
		Random random=new Random();
		return random.nextInt(90) + 10;		
	}

}

