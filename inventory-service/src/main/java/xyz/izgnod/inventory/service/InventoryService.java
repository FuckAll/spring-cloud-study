package xyz.izgnod.inventory.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.izgnod.inventory.api.InventoryApi;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
public class InventoryService implements InventoryApi {

    static class Request {
        public Long productId;
        public Long stock;
        public CompletableFuture<String> future;

        public Request(Long productId, Long stock, CompletableFuture<String> future) {
            this.productId = productId;
            this.stock = stock;
            this.future = future;
        }
    }

    private final LinkedBlockingDeque<Request> queue = new LinkedBlockingDeque<>();
    private final AtomicInteger concurrent = new AtomicInteger(0);

    public String deductStocks(Long productId, Long stock) {
        System.out.println("对商品【productId=" + productId + "】扣减库存：" + stock);
//        throw new RuntimeException("fail");
        return "{'msg': 'success'}";
    }

    // 并发高的场景，采用合并请求的方式
    @Override
    public String deductStock(@PathVariable("productId") Long productId,
                              @PathVariable("stock") Long stock) {
        try {
            if (concurrent.incrementAndGet() < 3) {
                return deductStocks(productId, stock);
            }
            CompletableFuture<String> future = new CompletableFuture<>();
            Request request = new Request(productId, stock, future);
            queue.put(request);
            return future.get(300, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            concurrent.decrementAndGet();
        }
        return null;
    }

    @PostConstruct
    public void init() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            try {
                // 减少线程空循环带来的消耗
                List<Request> rls = new ArrayList<>();
                rls.add(queue.take());
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    rls.add(queue.take());
                }
                Map<Long, Set<Request>> gb = rls.stream().collect(Collectors.groupingBy(v -> v.productId, Collectors.toSet()));
                gb.forEach((k, v) -> {
                    long sum = v.stream().mapToLong(r -> r.stock).sum();
                    // 可能扣减库存不够，需要退化成单个扣减过程
                    String s = this.deductStocks(k, sum);
                    v.forEach(r -> r.future.complete(s));
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 200, 100, TimeUnit.MILLISECONDS);
    }
}