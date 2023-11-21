package com.dietician.backend.model;

import lombok.Data;

@Data
public class PatientInfo {
   String firstName;
   String lastName;
   String contactNumber;
   String email;
   String allergy;
   String foodCategory;
   String dateOfBirth;
   String dieticianId;
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getContactNumber() {
	return contactNumber;
}
public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAllergy() {
	return allergy;
}
public void setAllergy(String allergy) {
	this.allergy = allergy;
}
public String getFoodCategory() {
	return foodCategory;
}
public void setFoodCategory(String foodCategory) {
	this.foodCategory = foodCategory;
}
public String getDateOfBirth() {
	return dateOfBirth;
}
public void setDateOfBirth(String dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}
public String getDieticianId() {
	return dieticianId;
}
public void setDieticianId(String dieticianId) {
	this.dieticianId = dieticianId;
}
   
}
