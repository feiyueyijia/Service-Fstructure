package com.yfny.servicefstructure.service.impl;

import com.yfny.servicefstructure.entity.ProjectEntity;
import com.yfny.servicefstructure.mapper.ProjectMapper;
import com.yfny.servicefstructure.service.ProjectService;
import com.yfny.utilscommon.basemvc.common.BusinessException;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import com.yfny.utilscommon.basemvc.producer.BaseServiceImpl;
import com.yfny.utilscommon.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public int insertSelective(ProjectEntity entity) throws BusinessException {
        this.validateProject(entity);
        return super.insertSelective(entity);
    }

    @Override
    public ProjectEntity selectOne(ProjectEntity entity) throws BusinessException {
        this.validateProject1(entity);
        return super.selectOne(entity);
    }

    public boolean permission(ProjectEntity project) throws BusinessException {
        if (!"管理员".equals(project.getUserName())) {
            return false;
        }
        return true;
    }

    public boolean isLocked(ProjectEntity project) throws BusinessException {
//        if ("UNLOCKED".equals(project.getLock())) {
//            return false;
//        }
        if ("已锁定".equals(project.getProgress())) {
            return true;
        }
        return false;
    }

    private void validateProject(ProjectEntity project) throws BusinessException {
        this.validateProjectNameEmpty(project);
        this.validateProjectNameDuplicate(project);
        this.validateProjectNameLegal(project);
    }

    private void validateProjectNameDuplicate(ProjectEntity project) throws BusinessException {
        ProjectEntity param = new ProjectEntity();
        param.setName(project.getName());
        int p = projectMapper.selectCount(param);
        if (p > 0) {
            throw new BusinessException("20101");
        }
    }

    private void validateProjectNameEmpty(ProjectEntity project) throws BusinessException {
        if (StringUtils.isEmpty(project.getName())) {
            throw new BusinessException("20102");
        }
    }

    private void validateProjectNameLegal(ProjectEntity project) throws BusinessException {
        if (project.getName().contains("$%^&*~!")) {
            throw new BusinessException("20103");
        }
    }

    private void validateProject1(ProjectEntity project) throws BusinessException {
        if (StringUtils.isEmpty(project.getName())) {
            throw new BusinessException("20142");
        } else if (project.getName().contains("$%^&*~!")) {
            throw new BusinessException("20142");
        }
    }
}
