package xyz.izgnod.order.service;

import xyz.izgnod.credit.api.CreditApi;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "credit-service")
@RibbonClient
public interface CreditService extends CreditApi {

}