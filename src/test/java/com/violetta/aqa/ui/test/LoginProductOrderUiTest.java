package com.violetta.aqa.ui.test;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.violetta.aqa.ui.dto.user.request.LoginDto;
import com.violetta.aqa.ui.page.CartPage;
import com.violetta.aqa.ui.page.InventoryPage;
import com.violetta.aqa.ui.page.LoginPage;
import com.violetta.aqa.ui.page.component.HeaderComponent;
import com.violetta.aqa.ui.page.component.InventoryProductComponent;
import com.violetta.aqa.ui.page.component.SidebarComponent;
import io.qameta.allure.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.awaitility.Awaitility.await;

@Tags({@Tag("ui"), @Tag("auth"), @Tag("product"), @Tag("order")})
@Issue("KAN-1")
@Epic("Order API")
@Feature("Order Max Price and Min Price products")
@Severity(SeverityLevel.CRITICAL)
@FieldDefaults(level = AccessLevel.PROTECTED)
public class LoginProductOrderUiTest extends BaseUiTest {

    @Story("Order Max Price and Min Price products")
    @DisplayName("Order Max Price and Min Price products")
    @Test
    public void orderMinAndMaxPriceProducts() {
        LoginPage loginPage = new LoginPage(page)
                .open();
        InventoryPage inventoryPage = loginPage
                .loginValid(new LoginDto(loginPage.getRandomUsernameVariant(), loginPage.getRandomPasswordVariant()));

        HeaderComponent header = inventoryPage.header();
        Allure.step("Verify header title is 'Products'", () -> PlaywrightAssertions.assertThat(header.title()).containsText("Products"));

        Allure.step("Verify sidebar is opening and has 'Logout' button", () -> {
                    SidebarComponent sidebarComponent = header.menuClick();
                    PlaywrightAssertions.assertThat(sidebarComponent.getLogout()).isVisible();
                    sidebarComponent.closeClick();
                }
        );

        inventoryPage.sortBy("lohi");
        Allure.step("Verify product cart is sorted by selected option", () -> {
            await().atMost(Duration.ofSeconds(5)).untilAsserted(() -> {
                List<InventoryProductComponent> products = inventoryPage.getProducts();
                Assertions.assertThat(products)
                        .isNotEmpty()
                        .hasSize(6)
                        .isSortedAccordingTo(Comparator.comparingDouble(InventoryProductComponent::getPrice));
            });
        });

        List<InventoryProductComponent> products = inventoryPage.getProducts();
        InventoryProductComponent minPriceProduct = products.get(0);
        InventoryProductComponent maxPriceProduct = products.get(products.size() - 1);

        minPriceProduct.addToCart();
        maxPriceProduct.addToCart();
        Allure.step("Verify product amount is 2 in cart icon", () -> assertThat(header.getCart()).hasText("2"));


        CartPage cartPage = header.cartClick();
        Allure.step("Verify product amount is 2 in cart page", () -> assertThat(cartPage.getCartProducts()).hasCount(2));

        cartPage.checkoutClick();
    }

}
