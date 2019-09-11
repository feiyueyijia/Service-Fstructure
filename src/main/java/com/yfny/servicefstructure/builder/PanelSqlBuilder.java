package com.yfny.servicefstructure.builder;

import com.yfny.servicefstructure.entity.PanelEntity;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * 功能结构管理场景面板对象SqlBuilder
 * Author auto
 * Date  2019-09-10
 */
public class PanelSqlBuilder {

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，多条件并列查询取交集
     *
     * @param   panel    对象实体
     * @return  Sql语句
     */
    public String buildFindPanelByAndCondition(final PanelEntity panel) {
        return buildFindPanelByCondition(panel, 0);
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，多条件亦或查询取并集
     *
     * @param   panel    对象实体
     * @return  Sql语句
     */
    public String buildFindPanelByORCondition(final PanelEntity panel) {
        return buildFindPanelByCondition(panel, 1);
    }

    private String buildFindPanelByCondition(final PanelEntity panel, final int type) {
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
                    "name AS name," +
                    "annotation AS annotation," +
                    "description AS description," +
                    "type AS type," +
                    "sort AS sort," +
                    "create_time AS createTime," +
                    "update_time AS updateTime," +
                    "function_id AS functionId"
                   );
            FROM("t_fstructure_panel");
            if (panel.getId() != null && !panel.getId().equals("")) {
                WHERE("id like #{panel.id}" + finalOrSql);
            }
            if (panel.getName() != null && !panel.getName().equals("")) {
                WHERE("name like #{panel.name}" + finalOrSql);
            }
            if (panel.getAnnotation() != null && !panel.getAnnotation().equals("")) {
                WHERE("annotation like #{panel.annotation}" + finalOrSql);
            }
            if (panel.getDescription() != null && !panel.getDescription().equals("")) {
                WHERE("description like #{panel.description}" + finalOrSql);
            }
            if (panel.getType() != null && !panel.getType().equals("")) {
                WHERE("type like #{panel.type}" + finalOrSql);
            }
//            if (panel.getSort() != null) {
//                WHERE("sort like #{panel.sort}" + finalOrSql);
//            }
            if (panel.getCreateTime() != null) {
                WHERE("create_time like #{panel.createTime}" + finalOrSql);
            }
            if (panel.getUpdateTime() != null) {
                WHERE("update_time like #{panel.updateTime}" + finalOrSql);
            }
            if (panel.getFunctionId() != null && !panel.getFunctionId().equals("")) {
                WHERE("function_id like #{panel.functionId}" + finalOrSql);
            }
        }}.toString();

        if (panel.getOrders() != null && panel.getOrders().size() > 0) {
            Map<String, String> orderMap = panel.getOrders();
            int count = 0;
            sqlResult = sqlResult + " ORDER BY ";
            for (String order : orderMap.keySet()) {
                if (count != 0) {
                    order = ", " + order;
                }
                if (PanelEntity.DESC.equals(orderMap.get(order))) {
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
