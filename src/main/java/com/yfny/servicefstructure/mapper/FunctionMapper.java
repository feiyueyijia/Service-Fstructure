package com.yfny.servicefstructure.mapper;

import com.yfny.servicefstructure.builder.FunctionSqlBuilder;
import com.yfny.servicefstructure.entity.FunctionEntity;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 功能结构管理功能对象Mapper
 * Author auto
 * Date  2019-09-05
 */
public interface FunctionMapper extends BaseMapper<FunctionEntity> {

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集
     *
     * @param function 对象实体
     * @return 返回对象列表为查询结果
     */
    @SelectProvider(type = FunctionSqlBuilder.class, method = "buildFindFunctionByAndCondition")
    List<FunctionEntity> findSimpleListByAndCondition(@Param("function") FunctionEntity function);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集
     *
     * @param function 对象实体
     * @return 返回对象列表为查询结果
     */
    @SelectProvider(type = FunctionSqlBuilder.class, method = "buildFindFunctionByORCondition")
    List<FunctionEntity> findSimpleListByORCondition(@Param("function") FunctionEntity function);

}
