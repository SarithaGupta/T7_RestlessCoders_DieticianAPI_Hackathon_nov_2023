package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class ConfigReaderAndWriter {
	
	
	private Properties prop;
	private FileInputStream ip;
	
	RunTimeDataReader runTimeData;
	String propertiesFilePath;
	
	
	public Properties initProp() {

		runTimeData = new RunTimeDataReader();
		String envFromTest = runTimeData.getEnvFromTestNG();
		 
		if (envFromTest!= null) {
			switch (envFromTest.toLowerCase()) {
			
			case "dev":
				propertiesFilePath = "./src/test/resources/config/dev_config.properties";
				break;
				
			case "qa":
				propertiesFilePath = "./src/test/resources/config/qa_config.properties";
				break;
			
			case "uat":
				propertiesFilePath = "./src/test/resources/config/uat_config.properties";
				break;
				
			case "stage":
				propertiesFilePath = "./src/test/resources/config/stage_config.properties";
				break;
				
			case "integration":
				propertiesFilePath = "./src/test/resources/config/integration_config.properties";
				break;
				
			case "prod":
				propertiesFilePath = "./src/test/resources/config/prod_config.properties";
				break;	
			}
		}else
			propertiesFilePath = "./src/test/resources/config.properties"; //./src/test/resources/config/qa_config.properties


		prop = new Properties();
		try {
			ip = new FileInputStream(propertiesFilePath);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
