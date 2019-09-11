package com.yfny.servicefstructure.entity;

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
 * 功能结构管理功能对象Entity
 * Author auto
 * Date  2019-09-05
 */
@Table(name = "t_fstructure_function")
public class FunctionEntity extends BaseEntity {

    @Id
    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "id", length = 64)
    private String id;

    @Column(name = "parent_id", length = 64)
    private String parentId;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "name", length = 255)
    private String name;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "level", length = 11)
    private int level;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "repositories", length = 255)
    private String repositories;

    @Column(name = "class_name", length = 255)
    private String className;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "progress", length = 32)
    private String progress;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "lockin", length = 11)
    private String lockin;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "progress_bar", length = 11)
    private String progressBar;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "create_time", length = 19)
    private Date createTime;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "update_time", length = 19)
    private Date updateTime;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "keep_time", length = 11)
    private int keepTime;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "sort", length = 11)
    private int sort;

    @NotEmpty(message = "存在不能为空的字段未填写")
    @Column(name = "project_id", length = 64)
    private String projectId;

    @Column(name = "user_id", length = 64)
    private String userId;


    public FunctionEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRepositories() {
        return repositories;
    }

    public void setRepositories(String repositories) {
        this.repositories = repositories;
    }

    public String getClassName() {
        return className;
    }

    public void setClass(String className) {
        this.className = className;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getLockin() {
        return lockin;
    }

    public void setLockin(String lockin) {
        this.lockin = lockin;
    }

    public String getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(String progressBar) {
        this.progressBar = progressBar;
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

    public int getKeepTime() {
        return keepTime;
    }

    public void setKeepTime(int keepTime) {
        this.keepTime = keepTime;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**************************************此下为非数据库字段属性**************************************/

    @Transient
    private String userName;

    @OneToMany
    @Transient
    private List<PanelEntity> panelList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<PanelEntity> getPanelList() {
        return panelList;
    }

    public void setPanelList(List<PanelEntity> panelList) {
        this.panelList = panelList;
    }
}
