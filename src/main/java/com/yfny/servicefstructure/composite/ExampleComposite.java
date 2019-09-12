package com.yfny.servicefstructure.composite;

import com.yfny.servicefstructure.entity.ExampleEntity;
import com.yfny.servicefstructure.mapper.ExampleMapper;
import com.yfny.utilscommon.basemvc.producer.BaseComponent;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 功能结构管理场景示例对象Composite
 * Author auto
 * Date  2019-09-12
 */
@Component
public class ExampleComposite extends BaseComponent<ExampleEntity> {

    @Autowired
    private ExampleMapper exampleMapper;

    public static ExampleComposite exampleComposite;

    @PostConstruct
    public void init() {
        exampleComposite = this;
    }

    @Override
    public BaseMapper<ExampleEntity> getBaseMapper() {
        return exampleComposite.exampleMapper;
    }

}
