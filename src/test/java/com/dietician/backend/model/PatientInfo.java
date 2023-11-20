package com.dietician.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
}
