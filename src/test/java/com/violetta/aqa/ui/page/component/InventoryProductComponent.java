package com.violetta.aqa.ui.page.component;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class InventoryProductComponent extends ProductComponent {
    Locator addToCartButton;

    public InventoryProductComponent(Locator root) {
        super(root);
        this.addToCartButton = root.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Add to cart"));
    }

    @Step("Add product to cart")
    public void addToCart() {
        addToCartButton.click();
    }

}
