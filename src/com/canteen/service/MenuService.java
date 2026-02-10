
package com.canteen.service;

import com.canteen.entity.MenuItem;
import com.canteen.repository.MenuItemRepository;

import java.util.List;

public class MenuService {
    private final MenuItemRepository menuRepo;

    public MenuService(MenuItemRepository menuRepo) {
        this.menuRepo = menuRepo;
    }

    public List<MenuItem> listAvailable() {
        return menuRepo.findAllAvailable();
    }

    public MenuItem getAvailableItem(int id) {
        return menuRepo.findById(id)
                .filter(MenuItem::isAvailable)
                .orElse(null);
    }
}
