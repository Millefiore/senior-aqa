package com.violetta.aqa.test;

import com.violetta.aqa.client.UserApiClient;
import com.violetta.aqa.dto.user.response.UserPageDto;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("User API")
@Feature("Get users")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserApiTest {

    UserApiClient userApiClient = new UserApiClient();

    @ParameterizedTest
    @Story("Get users")
    @ValueSource(ints = {1, 2})
    @DisplayName("Get users validPageable return 200 and user list")
    public void getUsers_validPageable_return200AndUserList(int page) {
        UserPageDto usersResponse = userApiClient.getUsersPage(page);

        assertThat(usersResponse).isNotNull();
        assertThat(usersResponse.page()).isEqualTo(page);
        assertThat(usersResponse.data()).isNotEmpty()
                .allSatisfy(userDto -> {
                    assertThat(userDto.getId()).isNotNull();
                    assertThat(userDto.getEmail()).isNotEmpty();
                    assertThat(userDto.getFirstName()).isNotEmpty();
                    assertThat(userDto.getLastName()).isNotEmpty();
                    assertThat(userDto.getAvatar()).isNotNull();
                });
    }
}
