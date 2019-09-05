package com.yfny.servicefstructure.composite;

import com.yfny.servicefstructure.entity.FunctionEntity;
import com.yfny.servicefstructure.mapper.FunctionMapper;
import com.yfny.utilscommon.basemvc.producer.BaseComponent;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 功能结构管理功能对象Composite
 * Author auto
 * Date  2019-09-05
 */
@Component
public class FunctionComposite extends BaseComponent<FunctionEntity> {

    @Autowired
    private FunctionMapper functionMapper;

    public static FunctionComposite functionComposite;

    @PostConstruct
    public void init() {
        functionComposite = this;
    }

    @Override
    public BaseMapper<FunctionEntity> getBaseMapper() {
        return functionComposite.functionMapper;
    }

}
