package com.yfny.servicefstructure.controller;

import com.yfny.servicefstructure.entity.ProjectEntity;
import com.yfny.servicefstructure.service.ProjectService;
import com.yfny.utilscommon.basemvc.common.BaseController;
import com.yfny.utilscommon.basemvc.common.BusinessException;
import com.yfny.utilscommon.basemvc.producer.BaseService;
import com.yfny.utilscommon.util.InvokeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jisongZhou on 2019/8/15.
 **/
@RestController
@RequestMapping(value = "/project")
public class ProjectController extends BaseController<ProjectEntity> {

    @Autowired
    private ProjectService projectService;

    @Override
    public BaseService<ProjectEntity> getBaseService() {
        return this.projectService;
    }

    @PostMapping(value = "/insert1")
    @ResponseBody
    public InvokeResult insert(@RequestBody ProjectEntity entity) throws Exception {
        int result = projectService.insertSelective(entity);
        return InvokeResult.writeResult(result, "20100", "10003", "10002");
    }

    @PostMapping(value = "/update1")
    @ResponseBody
    public InvokeResult update(@RequestBody ProjectEntity entity) throws Exception {
        boolean flag1 = projectService.permission(entity);
        boolean flag2 = projectService.isLocked(entity);
        if (!flag1) {
            throw new BusinessException("20111");
        }
        if (flag2) {
            throw new BusinessException("20112");
        }
        ProjectEntity param = new ProjectEntity();
        param.setName(entity.getName());
        ProjectEntity project = projectService.selectOne(param);
        entity.setId(project.getId());
        int result = projectService.updateSelective(entity);
        return InvokeResult.writeResult(result, "20110", "10003", "10002");
    }

    @PostMapping(value = "/delete1")
    @ResponseBody
    public InvokeResult delete(@RequestBody ProjectEntity entity) throws Exception {
        boolean flag1 = projectService.permission(entity);
        if (!flag1) {
            throw new BusinessException("20121");
        }
        int result = projectService.delete(entity);
        return InvokeResult.writeResult(result, "20120", "10003", "10002");
    }

    @PostMapping(value = "/lock")
    @ResponseBody
    public InvokeResult lock(@RequestBody ProjectEntity entity) throws Exception {
        boolean flag = projectService.permission(entity);
        if (!flag) {
            throw new BusinessException("20131");
        }
        ProjectEntity param = new ProjectEntity();
        param.setName(entity.getName());
        ProjectEntity project = projectService.selectOne(param);
        entity.setId(project.getId());
        int result = projectService.updateSelective(entity);
        return InvokeResult.writeResult(result, "20130", "10003", "10002");
    }

    @PostMapping(value = "/permission")
    @ResponseBody
    public InvokeResult permission(@RequestBody ProjectEntity entity) throws Exception {
        boolean result = projectService.permission(entity);
        return InvokeResult.readResult(result, "10001", "10003", "20142");
    }

    @PostMapping(value = "/selectOne1")
    @ResponseBody
    public InvokeResult selectOne(@RequestBody ProjectEntity entity) throws Exception {
        ProjectEntity result = projectService.selectOne(entity);
        return InvokeResult.readResult(result, "20141", "10003", "20142");
    }

    @PostMapping(value = "/findList1")
    @ResponseBody
    public InvokeResult findList(@RequestBody ProjectEntity entity) throws Exception {
        List<ProjectEntity> result = projectService.findAllList("1", "1");
        return InvokeResult.readResult(result, "20140", "10003", "10002");
    }

}
