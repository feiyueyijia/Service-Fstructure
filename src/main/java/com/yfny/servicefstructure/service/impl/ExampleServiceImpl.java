package com.yfny.servicefstructure.service.impl;

import com.yfny.servicefstructure.entity.ExampleEntity;
import com.yfny.servicefstructure.mapper.ExampleMapper;
import com.yfny.servicefstructure.service.ExampleService;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import com.yfny.utilscommon.basemvc.producer.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能结构管理场景示例对象ServiceImpl
 * Author auto
 * Date  2019-09-12
 */
@Service
public class ExampleServiceImpl extends BaseServiceImpl<ExampleEntity> implements ExampleService {

    @Autowired
    private ExampleMapper exampleMapper;

    @Override
    public BaseMapper<ExampleEntity> getBaseMapper() {
        return this.exampleMapper;
    }

}
