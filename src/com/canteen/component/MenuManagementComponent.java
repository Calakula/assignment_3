// Ravil Maratov add MenuManagementComponent
package com.canteen.component;

import com.canteen.entity.MenuItem;
import com.canteen.service.MenuService;

import java.sql.SQLException;
import java.util.List;

public class MenuManagementComponent {
    private final MenuService menuService;

    public MenuManagementComponent(MenuService menuService) {
        this.menuService = menuService;
    }

    public void addMenuItem(MenuItem item) throws SQLException {
        menuService.addMenuItem(item);
        System.out.println("Menu item added: " + item);
    }

    public void showAvailableItems() throws SQLException {
        List<MenuItem> items = menuService.getAvailableItems();
        items.forEach(System.out::println);
    }
}
