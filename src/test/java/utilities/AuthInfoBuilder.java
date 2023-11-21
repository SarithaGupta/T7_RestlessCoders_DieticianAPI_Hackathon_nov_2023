package utilities;

import com.dietician.backend.model.UserLoginRequest;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class AuthInfoBuilder {

    ConfigReaderAndWriter configReaderObj;
    Properties prop;
    public AuthInfoBuilder() {
        configReaderObj = new ConfigReaderAndWriter();
        prop = configReaderObj.initProp();
    }

    public String getAuthInformation(){
        String apiEndPointUri=prop.getProperty("BASE_URL").concat("/").concat("login");
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUserLoginEmail(prop.getProperty("email"));
        userLoginRequest.setPassword(prop.getProperty("password"));
        Gson gson = new Gson();
        String request = gson.toJson(userLoginRequest);
        Response response = given().body(request).contentType("application/JSON").post(apiEndPointUri);
        JsonPath jpath = new JsonPath(response.getBody().asString());
        return jpath.getString("token");
    }
}
