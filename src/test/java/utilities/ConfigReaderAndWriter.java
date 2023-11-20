package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReaderAndWriter {
	
	
	static Properties prop;

	public Properties init_prop() {
    	
    	 System.out.println("executing LoadProperties....."); 
    	 try {
    		
    		/* System.out.println(System.getProperty("user.dir") +
    		 "//src//test//resources//config.properties");*/
    		 
    		 prop = new Properties();
    		 
    		 FileInputStream ip = new FileInputStream( System.getProperty("user.dir") +"\\src\\test\\resources\\config.properties"); 
    		 prop.load(ip);
    		 
    		 } catch (FileNotFoundException e) 
    	 				{ e.printStackTrace(); 
    	 	} catch (IOException e) 
    	 			{ e.printStackTrace(); } 
    	 
    	 return prop;
    	
    }

	/*
	 * public Properties loadConfig() {
	 * 
	 * 
	 * System.out.println("executing LoadProperties....."); try {
	 * 
	 * System.out.println(System.getProperty("user.dir") +
	 * "//src//test//resources//config.properties");
	 * 
	 * FileInputStream ip = new FileInputStream( System.getProperty("user.dir") +
	 * "\\src\\test\\resources\\config.properties"); props.load(ip);
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
	 * e) { e.printStackTrace(); } return props;
	 * 
	 * }
	 * 
	 * 
	 * public static void storeConfig(String key, String value) throws IOException {
	 * 
	 * props = loadConfig();
	 * 
	 * //Populating the properties file props.put(key, value); //Instantiating the
	 * FileInputStream for output file String path = System.getProperty("user.dir")
	 * + "//src//test//resources//config.properties"; FileOutputStream outputStream
	 * = new FileOutputStream(path); //Storing the properties file
	 * 
	 * props.store(outputStream, "This is a sample properties file");
	 * 
	 * System.out.println("Properties file created......"); }
	 */

}
