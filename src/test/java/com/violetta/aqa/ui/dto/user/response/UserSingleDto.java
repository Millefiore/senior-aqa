package com.violetta.aqa.ui.dto.user.response;

//"id": 7,
//      "email": "michael.lawson@reqres.in",
//      "first_name": "Michael",
//      "last_name": "Lawson",
//      "avatar": "https://reqres.in/img/faces/7-image.jpg"

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.violetta.aqa.ui.dto.user.request.LoginDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserSingleDto(LoginDto data) {
}
