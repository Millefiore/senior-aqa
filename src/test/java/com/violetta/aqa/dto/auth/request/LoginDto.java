package com.violetta.aqa.dto.auth.request;

//"id": 7,
//      "email": "michael.lawson@reqres.in",
//      "first_name": "Michael",
//      "last_name": "Lawson",
//      "avatar": "https://reqres.in/img/faces/7-image.jpg"

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.violetta.aqa.dto.BaseDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@RequiredArgsConstructor
@SuperBuilder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDto {

    String email;

    String password;

}
