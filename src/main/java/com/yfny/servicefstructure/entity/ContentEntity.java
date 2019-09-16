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
 * 功能结构管理场景内容对象Entity
 * Author auto
 * Date  2019-09-12
 */
@Table(name = "t_fstructure_content")
public class ContentEntity extends BaseEntity {

    @Id
    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "id", length = 64)
    private String id;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "keyword", length = 255)
    private String keyword;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "content", length = 255)
    private String content;

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
    @Column(name = "panel_id", length = 64)
    private String panelId;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "function_id", length = 64)
    private String functionId;


    public ContentEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getPanelId() {
        return panelId;
    }

    public void setPanelId(String panelId) {
        this.panelId = panelId;
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
    private List<ExampleEntity> exampleList;

    public List<ExampleEntity> getExampleList() {
        return exampleList;
    }

    public void setExampleList(List<ExampleEntity> exampleList) {
        this.exampleList = exampleList;
    }
}
