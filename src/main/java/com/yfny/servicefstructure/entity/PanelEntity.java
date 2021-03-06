package com.yfny.servicefstructure.entity;

import com.yfny.utilscommon.annotation.relation.ForeignKey;
import com.yfny.utilscommon.annotation.relation.OneToMany;
import com.yfny.utilscommon.basemvc.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 * 功能结构管理场景面板对象Entity
 * Author auto
 * Date  2019-09-09
 */
@Table(name = "t_fstructure_panel")
public class PanelEntity extends BaseEntity {

    @Id
    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "id", length = 64)
    private String id;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "annotation", length = 255)
    private String annotation;

    @Column(name = "description", length = 255)
    private String description;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "type", length = 64)
    private String type;

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
    @Column(name = "function_id", length = 64)
    private String functionId;


    public PanelEntity() {
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

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    /**************************************此下为非数据库字段属性**************************************/

    @OneToMany
    @Transient
    private List<ContentEntity> contentList;

    public List<ContentEntity> getContentList() {
        return contentList;
    }

    public void setContentList(List<ContentEntity> contentList) {
        this.contentList = contentList;
    }
}
