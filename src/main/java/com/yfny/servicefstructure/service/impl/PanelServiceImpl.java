package com.yfny.servicefstructure.service.impl;

import com.yfny.servicefstructure.entity.PanelEntity;
import com.yfny.servicefstructure.mapper.PanelMapper;
import com.yfny.servicefstructure.service.PanelService;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import com.yfny.utilscommon.basemvc.producer.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能结构管理场景面板对象ServiceImpl
 * Author auto
 * Date  2019-09-05
 */
@Service
public class PanelServiceImpl extends BaseServiceImpl<PanelEntity> implements PanelService {

    @Autowired
    private PanelMapper panelMapper;

    @Override
    public BaseMapper<PanelEntity> getBaseMapper() {
        return this.panelMapper;
    }

}
