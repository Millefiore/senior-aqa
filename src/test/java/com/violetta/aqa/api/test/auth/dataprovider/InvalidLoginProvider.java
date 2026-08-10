package com.violetta.aqa.api.test.auth.dataprovider;

// {"email": "eve.holt@reqres.in", "password": "cityslicka"}

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.violetta.aqa.api.dto.auth.request.LoginDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import net.datafaker.Faker;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@RequiredArgsConstructor
@SuperBuilder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvalidLoginProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        Faker faker = new Faker();
        return Stream.generate(() -> LoginDto.builder()
                        .email(faker.internet().emailAddress())
                        .password(faker.internet().password())
                        .build())
                .limit(3)
                .map(Arguments::of);
    }
}
