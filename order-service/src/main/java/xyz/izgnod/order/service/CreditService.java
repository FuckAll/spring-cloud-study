package xyz.izgnod.order.service;

import xyz.izgnod.credit.api.CreditApi;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author izgnod
 */
@FeignClient(value = "credit-service")
public interface CreditService extends CreditApi {

}