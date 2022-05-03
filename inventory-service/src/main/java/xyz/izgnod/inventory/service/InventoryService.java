package xyz.izgnod.inventory.service;

import xyz.izgnod.inventory.api.InventoryApi;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryService implements InventoryApi {

    @Override
    public String deductStock(@PathVariable("productId") Long productId,
                              @PathVariable("stock") Long stock) {
        System.out.println("对商品【productId=" + productId + "】扣减库存：" + stock);
        throw new RuntimeException("fail");
//        return "{'msg': 'success'}";
    }

}