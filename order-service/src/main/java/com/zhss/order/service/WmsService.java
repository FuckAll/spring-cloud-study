package com.zhss.order.service;

import com.zhss.wms_api.WmsApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "wms-service")
public interface WmsService extends WmsApi {

}