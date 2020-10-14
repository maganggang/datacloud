package com.fei2e.filemanage.entity;


import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName FileBase
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/15 8:59
 * @Version 1.0
 **/
@Entity
@Table(name="base")
public class FileBase {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;
    @Column(name = "origin")
  private String origin;
    @Column(name = "base_url")
  private String baseUrl;
    @Column(name = "type")
  private String type;
    @Column(name = "name")
  private String name;
    @Column(name = "path")
  private String path;
    @Column(name = "size")
  private Long size;
    @Column(name = "year")
  private Integer year;
    @Column(name = "month")
  private Integer month;
    @Column(name = "day")
  private Integer day;
    @Column(name = "createTime")
  private Date createTime;
    @Column(name = "creator_id")
private Integer creatorId;
    @Column(name = "code")
    private String code;
    @Column(name = "use_count")
private Integer useCount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
