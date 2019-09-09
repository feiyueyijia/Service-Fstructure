package com.yfny.servicefstructure.service;

import com.yfny.servicefstructure.entity.FunctionEntity;
import com.yfny.utilscommon.basemvc.common.BusinessException;
import com.yfny.utilscommon.basemvc.producer.BaseService;

import java.util.Map;

/**
 * 功能结构管理功能对象Service
 * Author auto
 * Date  2019-08-21
 */
public interface FunctionService extends BaseService<FunctionEntity> {

    Map<String, Object> getTreeOf(FunctionEntity entity) throws BusinessException;

    boolean permission(FunctionEntity entity) throws BusinessException;

    boolean isLocked(FunctionEntity entity) throws BusinessException;

}
