package com.canteen.service;

import com.canteen.entity.MenuItem;
import com.canteen.exception.MenuItemNotAvailableException;
import com.canteen.repository.MenuItemRepository;

import java.sql.SQLException;
import java.util.List;

public class MenuService {
    private final MenuItemRepository menuItemRepository;

    public MenuService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public void addMenuItem(MenuItem item) throws SQLException {
        menuItemRepository.addMenuItem(item);
    }

    public void updateMenuItem(MenuItem item) throws SQLException {
        menuItemRepository.addMenuItem(item);
    }

    public List<MenuItem> getAvailableItems() throws SQLException {
        return menuItemRepository.findAll().stream()
                .filter(MenuItem::isAvailable)
                .toList();
    }

    public MenuItem getMenuItemById(int id) throws SQLException {
        MenuItem item = menuItemRepository.findById(id);
        if (item == null || !item.isAvailable()) {
            throw new MenuItemNotAvailableException("Menu item with ID " + id + " is not available.");
        }
        return item;
    }
}
