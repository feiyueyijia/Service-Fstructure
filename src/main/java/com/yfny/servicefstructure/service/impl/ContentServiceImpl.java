package com.yfny.servicefstructure.service.impl;

import com.yfny.servicefstructure.entity.ContentEntity;
import com.yfny.servicefstructure.mapper.ContentMapper;
import com.yfny.servicefstructure.service.ContentService;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import com.yfny.utilscommon.basemvc.producer.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能结构管理场景内容对象ServiceImpl
 * Author auto
 * Date  2019-09-12
 */
@Service
public class ContentServiceImpl extends BaseServiceImpl<ContentEntity> implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public BaseMapper<ContentEntity> getBaseMapper() {
        return this.contentMapper;
    }

}
