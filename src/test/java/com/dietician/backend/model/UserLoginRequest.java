package com.dietician.backend.model;

import lombok.Data;

/*import lombok.Data;
*/
@Data
public class UserLoginRequest {
    String userLoginEmail;
    String password;
    
	public String getUserLoginEmail() {
		return userLoginEmail;
	}
	public void setUserLoginEmail(String userLoginEmail) {
		this.userLoginEmail = userLoginEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
