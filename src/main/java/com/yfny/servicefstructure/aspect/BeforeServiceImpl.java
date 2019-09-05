package com.yfny.servicefstructure.aspect;

import com.yfny.utilscommon.basemvc.producer.BeforeBaseServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by jisongZhou on 2019/9/5.
 **/
@Aspect
@Component
public class BeforeServiceImpl extends BeforeBaseServiceImpl {

    @Override
    public String getPackageName() {
        return "com.yfny.servicefstructure.";
    }
    
}
