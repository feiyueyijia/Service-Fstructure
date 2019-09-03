package com.yfny.servicefstructure.composite;

import com.yfny.servicefstructure.entity.ProjectEntity;
import com.yfny.servicefstructure.mapper.ProjectMapper;
import com.yfny.utilscommon.basemvc.producer.BaseComponent;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 功能结构管理项目对象Composite
 * Author auto
 * Date  2019-08-21
 */
@Component
public class ProjectComposite extends BaseComponent<ProjectEntity> {

    @Autowired
    private ProjectMapper projectMapper;

    public static ProjectComposite projectComposite;

    @PostConstruct
    public void init() {
        projectComposite = this;
    }

    @Override
    public BaseMapper<ProjectEntity> getBaseMapper() {
        return projectComposite.projectMapper;
    }

}
