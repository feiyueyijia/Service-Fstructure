package com.yfny.servicefstructure.builder;

import com.yfny.servicefstructure.entity.ExampleEntity;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * 功能结构管理场景示例对象SqlBuilder
 * Author auto
 * Date  2019-09-12
 */
public class ExampleSqlBuilder {

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，多条件并列查询取交集
     *
     * @param   example    对象实体
     * @return  Sql语句
     */
    public String buildFindExampleByAndCondition(final ExampleEntity example) {
        return buildFindExampleByCondition(example, 0);
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用LIKE，多条件亦或查询取并集
     *
     * @param   example    对象实体
     * @return  Sql语句
     */
    public String buildFindExampleByORCondition(final ExampleEntity example) {
        return buildFindExampleByCondition(example, 1);
    }

    private String buildFindExampleByCondition(final ExampleEntity example, final int type) {
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
                    "value AS value," +
                    "sort AS sort," +
                    "create_time AS createTime," +
                    "update_time AS updateTime," +
                    "content_id AS contentId," +
                    "function_id AS functionId"
                   );
            FROM("t_fstructure_example");
            if (example.getId() != null && !example.getId().equals("")) {
                WHERE("id like #{example.id}" + finalOrSql);
            }
            if (example.getName() != null && !example.getName().equals("")) {
                WHERE("name like #{example.name}" + finalOrSql);
            }
            if (example.getValue() != null && !example.getValue().equals("")) {
                WHERE("value like #{example.value}" + finalOrSql);
            }
//            if (example.getSort() != null) {
//                WHERE("sort like #{example.sort}" + finalOrSql);
//            }
            if (example.getCreateTime() != null) {
                WHERE("create_time like #{example.createTime}" + finalOrSql);
            }
            if (example.getUpdateTime() != null) {
                WHERE("update_time like #{example.updateTime}" + finalOrSql);
            }
            if (example.getContentId() != null && !example.getContentId().equals("")) {
                WHERE("content_id like #{example.contentId}" + finalOrSql);
            }
            if (example.getFunctionId() != null && !example.getFunctionId().equals("")) {
                WHERE("function_id like #{example.functionId}" + finalOrSql);
            }
        }}.toString();

        if (example.getOrders() != null && example.getOrders().size() > 0) {
            Map<String, String> orderMap = example.getOrders();
            int count = 0;
            sqlResult = sqlResult + " ORDER BY ";
            for (String order : orderMap.keySet()) {
                if (count != 0) {
                    order = ", " + order;
                }
                if (ExampleEntity.DESC.equals(orderMap.get(order))) {
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
