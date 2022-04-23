package com.zhss.order.service;

import com.zhss.credit.api.CreditApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "credit-service")
public interface CreditService extends CreditApi {

}