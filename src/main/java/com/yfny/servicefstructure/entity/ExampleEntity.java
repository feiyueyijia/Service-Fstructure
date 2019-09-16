package com.yfny.servicefstructure.entity;

import com.yfny.utilscommon.annotation.relation.ForeignKey;
import com.yfny.utilscommon.basemvc.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * 功能结构管理场景示例对象Entity
 * Author auto
 * Date  2019-09-12
 */
@Table(name = "t_fstructure_example")
public class ExampleEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "id", length = 64)
    private String id;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "name", length = 255)
    private String name;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "value", length = 255)
    private String value;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "sort", length = 11)
    private int sort;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "create_time", length = 19)
    private Date createTime;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "update_time", length = 19)
    private Date updateTime;

    @ForeignKey
    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "content_id", length = 64)
    private String contentId;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "function_id", length = 64)
    private String functionId;


    public ExampleEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    /**************************************此下为非数据库字段属性**************************************/
}
