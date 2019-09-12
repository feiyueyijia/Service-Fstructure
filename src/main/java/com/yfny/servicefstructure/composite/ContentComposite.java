package com.yfny.servicefstructure.composite;

import com.yfny.servicefstructure.entity.ContentEntity;
import com.yfny.servicefstructure.mapper.ContentMapper;
import com.yfny.utilscommon.basemvc.producer.BaseComponent;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 功能结构管理场景内容对象Composite
 * Author auto
 * Date  2019-09-12
 */
@Component
public class ContentComposite extends BaseComponent<ContentEntity> {

    @Autowired
    private ContentMapper contentMapper;

    public static ContentComposite contentComposite;

    @PostConstruct
    public void init() {
        contentComposite = this;
    }

    @Override
    public BaseMapper<ContentEntity> getBaseMapper() {
        return contentComposite.contentMapper;
    }

}
