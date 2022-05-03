package xyz.izgnod.order.service;

import xyz.izgnod.inventory.api.InventoryApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "inventory-service")
public interface InventoryService extends InventoryApi {

    default String test(Long productId, Long stock) {
        System.out.println("能够通过实现默认方法对接口进行封装");
        return this.deductStock(productId, stock);
    }
}