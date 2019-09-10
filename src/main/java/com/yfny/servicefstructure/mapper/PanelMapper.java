package com.yfny.servicefstructure.mapper;

import com.yfny.servicefstructure.builder.PanelSqlBuilder;
import com.yfny.servicefstructure.entity.PanelEntity;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 功能结构管理面板对象Mapper
 * Author auto
 * Date  2019-09-10
 */
public interface PanelMapper extends BaseMapper<PanelEntity> {

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集
     *
     * @param panel 对象实体
     * @return 返回对象列表为查询结果
     */
    @SelectProvider(type = PanelSqlBuilder.class, method = "buildFindPanelByAndCondition")
    List<PanelEntity> findSimpleListByAndCondition(@Param("panel") PanelEntity panel);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集
     *
     * @param panel 对象实体
     * @return 返回对象列表为查询结果
     */
    @SelectProvider(type = PanelSqlBuilder.class, method = "buildFindPanelByORCondition")
    List<PanelEntity> findSimpleListByORCondition(@Param("panel") PanelEntity panel);

}
