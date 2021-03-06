package com.yfny.servicefstructure.mapper;

import com.yfny.servicefstructure.builder.ContentSqlBuilder;
import com.yfny.servicefstructure.entity.ContentEntity;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 功能结构管理场景内容对象Mapper
 * Author auto
 * Date  2019-09-12
 */
public interface ContentMapper extends BaseMapper<ContentEntity> {

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集
     *
     * @param content 对象实体
     * @return 返回对象列表为查询结果
     */
    @SelectProvider(type = ContentSqlBuilder.class, method = "buildFindContentByAndCondition")
    List<ContentEntity> findListByAndCondition(@Param("content") ContentEntity content);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集
     *
     * @param content 对象实体
     * @return 返回对象列表为查询结果
     */
    @SelectProvider(type = ContentSqlBuilder.class, method = "buildFindContentByORCondition")
    List<ContentEntity> findListByORCondition(@Param("content") ContentEntity content);

    /**
     * 根据外键查询相应对象（一对多关系）
     *
     * @param panelId 外键
     * @return 返回对象列表为查询结果
     */
    @Select("select * from t_fstructure_content where panel_id = #{panelId}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "id", property = "exampleList",
                    many = @Many(select = "com.yfny.servicefstructure.mapper.ExampleMapper.findExampleByContentId", fetchType = FetchType.EAGER))
    })
    List<ContentEntity> findContentByPanelId(String panelId);

}
