package xyz.izgnod.order.service;

import xyz.izgnod.wms_api.WmsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "wms-service")
public interface WmsService extends WmsApi {

}