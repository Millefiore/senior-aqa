package com.violetta.aqa.ui.dto.user.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LoginDto(String email,
                       String password) {
}
