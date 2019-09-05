package com.yfny.servicefstructure.builder;

import com.yfny.servicefstructure.entity.FunctionEntity;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * 功能结构管理功能对象SqlBuilder
 * Author auto
 * Date  2019-09-05
 */
public class FunctionSqlBuilder {

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，多条件并列查询取交集
     *
     * @param function 对象实体
     * @return Sql语句
     */
    public String buildFindFunctionByAndCondition(final FunctionEntity function) {
        return buildFindFunctionByCondition(function, 0);
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，多条件亦或查询取并集
     *
     * @param function 对象实体
     * @return Sql语句
     */
    public String buildFindFunctionByORCondition(final FunctionEntity function) {
        return buildFindFunctionByCondition(function, 1);
    }

    private String buildFindFunctionByCondition(final FunctionEntity function, final int type) {
        String orSql = "";
        if (type == 1) {
            orSql = " || '%'";
        } else {
            orSql = " '%'";
        }
        String finalOrSql = orSql;
        String sqlResult = new SQL() {{
            SELECT(
                    "id AS id," +
                            "parent_id AS parentId," +
                            "name AS name," +
                            "level AS level," +
                            "description AS description," +
                            "repositories AS repositories," +
                            "class_name AS className," +
                            "progress AS progress," +
                            "lockin AS lockin," +
                            "progress_bar AS progressBar," +
                            "create_time AS createTime," +
                            "update_time AS updateTime," +
                            "keep_time AS keepTime," +
                            "sort AS sort," +
                            "project_id AS projectId," +
                            "user_id AS userId"
            );
            FROM("t_fstructure_function");
            if (function.getId() != null && !function.getId().equals("")) {
                WHERE("id like #{function.id}" + finalOrSql);
            }
            if (function.getParentId() != null && !function.getParentId().equals("")) {
                WHERE("parent_id like #{function.parentId}" + finalOrSql);
            }
            if (function.getName() != null && !function.getName().equals("")) {
                WHERE("name like #{function.name}" + finalOrSql);
            }
//            if (function.getLevel() != null) {
//                WHERE("level like #{function.level}" + finalOrSql);
//            }
            if (function.getDescription() != null && !function.getDescription().equals("")) {
                WHERE("description like #{function.description}" + finalOrSql);
            }
            if (function.getRepositories() != null && !function.getRepositories().equals("")) {
                WHERE("repositories like #{function.repositories}" + finalOrSql);
            }
            if (function.getClassName() != null && !function.getClassName().equals("")) {
                WHERE("class_name like #{function.className}" + finalOrSql);
            }
            if (function.getProgress() != null && !function.getProgress().equals("")) {
                WHERE("progress like #{function.progress}" + finalOrSql);
            }
            if (function.getLockin() != null && !function.getLockin().equals("")) {
                WHERE("lockin like #{function.lockin}" + finalOrSql);
            }
            if (function.getProgressBar() != null && !function.getProgressBar().equals("")) {
                WHERE("progress_bar like #{function.progressBar}" + finalOrSql);
            }
            if (function.getCreateTime() != null) {
                WHERE("create_time like #{function.createTime}" + finalOrSql);
            }
            if (function.getUpdateTime() != null) {
                WHERE("update_time like #{function.updateTime}" + finalOrSql);
            }
//            if (function.getKeepTime() != null) {
//                WHERE("keep_time like #{function.keepTime}" + finalOrSql);
//            }
//            if (function.getSort() != null) {
//                WHERE("sort like #{function.sort}" + finalOrSql);
//            }
            if (function.getProjectId() != null && !function.getProjectId().equals("")) {
                WHERE("project_id like #{function.projectId}" + finalOrSql);
            }
            if (function.getUserId() != null && !function.getUserId().equals("")) {
                WHERE("user_id like #{function.userId}" + finalOrSql);
            }
        }}.toString();

        if (function.getOrders() != null && function.getOrders().size() > 0) {
            Map<String, String> orderMap = function.getOrders();
            int count = 0;
            sqlResult = sqlResult + " ORDER BY ";
            for (String order : orderMap.keySet()) {
                if (count != 0) {
                    order = ", " + order;
                }
                if (FunctionEntity.DESC.equals(orderMap.get(order))) {
                    sqlResult = sqlResult + order + " DESC";
                } else {
                    sqlResult = sqlResult + order + " ASC";
                }
                count++;
            }
        }
        return sqlResult;
    }

}
