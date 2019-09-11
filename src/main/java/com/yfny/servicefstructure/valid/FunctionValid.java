package com.yfny.servicefstructure.valid;

import com.yfny.servicefstructure.entity.FunctionEntity;
import com.yfny.servicefstructure.service.FunctionService;
import com.yfny.utilscommon.basemvc.common.BaseEntity;
import com.yfny.utilscommon.basemvc.common.BusinessException;
import com.yfny.utilscommon.basemvc.producer.BaseValid;
import com.yfny.utilscommon.util.InvokeResult;
import com.yfny.utilscommon.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能结构管理项目功能属性验证
 * Author auto
 * Date  2019-08-21
 */
@Component
public class FunctionValid implements BaseValid<FunctionEntity> {

    @Autowired
    private FunctionService functionService;

    private static final int LOCK = 5;

    public void validInsert(FunctionEntity entity) throws BusinessException {
        validNameEmpty(entity, BaseEntity.INSERT);
        validNameDuplicate(entity, BaseEntity.INSERT);
        validNameLegal(entity, BaseEntity.INSERT);
    }

    public void validUpdate(FunctionEntity entity) throws BusinessException {
        validPermission(entity, BaseEntity.UPDATE);
        validLocked(entity, BaseEntity.UPDATE);
    }

    public void validDelete(FunctionEntity entity) throws BusinessException {
        validPermission(entity, BaseEntity.DELETE);
    }

    public void validSelect(FunctionEntity entity) throws BusinessException {
        validNameEmpty(entity, BaseEntity.SELECT);
        validNameLegal(entity, BaseEntity.SELECT);
    }

    public void validLock(FunctionEntity entity) throws BusinessException {
        validPermission(entity, LOCK);
    }

    public void validSave(FunctionEntity entity) throws BusinessException {

    }

    private void validPermission(FunctionEntity entity, int scenario) throws BusinessException {
        boolean flag = functionService.permission(entity);
        if (!flag) {
            String params = InvokeResult.getMsgFromCfg("20134", null);
            switch (scenario) {
                case BaseEntity.INSERT:

                case BaseEntity.UPDATE:
                    throw new BusinessException("20203", params);
                case BaseEntity.DELETE:
                    throw new BusinessException("20205", params);
                case BaseEntity.SELECT:

                case LOCK:
                    throw new BusinessException("20211", params);
                default:

            }

        }
    }

    private void validLocked(FunctionEntity entity, int scenario) throws BusinessException {
        boolean flag = functionService.isLocked(entity);
        if (flag) {
            String params = InvokeResult.getMsgFromCfg("20235", null);
            switch (scenario) {
                case BaseEntity.INSERT:

                case BaseEntity.UPDATE:
                    throw new BusinessException("20203", params);
                case BaseEntity.DELETE:

                case BaseEntity.SELECT:

                default:

            }
        }
    }

    private void validNameDuplicate(FunctionEntity entity, int scenario) throws BusinessException {
        FunctionEntity param = new FunctionEntity();
        param.setName(entity.getName());
        int p = functionService.selectCount(param);
        if (p > 0) {
            String params = InvokeResult.getMsgFromCfg("20230", null);
            switch (scenario) {
                case BaseEntity.INSERT:
                    throw new BusinessException("20201", params);
                case BaseEntity.UPDATE:

                case BaseEntity.DELETE:

                case BaseEntity.SELECT:

                default:

            }
        }
    }

    private void validNameEmpty(FunctionEntity entity, int scenario) throws BusinessException {
        if (StringUtils.isEmpty(entity.getName())) {
            String params = InvokeResult.getMsgFromCfg("20231", null);
            switch (scenario) {
                case BaseEntity.INSERT:
                    throw new BusinessException("20201", params);
                case BaseEntity.UPDATE:

                case BaseEntity.DELETE:

                case BaseEntity.SELECT:
                    throw new BusinessException("20233");
                default:

            }
        }
    }

    private void validNameLegal(FunctionEntity entity, int scenario) throws BusinessException {
        if (entity.getName().contains("$%^&*~!")) {
            String params = InvokeResult.getMsgFromCfg("20232", null);
            switch (scenario) {
                case BaseEntity.INSERT:
                    throw new BusinessException("20201", params);
                case BaseEntity.UPDATE:

                case BaseEntity.DELETE:

                case BaseEntity.SELECT:
                    throw new BusinessException("20233");
                default:

            }
        }
    }

}
