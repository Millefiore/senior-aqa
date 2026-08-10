package com.violetta.aqa.api.test.user;

import com.violetta.aqa.api.client.user.UserApiClient;
import com.violetta.aqa.api.dto.user.request.UserDto;
import com.violetta.aqa.api.dto.user.response.UserPageDto;
import com.violetta.aqa.api.dto.user.response.UserSingleDto;
import com.violetta.aqa.api.test.user.dataprovider.ValidUserProvider;
import io.qameta.allure.*;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@Tags({@Tag("api"), @Tag("user")})
@Issue("KAN-1")
@Epic("User Management API")
@Feature("User CRUD Operations")
@Severity(SeverityLevel.NORMAL)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserApiTest {

    UserApiClient userApiClient = new UserApiClient();

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @Story("Get users")
    @DisplayName("Get users validPageable return 200 and user list")
    public void getUsers_validPageable_return200AndUserList(int page) {
        UserPageDto usersResponse = userApiClient.getUsersPage(page);
        Allure.step("Verify user details", () -> {
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
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    @Story("Get user by id")
    @DisplayName("Get user by id and returns 200 and valid user")
    public void getUserById_validRequest_return200AndValidUser(int id) {
        UserSingleDto user = userApiClient.getUser(id);

        Allure.step("Verify user details", () -> {
            assertThat(user.data()).isNotNull()
                    .satisfies(userDto -> {
                        Allure.step("Verify user id", () -> assertThat(userDto.getId()).isNotNull().isEqualTo(id));
                        Allure.step("Verify user email not empty", () -> assertThat(userDto.getEmail()).isNotEmpty());
                        Allure.step("Verify user firstname not empty", () -> assertThat(userDto.getFirstName()).isNotEmpty());
                        Allure.step("Verify user lastname not empty", () -> assertThat(userDto.getLastName()).isNotEmpty());
                        Allure.step("Verify user avatar not empty", () -> assertThat(userDto.getAvatar()).isNotNull());
                    });
        });
    }

    @ParameterizedTest
    @ArgumentsSource(ValidUserProvider.class)
    @Story("Create user")
    @DisplayName("Create user by valid body and returns 200 and valid user")
    public void createUser_validRequest_return201AndValidUser(UserDto userEntity) {
        UserDto user = userApiClient.createUser(userEntity);

        Allure.step("Verify user details", () -> {
            assertThat(user).isNotNull()
                    .satisfies(userDto -> {
                        assertThat(userDto.getId()).isNotNull();
                        assertThat(userDto.getFirstName()).isNotNull().isEqualTo(userEntity.getFirstName());
                        assertThat(userDto.getLastName()).isNotNull().isEqualTo(userEntity.getLastName());
                        assertThat(userDto.getAvatar()).isNotNull().isEqualTo(userEntity.getAvatar());
                        assertThat(userDto.getEmail()).isNotNull().isEqualTo(userEntity.getEmail());
                    });
        });
    }
}
