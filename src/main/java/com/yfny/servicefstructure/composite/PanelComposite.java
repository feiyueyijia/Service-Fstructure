package com.yfny.servicefstructure.composite;

import com.yfny.servicefstructure.entity.PanelEntity;
import com.yfny.servicefstructure.mapper.PanelMapper;
import com.yfny.utilscommon.basemvc.producer.BaseComponent;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 功能结构管理面板对象Composite
 * Author auto
 * Date  2019-09-10
 */
@Component
public class PanelComposite extends BaseComponent<PanelEntity> {

    @Autowired
    private PanelMapper panelMapper;

    public static PanelComposite panelComposite;

    @PostConstruct
    public void init() {
        panelComposite = this;
    }

    @Override
    public BaseMapper<PanelEntity> getBaseMapper() {
        return panelComposite.panelMapper;
    }

}
