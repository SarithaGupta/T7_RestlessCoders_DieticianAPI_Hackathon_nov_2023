package utilities;

import java.io.IOException;
import java.util.Random;

public class DynamicNameGenerator {


    public String patientNameGenerator() throws IOException {

        String nameConstant = "RestlessCoders-";
        String dynamicSerialNumber = "";
        String dynamicName = "";
        for (int i = 0; i < 3; i++) {
            int serialNumber = Integer.parseInt(ConfigReaderAndWriter.loadConfig().getProperty("serialNumber"));
            dynamicSerialNumber = String.valueOf(serialNumber + 1);
            dynamicName = nameConstant + dynamicSerialNumber;

        }
        ConfigReaderAndWriter.storeConfig("serialNumber", dynamicSerialNumber);
        ConfigReaderAndWriter.storeConfig("dynamicSerialNumber", dynamicSerialNumber);
        ConfigReaderAndWriter.storeConfig("dynamicUserName", dynamicName);
        return dynamicName;
    }

   
    public Integer phoneNumberGenerator() throws IOException {
        Random rand = new Random();
        int countryCode = rand.nextInt(0,10000);
        int areaCode = rand.nextInt(0,10000);
        int phoneNumberDigits = rand.nextInt(0,1000000000);

        int phoneNumber = countryCode+areaCode+phoneNumberDigits;
        System.out.println(phoneNumber);
        ConfigReaderAndWriter.storeConfig("phoneNumber", String.valueOf(phoneNumber));
        return phoneNumber;
    }



}

