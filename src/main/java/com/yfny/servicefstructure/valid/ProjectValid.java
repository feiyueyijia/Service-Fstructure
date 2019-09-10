package com.yfny.servicefstructure.valid;

import com.yfny.servicefstructure.entity.ProjectEntity;
import com.yfny.servicefstructure.service.ProjectService;
import com.yfny.utilscommon.basemvc.common.BaseEntity;
import com.yfny.utilscommon.basemvc.common.BusinessException;
import com.yfny.utilscommon.util.InvokeResult;
import com.yfny.utilscommon.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 功能结构管理项目对象属性验证
 * Author auto
 * Date  2019-08-21
 */
@Component
public class ProjectValid {

    @Autowired
    private ProjectService projectService;

    private static ProjectValid projectValid;

    private static final int LOCK = 5;

    @PostConstruct
    public void init() {
        projectValid = this;
    }

    public static void validInsert(ProjectEntity entity) throws BusinessException {
        validNameEmpty(entity, BaseEntity.INSERT);
        validNameDuplicate(entity, BaseEntity.INSERT);
        validNameLegal(entity, BaseEntity.INSERT);
    }

    public static void validUpdate(ProjectEntity entity) throws BusinessException {
        validPermission(entity, BaseEntity.UPDATE);
        validLocked(entity, BaseEntity.UPDATE);
    }

    public static void validDelete(ProjectEntity entity) throws BusinessException {
        validPermission(entity, BaseEntity.DELETE);
    }

    public static void validSelect(ProjectEntity entity) throws BusinessException {
        validNameEmpty(entity, BaseEntity.SELECT);
        validNameLegal(entity, BaseEntity.SELECT);
    }

    public static void validLock(ProjectEntity entity) throws BusinessException {
        validPermission(entity, LOCK);
    }

    private static void validPermission(ProjectEntity entity, int scenario) throws BusinessException {
        boolean flag = projectValid.projectService.permission(entity);
        if (!flag) {
            String params = InvokeResult.getMsgFromCfg("20134", null);
            switch (scenario) {
                case BaseEntity.INSERT:

                case BaseEntity.UPDATE:
                    throw new BusinessException("20103", params);
                case BaseEntity.DELETE:
                    throw new BusinessException("20105", params);
                case BaseEntity.SELECT:

                case LOCK:
                    throw new BusinessException("20111", params);
                default:

            }

        }
    }

    private static void validLocked(ProjectEntity entity, int scenario) throws BusinessException {
        boolean flag = projectValid.projectService.isLocked(entity);
        if (flag) {
            String params = InvokeResult.getMsgFromCfg("20135", null);
            switch (scenario) {
                case BaseEntity.INSERT:

                case BaseEntity.UPDATE:
                    throw new BusinessException("20103", params);
                case BaseEntity.DELETE:

                case BaseEntity.SELECT:

                default:

            }
        }
    }

    private static void validNameDuplicate(ProjectEntity entity, int scenario) throws BusinessException {
        ProjectEntity param = new ProjectEntity();
        param.setName(entity.getName());
        int p = projectValid.projectService.selectCount(param);
        if (p > 0) {
            String params = InvokeResult.getMsgFromCfg("20130", null);
            switch (scenario) {
                case BaseEntity.INSERT:
                    throw new BusinessException("20101", params);
                case BaseEntity.UPDATE:

                case BaseEntity.DELETE:

                case BaseEntity.SELECT:

                default:

            }
        }
    }

    private static void validNameEmpty(ProjectEntity entity, int scenario) throws BusinessException {
        if (StringUtils.isEmpty(entity.getName())) {
            String params = InvokeResult.getMsgFromCfg("20131", null);
            switch (scenario) {
                case BaseEntity.INSERT:
                    throw new BusinessException("20101", params);
                case BaseEntity.UPDATE:

                case BaseEntity.DELETE:

                case BaseEntity.SELECT:
                    throw new BusinessException("20133");
                default:

            }
        }
    }

    private static void validNameLegal(ProjectEntity entity, int scenario) throws BusinessException {
        if (entity.getName().contains("$%^&*~!")) {
            String params = InvokeResult.getMsgFromCfg("20132", null);
            switch (scenario) {
                case BaseEntity.INSERT:
                    throw new BusinessException("20101", params);
                case BaseEntity.UPDATE:

                case BaseEntity.DELETE:

                case BaseEntity.SELECT:
                    throw new BusinessException("20133");
                default:

            }
        }
    }

}
