package com.yfny.servicefstructure.service.impl;

import com.yfny.servicefstructure.entity.FunctionEntity;
import com.yfny.servicefstructure.mapper.FunctionMapper;
import com.yfny.servicefstructure.service.FunctionService;
import com.yfny.utilscommon.basemvc.common.BusinessException;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import com.yfny.utilscommon.basemvc.producer.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能结构管理功能对象ServiceImpl
 * Author auto
 * Date  2019-09-05
 */
@Service
public class FunctionServiceImpl extends BaseServiceImpl<FunctionEntity> implements FunctionService {

    @Autowired
    private FunctionMapper functionMapper;

    @Override
    public BaseMapper<FunctionEntity> getBaseMapper() {
        return this.functionMapper;
    }

    public boolean permission(FunctionEntity entity) throws BusinessException {
        if (!"管理员".equals(entity.getUserName())) {
            return false;
        }
        return true;
    }

    public boolean isLocked(FunctionEntity entity) throws BusinessException {
//        if ("UNLOCKED".equals(entity.getLock())) {
//            return false;
//        }
        if ("已锁定".equals(entity.getProgress())) {
            return true;
        }
        return false;
    }

}