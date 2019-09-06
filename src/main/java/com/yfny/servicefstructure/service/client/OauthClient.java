package com.yfny.servicefstructure.service.client;

import com.yfny.servicefstructure.fallback.UserHystrix;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by jisongZhou on 2019/9/5.
 **/
@FeignClient(value = "serviceoauth", path = "/oauth", fallback = UserHystrix.class)
public interface OauthClient {
    //用户身份认证
    //用户权限验证
}
