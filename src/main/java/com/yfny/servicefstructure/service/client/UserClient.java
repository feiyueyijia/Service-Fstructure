package com.yfny.servicefstructure.service.client;

import com.yfny.servicefstructure.fallback.UserHystrix;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by jisongZhou on 2019/9/5.
 **/
@FeignClient(value = "serviceuser", path = "/user", fallback = UserHystrix.class)
public interface UserClient {
    //获取单个用户信息
    //获取用户列表信息
}
