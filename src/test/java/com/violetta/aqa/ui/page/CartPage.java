package com.violetta.aqa.ui.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.violetta.aqa.ui.page.component.ProductComponent;
import io.qameta.allure.Step;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CartPage extends BasePage {
    Locator cartList;
    Locator checkoutButton;

    public CartPage(Page page) {
        super(page);
        cartList = page.getByTestId("cart-list");
        checkoutButton = page.getByTestId("checkout");
    }

    public List<ProductComponent> getProducts() {
        return cartList.locator("div").getByTestId("inventory-item").all().stream().map(ProductComponent::new).collect(Collectors.toList());
    }

    @Step("Get cart products")
    public Locator getCartProducts() {
        return cartList.getByTestId("inventory-item");
    }

    @Step("Click checkout button")
    public CheckoutPage checkoutClick() {
        checkoutButton.click();
        return new CheckoutPage(page);
    }

}
