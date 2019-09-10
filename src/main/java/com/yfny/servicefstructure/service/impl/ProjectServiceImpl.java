package com.yfny.servicefstructure.service.impl;

import com.yfny.servicefstructure.constant.ProjectConstant;
import com.yfny.servicefstructure.entity.ProjectEntity;
import com.yfny.servicefstructure.mapper.ProjectMapper;
import com.yfny.servicefstructure.service.ProjectService;
import com.yfny.utilscommon.basemvc.common.BusinessException;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import com.yfny.utilscommon.basemvc.producer.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能结构管理项目对象ServiceImpl
 * Author auto
 * Date  2019-08-21
 */
@Service
public class ProjectServiceImpl extends BaseServiceImpl<ProjectEntity> implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public BaseMapper<ProjectEntity> getBaseMapper() {
        return this.projectMapper;
    }

    public boolean permission(ProjectEntity entity) throws BusinessException {
        if (!"管理员".equals(entity.getUserName())) {
            return false;
        }
        return true;
    }

    public boolean isLocked(ProjectEntity entity) throws BusinessException {
        if (ProjectConstant.LOCKED.equals(entity.getLockin())) {
            return true;
        }
        return false;
    }

}
