package com.yfny.servicefstructure.fallback;

import com.yfny.servicefstructure.service.client.UserClient;
import org.springframework.stereotype.Component;

/**
 * Created by jisongZhou on 2019/9/5.
 **/
@Component
public class UserHystrix implements UserClient {
}
