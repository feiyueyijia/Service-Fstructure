package com.yfny.servicefstructure.service;

import com.yfny.servicefstructure.entity.ProjectEntity;
import com.yfny.utilscommon.basemvc.common.BusinessException;
import com.yfny.utilscommon.basemvc.producer.BaseService;

/**
 * Created by jisongZhou on 2019/8/6.
 **/
public interface ProjectService extends BaseService<ProjectEntity> {

    boolean permission(ProjectEntity project) throws BusinessException;

    boolean isLocked(ProjectEntity project) throws BusinessException;

}
