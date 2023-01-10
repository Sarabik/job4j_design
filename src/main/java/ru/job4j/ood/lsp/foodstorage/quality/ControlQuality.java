package ru.job4j.ood.lsp.foodstorage.quality;

import ru.job4j.ood.lsp.foodstorage.products.Product;
import ru.job4j.ood.lsp.foodstorage.store.Shop;
import ru.job4j.ood.lsp.foodstorage.store.Store;
import ru.job4j.ood.lsp.foodstorage.store.Trash;
import ru.job4j.ood.lsp.foodstorage.store.Warehouse;

import java.util.Arrays;
import java.util.List;

public class ControlQuality {
    private final Store warehouse = new Warehouse();
    private final Store shop = new Shop();
    private final Store trash = new Trash();
    private final List<Store> storages;

    public ControlQuality() {
        this.storages = Arrays.asList(warehouse, shop, trash);
    }

    public List<Product> getWarehouseProducts() {
        return warehouse.getAll();
    }

    public List<Product> getShopProducts() {
        return shop.getAll();
    }

    public List<Product> getTrashProducts() {
        return trash.getAll();
    }

    public void sendToStorage(Product product) {
        double quality = QualityPercent.getQualityPercent(product);
        for (Store store : storages) {
            if (store.add(product, quality)) {
                break;
            }
        }
    }
}
