package com.yfny.servicefstructure.controller;

import com.yfny.servicefstructure.entity.ProjectEntity;
import com.yfny.servicefstructure.service.ProjectService;
import com.yfny.servicefstructure.valid.ProjectValid;
import com.yfny.utilscommon.util.InvokeResult;
import com.yfny.utilscommon.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jisongZhou on 2019/8/15.
 **/
@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param entity 对象实体
     * @return 返回0为失败，返回1为成功
     */
    @PostMapping(value = "/insert")
    @ResponseBody
    public InvokeResult insert(@RequestBody ProjectEntity entity) throws Exception {
        ProjectValid.validInsert(entity);
        int result = projectService.insert(entity);
        return InvokeResult.writeResult(result, "20100", "20101");
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity 对象实体
     * @return 返回0为失败，返回1为成功
     */
    @PostMapping(value = "/insertSelective")
    @ResponseBody
    public InvokeResult insertSelective(@RequestBody ProjectEntity entity) throws Exception {
        ProjectValid.validInsert(entity);
        int result = projectService.insertSelective(entity);
        return InvokeResult.writeResult(result, "20100", "20101");
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity 对象实体
     * @return 返回0为失败，返回1为成功
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public InvokeResult update(@RequestBody ProjectEntity entity) throws Exception {
        ProjectValid.validUpdate(entity);
        if (StringUtils.isNotBlank(entity.getId())) {
            ProjectEntity param = new ProjectEntity();
            param.setName(entity.getName());
            ProjectEntity project = projectService.selectOne(param);
            entity.setId(project.getId());
        }
        int result = projectService.update(entity);
        return InvokeResult.writeResult(result, "20102", "20003");
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity 对象实体
     * @return 返回0为失败，返回1为成功
     */
    @PostMapping(value = "/updateSelective")
    @ResponseBody
    public InvokeResult updateSelective(@RequestBody ProjectEntity entity) throws Exception {
        ProjectValid.validUpdate(entity);
        if (StringUtils.isNotBlank(entity.getId())) {
            ProjectEntity param = new ProjectEntity();
            param.setName(entity.getName());
            ProjectEntity project = projectService.selectOne(param);
            entity.setId(project.getId());
        }
        int result = projectService.updateSelective(entity);
        return InvokeResult.writeResult(result, "20102", "20003");
    }

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     *
     * @param entity 对象实体
     * @return 返回0为失败，返回1为成功
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public InvokeResult delete(@RequestBody ProjectEntity entity) throws Exception {
        ProjectValid.validDelete(entity);
        int result = projectService.delete(entity);
        return InvokeResult.writeResult(result, "20104", "20105");
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param key 主键
     * @return 返回0为失败，返回1为成功
     */
    @PostMapping(value = "/deleteByPrimaryKey")
    @ResponseBody
    public InvokeResult deleteByPrimaryKey(@RequestParam(value = "key") Object key) throws Exception {
        int result = projectService.deleteByPrimaryKey(key);
        return InvokeResult.writeResult(result, "20104", "20105");
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     *
     * @param entity 对象实体
     * @return 返回null为未查询到结果，返回对象为查询结果，返回多个结果则抛出异常
     */
    @PostMapping(value = "/selectOne")
    @ResponseBody
    public InvokeResult selectOne(@RequestBody ProjectEntity entity) throws Exception {
        ProjectValid.validSelect(entity);
        ProjectEntity result = projectService.selectOne(entity);
        return InvokeResult.readResult(result, "20110", "20111");
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param key 主键
     * @return 返回null为未查询到结果，返回对象为查询结果
     */
    @GetMapping(value = "/selectByPrimaryKey")
    @ResponseBody
    public InvokeResult selectByPrimaryKey(@RequestParam(value = "key") Object key) throws Exception {
        ProjectEntity result = projectService.selectByPrimaryKey(key);
        return InvokeResult.readResult(result, "20110", "20111");
    }

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     *
     * @param entity 对象实体
     * @return 返回查询结果数量
     */
    @PostMapping(value = "/selectCount")
    @ResponseBody
    public InvokeResult selectCount(@RequestBody ProjectEntity entity) throws Exception {
        int result = projectService.selectCount(entity);
        if (result >= 0) {
            return InvokeResult.success(result);
        } else if (result == -1) {
            return InvokeResult.failure("10003", "网络请求超时或服务器崩溃");
        }
        return InvokeResult.failure();
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号，分页返回
     *
     * @param entity   对象实体
     * @param pageNum  页数
     * @param pageSize 每页数量
     * @return 返回对象列表为查询结果
     */
    @PostMapping(value = {"/findList", "/findList/{pageNum}/{pageSize}"})
    @ResponseBody
    public InvokeResult findList(@RequestBody ProjectEntity entity, @PathVariable(value = "pageNum", required = false) String pageNum, @PathVariable(value = "pageSize", required = false) String pageSize) throws Exception {
        List<ProjectEntity> result = projectService.findList(entity, pageNum, pageSize);
        return InvokeResult.readResult(result, "20108", "20109");
    }

    /**
     * 查询全部结果分页返回
     *
     * @param pageNum  页数
     * @param pageSize 每页数量
     * @return 返回对象列表为查询结果
     */
    @GetMapping(value = {"/findAllList", "/findAllList/{pageNum}/{pageSize}"})
    @ResponseBody
    public InvokeResult findAllList(@PathVariable(value = "pageNum", required = false) String pageNum, @PathVariable(value = "pageSize", required = false) String pageSize) throws Exception {
        List<ProjectEntity> result = projectService.findAllList(pageNum, pageSize);
        return InvokeResult.readResult(result, "20108", "20109");
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集
     *
     * @param entity   对象实体
     * @param pageNum  页数
     * @param pageSize 每页数量
     * @return 返回对象列表为查询结果
     */
    @PostMapping(value = {"/findSimpleListByAndCondition", "/findSimpleListByAndCondition/{pageNum}/{pageSize}"})
    @ResponseBody
    public InvokeResult findSimpleListByAndCondition(@RequestBody ProjectEntity entity, @PathVariable(value = "pageNum", required = false) String pageNum, @PathVariable(value = "pageSize", required = false) String pageSize) throws Exception {
        List<ProjectEntity> result = projectService.findSimpleListByAndCondition(entity, pageNum, pageSize);
        return InvokeResult.readResult(result, "20108", "20109");
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集
     *
     * @param entity   对象实体
     * @param pageNum  页数
     * @param pageSize 每页数量
     * @return 返回对象列表为查询结果
     */
    @PostMapping(value = {"/findListByAndCondition", "/findListByAndCondition/{pageNum}/{pageSize}"})
    @ResponseBody
    public InvokeResult findListByAndCondition(@RequestBody ProjectEntity entity, @PathVariable(value = "pageNum", required = false) String pageNum, @PathVariable(value = "pageSize", required = false) String pageSize) throws Exception {
        List<ProjectEntity> result = projectService.findListByAndCondition(entity, pageNum, pageSize);
        return InvokeResult.readResult(result, "20108", "20109");
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集
     *
     * @param entity   对象实体
     * @param pageNum  页数
     * @param pageSize 每页数量
     * @return 返回对象列表为查询结果
     */
    @PostMapping(value = {"/findSimpleListByORCondition", "/findSimpleListByORCondition/{pageNum}/{pageSize}"})
    @ResponseBody
    public InvokeResult findSimpleListByORCondition(@RequestBody ProjectEntity entity, @PathVariable(value = "pageNum", required = false) String pageNum, @PathVariable(value = "pageSize", required = false) String pageSize) throws Exception {
        List<ProjectEntity> result = projectService.findSimpleListByORCondition(entity, pageNum, pageSize);
        return InvokeResult.readResult(result, "20108", "20109");
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集
     *
     * @param entity   对象实体
     * @param pageNum  页数
     * @param pageSize 每页数量
     * @return 返回对象列表为查询结果
     */
    @PostMapping(value = {"/findListByORCondition", "/findListByORCondition/{pageNum}/{pageSize}"})
    @ResponseBody
    public InvokeResult findListByORCondition(@RequestBody ProjectEntity entity, @PathVariable(value = "pageNum", required = false) String pageNum, @PathVariable(value = "pageSize", required = false) String pageSize) throws Exception {
        List<ProjectEntity> result = projectService.findListByORCondition(entity, pageNum, pageSize);
        return InvokeResult.readResult(result, "20108", "20109");
    }

    @PostMapping(value = "/lock")
    @ResponseBody
    public InvokeResult lock(@RequestBody ProjectEntity entity) throws Exception {
        ProjectValid.validLock(entity);
        if (StringUtils.isNotBlank(entity.getId())) {
            ProjectEntity param = new ProjectEntity();
            param.setName(entity.getName());
            ProjectEntity project = projectService.selectOne(param);
            entity.setId(project.getId());
        }
        int result = projectService.updateSelective(entity);
        return InvokeResult.writeResult(result, "20106", "20107");
    }

    @PostMapping(value = "/permission")
    @ResponseBody
    public InvokeResult permission(@RequestBody ProjectEntity entity) throws Exception {
        boolean result = projectService.permission(entity);
        return InvokeResult.readResult(result, "10001", "10002");
    }

}
