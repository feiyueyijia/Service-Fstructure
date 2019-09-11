package com.yfny.servicefstructure.mapper;

import com.yfny.servicefstructure.builder.FunctionSqlBuilder;
import com.yfny.servicefstructure.entity.FunctionEntity;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

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
    List<FunctionEntity> findListByAndCondition(@Param("function") FunctionEntity function);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集
     *
     * @param function 对象实体
     * @return 返回对象列表为查询结果
     */
    @SelectProvider(type = FunctionSqlBuilder.class, method = "buildFindFunctionByORCondition")
    List<FunctionEntity> findListByORCondition(@Param("function") FunctionEntity function);

    /**
     * 根据实体中的主键进行查询单个对象
     *
     * @param id 对象实体主键
     * @return 返回单个对象为查询结果
     */
    @Select("select * from t_fstructure_function where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "id", property = "panelList",
                    many = @Many(select = "com.yfny.servicefstructure.mapper.PanelMapper.findPanelByFunctionId", fetchType = FetchType.EAGER))
    })
    FunctionEntity selectComplexById(String id);

}
