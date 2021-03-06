package com.yfny.servicefstructure.mapper;

import com.yfny.servicefstructure.builder.ProjectSqlBuilder;
import com.yfny.servicefstructure.entity.ProjectEntity;
import com.yfny.utilscommon.basemvc.producer.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 功能结构管理项目对象Mapper
 * Author auto
 * Date  2019-08-21
 */
public interface ProjectMapper extends BaseMapper<ProjectEntity> {

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，并列查询取交集
     *
     * @param project 对象实体
     * @return 返回对象列表为查询结果
     */
    @SelectProvider(type = ProjectSqlBuilder.class, method = "buildFindProjectByAndCondition")
    List<ProjectEntity> findListByAndCondition(@Param("project") ProjectEntity project);

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，亦或查询取并集
     *
     * @param project 对象实体
     * @return 返回对象列表为查询结果
     */
    @SelectProvider(type = ProjectSqlBuilder.class, method = "buildFindProjectByORCondition")
    List<ProjectEntity> findListByORCondition(@Param("project") ProjectEntity project);

}
