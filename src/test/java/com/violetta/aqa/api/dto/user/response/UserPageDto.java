package com.violetta.aqa.api.dto.user.response;

//"id": 7,
//      "email": "michael.lawson@reqres.in",
//      "first_name": "Michael",
//      "last_name": "Lawson",
//      "avatar": "https://reqres.in/img/faces/7-image.jpg"

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.violetta.aqa.api.dto.user.request.UserDto;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserPageDto(int page,
                          @JsonProperty("per_page") int perPage,
                          int total,
                          @JsonProperty("total_pages") int totalPages,
                          List<UserDto> data){
}
