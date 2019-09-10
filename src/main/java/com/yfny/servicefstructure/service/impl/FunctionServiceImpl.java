package com.yfny.servicefstructure.service.impl;

import com.yfny.servicefstructure.constant.FunctionConstant;
import com.yfny.servicefstructure.entity.FunctionEntity;
import com.yfny.servicefstructure.mapper.FunctionMapper;
import com.yfny.servicefstructure.service.FunctionService;
import com.yfny.utilscommon.basemvc.common.BusinessException;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import com.yfny.utilscommon.basemvc.producer.BaseServiceImpl;
import com.yfny.utilscommon.util.MultipleTreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> getTreeOf(FunctionEntity entity) throws BusinessException {
        Map<String, Object> resultMap = new HashMap<>();
        List<FunctionEntity> list = findList(entity, null, null);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (FunctionEntity function : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", function.getId());
            map.put("name", function.getName());
            map.put("parentId", function.getParentId());
            map.put("level", function.getLevel());
            mapList.add(map);
        }
        String treeList = MultipleTreeUtils.getTreeList(list);
        resultMap.put("tree", treeList);
        resultMap.put("list", list);
        return resultMap;
    }

    public boolean permission(FunctionEntity entity) throws BusinessException {
        if (!"管理员".equals(entity.getUserName())) {
            return false;
        }
        return true;
    }

    public boolean isLocked(FunctionEntity entity) throws BusinessException {
        if (FunctionConstant.LOCKED.equals(entity.getLockin())) {
            return true;
        }
        return false;
    }

}
