package com.yfny.servicefstructure.mapper;

import com.yfny.servicefstructure.builder.ExampleSqlBuilder;
import com.yfny.servicefstructure.entity.ExampleEntity;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 功能结构管理场景示例对象Mapper
 * Author auto
 * Date  2019-09-12
 */
public interface ExampleMapper extends BaseMapper<ExampleEntity> {

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集
     *
     * @param   example    对象实体
     * @return  返回对象列表为查询结果
     */
    @SelectProvider(type = ExampleSqlBuilder.class, method = "buildFindExampleByAndCondition")
    List<ExampleEntity> findListByAndCondition(@Param("example") ExampleEntity example);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集
     *
     * @param   example    对象实体
     * @return  返回对象列表为查询结果
     */
    @SelectProvider(type = ExampleSqlBuilder.class, method = "buildFindExampleByORCondition")
    List<ExampleEntity> findListByORCondition(@Param("example") ExampleEntity example);

}
