package com.fei2e.filemanage.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName FileUEditor
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/18 8:59
 * @Version 1.0
 **/
@Entity
@Table(name = "file_ueditor")
public class FileUEditor {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ueditor_id")
    private Integer ueditorId;
    @Column(name = "file_id")
    private Integer fileId;
    @Column(name = "create_time")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUeditorId() {
        return ueditorId;
    }

    public void setUeditorId(Integer ueditorId) {
        this.ueditorId = ueditorId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
