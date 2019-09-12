package com.yfny.servicefstructure.builder;

import com.yfny.servicefstructure.entity.ContentEntity;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * 功能结构管理场景内容对象SqlBuilder
 * Author auto
 * Date  2019-09-12
 */
public class ContentSqlBuilder {

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，多条件并列查询取交集
     *
     * @param content 对象实体
     * @return Sql语句
     */
    public String buildFindContentByAndCondition(final ContentEntity content) {
        return buildFindContentByCondition(content, 0);
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，多条件亦或查询取并集
     *
     * @param content 对象实体
     * @return Sql语句
     */
    public String buildFindContentByORCondition(final ContentEntity content) {
        return buildFindContentByCondition(content, 1);
    }

    private String buildFindContentByCondition(final ContentEntity content, final int type) {
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
                            "keyword AS keyword," +
                            "content AS content," +
                            "sort AS sort," +
                            "create_time AS createTime," +
                            "update_time AS updateTime," +
                            "panel_id AS panelId," +
                            "function_id AS functionId"
            );
            FROM("t_fstructure_content");
            if (content.getId() != null && !content.getId().equals("")) {
                WHERE("id like #{content.id}" + finalOrSql);
            }
            if (content.getKeyword() != null && !content.getKeyword().equals("")) {
                WHERE("keyword like #{content.keyword}" + finalOrSql);
            }
            if (content.getContent() != null && !content.getContent().equals("")) {
                WHERE("content like #{content.content}" + finalOrSql);
            }
//            if (content.getSort() != null) {
//                WHERE("sort like #{content.sort}" + finalOrSql);
//            }
            if (content.getCreateTime() != null) {
                WHERE("create_time like #{content.createTime}" + finalOrSql);
            }
            if (content.getUpdateTime() != null) {
                WHERE("update_time like #{content.updateTime}" + finalOrSql);
            }
            if (content.getPanelId() != null && !content.getPanelId().equals("")) {
                WHERE("panel_id like #{content.panelId}" + finalOrSql);
            }
            if (content.getFunctionId() != null && !content.getFunctionId().equals("")) {
                WHERE("function_id like #{content.functionId}" + finalOrSql);
            }
        }}.toString();

        if (content.getOrders() != null && content.getOrders().size() > 0) {
            Map<String, String> orderMap = content.getOrders();
            int count = 0;
            sqlResult = sqlResult + " ORDER BY ";
            for (String order : orderMap.keySet()) {
                if (count != 0) {
                    order = ", " + order;
                }
                if (ContentEntity.DESC.equals(orderMap.get(order))) {
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
