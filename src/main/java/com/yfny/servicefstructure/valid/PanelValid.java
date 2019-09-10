package com.yfny.servicefstructure.valid;

import com.yfny.servicefstructure.entity.PanelEntity;
import com.yfny.servicefstructure.service.PanelService;
import com.yfny.utilscommon.basemvc.common.BaseEntity;
import com.yfny.utilscommon.basemvc.common.BusinessException;
import com.yfny.utilscommon.basemvc.producer.BaseValid;
import com.yfny.utilscommon.util.InvokeResult;
import com.yfny.utilscommon.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能结构管理面板对象属性验证
 * Author auto
 * Date  2019-08-21
 */
@Component
public class PanelValid extends BaseValid<PanelEntity> {

    @Autowired
    private PanelService panelService;

    public void validInsert(PanelEntity entity) throws BusinessException {
        validNameEmpty(entity, BaseEntity.INSERT);
        validNameDuplicate(entity, BaseEntity.INSERT);
        validNameLegal(entity, BaseEntity.INSERT);
    }

    public void validUpdate(PanelEntity entity) throws BusinessException {

    }

    public void validDelete(PanelEntity entity) throws BusinessException {

    }

    public void validSelect(PanelEntity entity) throws BusinessException {
        validNameEmpty(entity, BaseEntity.SELECT);
        validNameLegal(entity, BaseEntity.SELECT);
    }

    private void validNameDuplicate(PanelEntity entity, int scenario) throws BusinessException {
        PanelEntity param = new PanelEntity();
        param.setName(entity.getName());
        int p = panelService.selectCount(param);
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

    private static void validNameEmpty(PanelEntity entity, int scenario) throws BusinessException {
        if (StringUtils.isEmpty(entity.getName())) {
            String params = InvokeResult.getMsgFromCfg("20231", null);
            switch (scenario) {
                case BaseEntity.INSERT:
                    throw new BusinessException("20201", params);
                case BaseEntity.UPDATE:

                case BaseEntity.DELETE:

                case BaseEntity.SELECT:
                    throw new BusinessException("20235");
                default:

            }
        }
    }

    private void validNameLegal(PanelEntity entity, int scenario) throws BusinessException {
        if (entity.getName().contains("$%^&*~!")) {
            String params = InvokeResult.getMsgFromCfg("20232", null);
            switch (scenario) {
                case BaseEntity.INSERT:
                    throw new BusinessException("20201", params);
                case BaseEntity.UPDATE:

                case BaseEntity.DELETE:

                case BaseEntity.SELECT:
                    throw new BusinessException("20235");
                default:

            }
        }
    }

}
