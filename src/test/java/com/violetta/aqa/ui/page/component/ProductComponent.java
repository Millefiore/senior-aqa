package com.violetta.aqa.ui.page.component;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ProductComponent {
    Locator title;
    Locator price;
    Locator removeFromCartButton;

    public ProductComponent(Locator root) {
        this.title = root.getByTestId("inventory-item-name");
        this.price = root.getByTestId("inventory-item-price");
        this.removeFromCartButton = root.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("Add to cart"));
    }

    public String getTitleText() {
        return title.innerText();
    }

    public double getPrice() {
        String priceText = price.innerText().replace("$", "").trim();
        return Double.parseDouble(priceText);
    }

    public void removeFromCart() {
        removeFromCartButton.click();
    }
}
