package com.violetta.aqa.ui.page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.violetta.aqa.ui.page.component.InventoryProductComponent;
import com.violetta.aqa.ui.page.component.ProductComponent;
import io.qameta.allure.Step;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class InventoryPage extends BasePage {
    final Locator sortSelect;

    public InventoryPage(Page page) {
        super(page);
        sortSelect = page.getByRole(AriaRole.COMBOBOX);
    }

    @Step("Sort products by {0}")
    public InventoryPage sortBy(String option) {
        sortSelect.selectOption(option);
        return this;
    }

    @Step("Get products cards")
    public List<InventoryProductComponent> getProducts() {
        return page.getByTestId("inventory-item").all().stream().map(InventoryProductComponent::new).collect(Collectors.toList());
    }

}
