package com.yfny.servicefstructure.service;

import com.yfny.servicefstructure.entity.FunctionEntity;
import com.yfny.utilscommon.basemvc.common.BusinessException;
import com.yfny.utilscommon.basemvc.producer.BaseService;

/**
 * 功能结构管理功能对象Service
 * Author auto
 * Date  2019-08-21
 */
public interface FunctionService extends BaseService<FunctionEntity> {

    boolean permission(FunctionEntity project) throws BusinessException;

    boolean isLocked(FunctionEntity project) throws BusinessException;

}
