package ru.job4j.ood.lsp.foodstorage.quality;

import ru.job4j.ood.lsp.foodstorage.products.Product;
import ru.job4j.ood.lsp.foodstorage.store.Shop;
import ru.job4j.ood.lsp.foodstorage.store.Store;
import ru.job4j.ood.lsp.foodstorage.store.Trash;
import ru.job4j.ood.lsp.foodstorage.store.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControlQuality {
    private final ExpirationCalculator<LocalDate> expirationCalculator = new LocalDateExpirationCalculator();
    private final Store warehouse = new Warehouse(expirationCalculator);
    private final Store shop = new Shop(expirationCalculator);
    private final Store trash = new Trash(expirationCalculator);
    private final List<Store> storages;

    public ControlQuality() {
        this.storages = Arrays.asList(warehouse, shop, trash);
    }

    public List<Product> getWarehouseProducts() {
        return new ArrayList<>(warehouse.getAll());
    }

    public List<Product> getShopProducts() {
        return new ArrayList<>(shop.getAll());
    }

    public List<Product> getTrashProducts() {
        return new ArrayList<>(trash.getAll());
    }

    public void sendToStorage(Product product) {
        for (Store store : storages) {
            if (store.add(product)) {
                break;
            }
        }
    }
}
