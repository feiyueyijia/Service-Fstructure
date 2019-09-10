package com.yfny.servicefstructure.controller;

import com.yfny.servicefstructure.entity.PanelEntity;
import com.yfny.servicefstructure.service.PanelService;
import com.yfny.servicefstructure.valid.PanelValid;
import com.yfny.utilscommon.util.InvokeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能结构管理面板对象Controller
 * Author auto
 * Date  2019-09-05
 */
@RestController
@RequestMapping(value = "/panel")
public class PanelController {

    @Autowired
    private PanelService panelService;

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity 对象实体
     * @return 返回0为失败，返回1为成功
     */
    @PostMapping(value = "/insertSelective")
    @ResponseBody
    public InvokeResult insertSelective(@RequestBody PanelEntity entity) throws Exception {
        int result = panelService.insertSelective(entity);
        return InvokeResult.writeResult(result, "20200", "20201");
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity 对象实体
     * @return 返回0为失败，返回1为成功
     */
    @PostMapping(value = "/updateSelective")
    @ResponseBody
    public InvokeResult updateSelective(@RequestBody PanelEntity entity) throws Exception {
        int result = panelService.updateSelective(entity);
        return InvokeResult.writeResult(result, "20202", "20203");
    }

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     *
     * @param entity 对象实体
     * @return 返回0为失败，返回1为成功
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public InvokeResult delete(@RequestBody PanelEntity entity) throws Exception {
        //PanelValid.validDelete(entity);
        int result = panelService.delete(entity);
        return InvokeResult.writeResult(result, "20204", "20205");
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
        int result = panelService.deleteByPrimaryKey(key);
        return InvokeResult.writeResult(result, "20204", "20205");
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     *
     * @param entity 对象实体
     * @return 返回null为未查询到结果，返回对象为查询结果，返回多个结果则抛出异常
     */
    @PostMapping(value = "/selectOne")
    @ResponseBody
    public InvokeResult selectOne(@RequestBody PanelEntity entity) throws Exception {
        PanelEntity result = panelService.selectOne(entity);
        return InvokeResult.readResult(result, "20208", "20209");
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
        PanelEntity result = panelService.selectByPrimaryKey(key);
        return InvokeResult.readResult(result, "20208", "20209");
    }

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     *
     * @param entity 对象实体
     * @return 返回查询结果数量
     */
    @PostMapping(value = "/selectCount")
    @ResponseBody
    public InvokeResult selectCount(@RequestBody PanelEntity entity) throws Exception {
        int result = panelService.selectCount(entity);
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
    public InvokeResult findList(@RequestBody PanelEntity entity, @PathVariable(value = "pageNum", required = false) String pageNum, @PathVariable(value = "pageSize", required = false) String pageSize) throws Exception {
        List<PanelEntity> result = panelService.findList(entity, pageNum, pageSize);
        return InvokeResult.readResult(result, "20206", "20207");
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
        List<PanelEntity> result = panelService.findAllList(pageNum, pageSize);
        return InvokeResult.readResult(result, "20206", "20207");
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
    public InvokeResult findSimpleListByAndCondition(@RequestBody PanelEntity entity, @PathVariable(value = "pageNum", required = false) String pageNum, @PathVariable(value = "pageSize", required = false) String pageSize) throws Exception {
        List<PanelEntity> result = panelService.findSimpleListByAndCondition(entity, pageNum, pageSize);
        return InvokeResult.readResult(result, "20206", "20207");
    }

//    /**
//     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集
//     *
//     * @param entity   对象实体
//     * @param pageNum  页数
//     * @param pageSize 每页数量
//     * @return 返回对象列表为查询结果
//     */
//    @PostMapping(value = {"/findListByAndCondition", "/findListByAndCondition/{pageNum}/{pageSize}"})
//    @ResponseBody
//    public InvokeResult findListByAndCondition(@RequestBody PanelEntity entity, @PathVariable(value = "pageNum", required = false) String pageNum, @PathVariable(value = "pageSize", required = false) String pageSize) throws Exception {
//        List<PanelEntity> result = panelService.findListByAndCondition(entity, pageNum, pageSize);
//        return InvokeResult.readResult(result, "20206", "20207");
//    }

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
    public InvokeResult findSimpleListByORCondition(@RequestBody PanelEntity entity, @PathVariable(value = "pageNum", required = false) String pageNum, @PathVariable(value = "pageSize", required = false) String pageSize) throws Exception {
        List<PanelEntity> result = panelService.findSimpleListByORCondition(entity, pageNum, pageSize);
        return InvokeResult.readResult(result, "20206", "20207");
    }

//    /**
//     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集
//     *
//     * @param entity   对象实体
//     * @param pageNum  页数
//     * @param pageSize 每页数量
//     * @return 返回对象列表为查询结果
//     */
//    @PostMapping(value = {"/findListByORCondition", "/findListByORCondition/{pageNum}/{pageSize}"})
//    @ResponseBody
//    public InvokeResult findListByORCondition(@RequestBody PanelEntity entity, @PathVariable(value = "pageNum", required = false) String pageNum, @PathVariable(value = "pageSize", required = false) String pageSize) throws Exception {
//        List<PanelEntity> result = panelService.findListByORCondition(entity, pageNum, pageSize);
//        return InvokeResult.readResult(result, "20206", "20207");
//    }

}
