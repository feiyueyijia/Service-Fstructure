package com.yfny.servicefstructure.service;

import com.yfny.servicefstructure.entity.ProjectEntity;
import com.yfny.utilscommon.basemvc.common.BusinessException;
import com.yfny.utilscommon.basemvc.producer.BaseService;

/**
 * 功能结构管理项目对象Service
 * Author auto
 * Date  2019-08-21
 */
public interface ProjectService extends BaseService<ProjectEntity> {

    boolean permission(ProjectEntity entity) throws BusinessException;

    boolean isLocked(ProjectEntity entity) throws BusinessException;

}
