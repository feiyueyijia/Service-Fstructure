package com.yfny.servicefstructure.valid;

import com.yfny.servicefstructure.entity.ContentEntity;
import com.yfny.servicefstructure.service.ContentService;
import com.yfny.utilscommon.basemvc.common.BaseEntity;
import com.yfny.utilscommon.basemvc.common.BusinessException;
import com.yfny.utilscommon.basemvc.producer.BaseValid;
import com.yfny.utilscommon.util.InvokeResult;
import com.yfny.utilscommon.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 功能结构管理场景内容对象属性验证
 * Author auto
 * Date  2019-08-21
 */
@Component
public class ContentValid implements BaseValid<ContentEntity> {

    @Autowired
    private ContentService contentService;

    public void validInsert(ContentEntity entity) throws BusinessException {
        validNameEmpty(entity, BaseEntity.INSERT);
        validNameDuplicate(entity, BaseEntity.INSERT);
        validNameLegal(entity, BaseEntity.INSERT);
    }

    public void validUpdate(ContentEntity entity) throws BusinessException {

    }

    public void validDelete(ContentEntity entity) throws BusinessException {

    }

    public void validSelect(ContentEntity entity) throws BusinessException {
        validNameEmpty(entity, BaseEntity.SELECT);
        validNameLegal(entity, BaseEntity.SELECT);
    }

    private void validNameDuplicate(ContentEntity entity, int scenario) throws BusinessException {
        ContentEntity param = new ContentEntity();
        param.setContent(entity.getContent());
        int p = contentService.selectCount(param);
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

    private static void validNameEmpty(ContentEntity entity, int scenario) throws BusinessException {
        if (StringUtils.isEmpty(entity.getContent())) {
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

    private void validNameLegal(ContentEntity entity, int scenario) throws BusinessException {
        if (entity.getContent().contains("$%^&*~!")) {
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
