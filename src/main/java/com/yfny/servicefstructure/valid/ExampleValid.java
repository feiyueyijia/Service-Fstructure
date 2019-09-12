package com.yfny.servicefstructure.valid;

import com.yfny.servicefstructure.entity.ExampleEntity;
import com.yfny.servicefstructure.service.ExampleService;
import com.yfny.utilscommon.basemvc.common.BusinessException;
import com.yfny.utilscommon.basemvc.producer.BaseValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能结构管理场景示例对象属性验证
 * Author auto
 * Date  2019-08-21
 */
@Component
public class ExampleValid implements BaseValid<ExampleEntity> {

    @Autowired
    private ExampleService exampleService;

    public void validInsert(ExampleEntity entity) throws BusinessException {
        
    }

    public void validUpdate(ExampleEntity entity) throws BusinessException {

    }

    public void validDelete(ExampleEntity entity) throws BusinessException {

    }

    public void validSelect(ExampleEntity entity) throws BusinessException {

    }

}
