package com.fei2e.filemanage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName UEditorConfig
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/16 13:48
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "ueditor.config")
public class UEditorConfig {
private String basePath;
private String imageActionName; // 执行上传图片的action名称
private String imageFieldName; /* 提交的图片表单名称 */
private Long imageMaxSize; /* 上传大小限制，单位B */
private String imageAllowFiles; /* 上传图片格式显示 */
private String imageCompressEnable;  /* 是否压缩图片,默认是true */
private String imageCompressBorder;  /* 图片压缩最长边限制 */
private String imageInsertAlign;  /* 插入的图片浮动方式 */
private String imageUrlPrefix; /* 图片访问路径前缀 */
private String imagePathFormat;  /* 上传保存路径,可以自定义保存路径和文件名格式 */
private String scrawlActionName; /* 执行上传涂鸦的action名称 */
private String scrawlFieldName; /* 提交的图片表单名称 */
private String scrawlPathFormat; /* 上传保存路径,可以自定义保存路径和文件名格式 */
private String scrawlMaxSize;  /* 上传大小限制，单位B */
private String scrawlUrlPrefix; /* 图片访问路径前缀 */
private String scrawlInsertAlign;
            /* 截图工具上传 */
private String snapscreenActionName; /* 执行上传截图的action名称 */
private String snapscreenPathFormat;  /* 上传保存路径,可以自定义保存路径和文件名格式 */
private String snapscreenUrlPrefix; /* 图片访问路径前缀 */
private String snapscreenInsertAlign;  /* 插入的图片浮动方式 */
            /* 抓取远程图片配置 */
private String[] catcherLocalDomain;
private String catcherActionName;  /* 执行抓取远程图片的action名称 */
private String catcherFieldName;  /* 提交的图片列表表单名称 */
private String catcherPathFormat;/* 上传保存路径,可以自定义保存路径和文件名格式 */
private String catcherUrlPrefix;  /* 图片访问路径前缀 */
private Long catcherMaxSize; /* 上传大小限制，单位B */
private String[] catcherAllowFiles; /* 抓取图片格式显示 */
            /* 上传视频配置 */
private String videoActionName; //uploadvideo//, /* 执行上传视频的action名称 */
private String videoFieldName;  /* 提交的视频表单名称 */
private String videoPathFormat;  /* 上传保存路径,可以自定义保存路径和文件名格式 */
private String videoUrlPrefix;  /* 视频访问路径前缀 */
private Long videoMaxSize;  /* 上传大小限制，单位B，默认100MB */
private String[] videoAllowFiles; /* 上传视频格式显示 */
            /* 上传文件配置 */
private String fileActionName;  /* controller里,执行上传视频的action名称 */
private String fileFieldName; /* 提交的文件表单名称 */
private String filePathFormat; /* 上传保存路径,可以自定义保存路径和文件名格式 */
private String fileUrlPrefix; /* 文件访问路径前缀 */
private Long fileMaxSize;  /* 上传大小限制，单位B，默认50MB */
private String[] fileAllowFiles; /* 上传文件格式显示 */
            /* 列出指定目录下的图片 */
private String imageManagerActionName;  /* 执行图片管理的action名称 */
private String imageManagerListPath;  /* 指定要列出图片的目录 */
private Integer imageManagerListSize; /* 每次列出文件数量 */
private String imageManagerUrlPrefix;  /* 图片访问路径前缀 */
private String imageManagerInsertAlign;/* 插入的图片浮动方式 */
private String[] imageManagerAllowFiles; /* 列出的文件类型 */
            /* 列出指定目录下的文件 */
private String fileManagerActionName; /* 执行文件管理的action名称 */
private String fileManagerListPath; /* 指定要列出文件的目录 */
private String fileManagerUrlPrefix;  /* 文件访问路径前缀 */
private Integer fileManagerListSize; /* 每次列出文件数量 */
private String[] fileManagerAllowFiles;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getImageActionName() {
        return imageActionName;
    }

    public void setImageActionName(String imageActionName) {
        this.imageActionName = imageActionName;
    }

    public String getImageFieldName() {
        return imageFieldName;
    }

    public void setImageFieldName(String imageFieldName) {
        this.imageFieldName = imageFieldName;
    }

    public Long getImageMaxSize() {
        return imageMaxSize;
    }

    public void setImageMaxSize(Long imageMaxSize) {
        this.imageMaxSize = imageMaxSize;
    }

    public String getImageAllowFiles() {
        return imageAllowFiles;
    }

    public void setImageAllowFiles(String imageAllowFiles) {
        this.imageAllowFiles = imageAllowFiles;
    }

    public String getImageCompressEnable() {
        return imageCompressEnable;
    }

    public void setImageCompressEnable(String imageCompressEnable) {
        this.imageCompressEnable = imageCompressEnable;
    }

    public String getImageCompressBorder() {
        return imageCompressBorder;
    }

    public void setImageCompressBorder(String imageCompressBorder) {
        this.imageCompressBorder = imageCompressBorder;
    }

    public String getImageInsertAlign() {
        return imageInsertAlign;
    }

    public void setImageInsertAlign(String imageInsertAlign) {
        this.imageInsertAlign = imageInsertAlign;
    }

    public String getImageUrlPrefix() {
        return imageUrlPrefix;
    }

    public void setImageUrlPrefix(String imageUrlPrefix) {
        this.imageUrlPrefix = imageUrlPrefix;
    }

    public String getImagePathFormat() {
        return imagePathFormat;
    }

    public void setImagePathFormat(String imagePathFormat) {
        this.imagePathFormat = imagePathFormat;
    }

    public String getScrawlActionName() {
        return scrawlActionName;
    }

    public void setScrawlActionName(String scrawlActionName) {
        this.scrawlActionName = scrawlActionName;
    }

    public String getScrawlFieldName() {
        return scrawlFieldName;
    }

    public void setScrawlFieldName(String scrawlFieldName) {
        this.scrawlFieldName = scrawlFieldName;
    }

    public String getScrawlPathFormat() {
        return scrawlPathFormat;
    }

    public void setScrawlPathFormat(String scrawlPathFormat) {
        this.scrawlPathFormat = scrawlPathFormat;
    }

    public String getScrawlMaxSize() {
        return scrawlMaxSize;
    }

    public void setScrawlMaxSize(String scrawlMaxSize) {
        this.scrawlMaxSize = scrawlMaxSize;
    }

    public String getScrawlUrlPrefix() {
        return scrawlUrlPrefix;
    }

    public void setScrawlUrlPrefix(String scrawlUrlPrefix) {
        this.scrawlUrlPrefix = scrawlUrlPrefix;
    }

    public String getScrawlInsertAlign() {
        return scrawlInsertAlign;
    }

    public void setScrawlInsertAlign(String scrawlInsertAlign) {
        this.scrawlInsertAlign = scrawlInsertAlign;
    }

    public String getSnapscreenActionName() {
        return snapscreenActionName;
    }

    public void setSnapscreenActionName(String snapscreenActionName) {
        this.snapscreenActionName = snapscreenActionName;
    }

    public String getSnapscreenPathFormat() {
        return snapscreenPathFormat;
    }

    public void setSnapscreenPathFormat(String snapscreenPathFormat) {
        this.snapscreenPathFormat = snapscreenPathFormat;
    }

    public String getSnapscreenUrlPrefix() {
        return snapscreenUrlPrefix;
    }

    public void setSnapscreenUrlPrefix(String snapscreenUrlPrefix) {
        this.snapscreenUrlPrefix = snapscreenUrlPrefix;
    }

    public String getSnapscreenInsertAlign() {
        return snapscreenInsertAlign;
    }

    public void setSnapscreenInsertAlign(String snapscreenInsertAlign) {
        this.snapscreenInsertAlign = snapscreenInsertAlign;
    }

    public String[] getCatcherLocalDomain() {
        return catcherLocalDomain;
    }

    public void setCatcherLocalDomain(String[] catcherLocalDomain) {
        this.catcherLocalDomain = catcherLocalDomain;
    }

    public String getCatcherActionName() {
        return catcherActionName;
    }

    public void setCatcherActionName(String catcherActionName) {
        this.catcherActionName = catcherActionName;
    }

    public String getCatcherFieldName() {
        return catcherFieldName;
    }

    public void setCatcherFieldName(String catcherFieldName) {
        this.catcherFieldName = catcherFieldName;
    }

    public String getCatcherPathFormat() {
        return catcherPathFormat;
    }

    public void setCatcherPathFormat(String catcherPathFormat) {
        this.catcherPathFormat = catcherPathFormat;
    }

    public String getCatcherUrlPrefix() {
        return catcherUrlPrefix;
    }

    public void setCatcherUrlPrefix(String catcherUrlPrefix) {
        this.catcherUrlPrefix = catcherUrlPrefix;
    }

    public Long getCatcherMaxSize() {
        return catcherMaxSize;
    }

    public void setCatcherMaxSize(Long catcherMaxSize) {
        this.catcherMaxSize = catcherMaxSize;
    }

    public String[] getCatcherAllowFiles() {
        return catcherAllowFiles;
    }

    public void setCatcherAllowFiles(String[] catcherAllowFiles) {
        this.catcherAllowFiles = catcherAllowFiles;
    }

    public String getVideoActionName() {
        return videoActionName;
    }

    public void setVideoActionName(String videoActionName) {
        this.videoActionName = videoActionName;
    }

    public String getVideoFieldName() {
        return videoFieldName;
    }

    public void setVideoFieldName(String videoFieldName) {
        this.videoFieldName = videoFieldName;
    }

    public String getVideoPathFormat() {
        return videoPathFormat;
    }

    public void setVideoPathFormat(String videoPathFormat) {
        this.videoPathFormat = videoPathFormat;
    }

    public String getVideoUrlPrefix() {
        return videoUrlPrefix;
    }

    public void setVideoUrlPrefix(String videoUrlPrefix) {
        this.videoUrlPrefix = videoUrlPrefix;
    }

    public Long getVideoMaxSize() {
        return videoMaxSize;
    }

    public void setVideoMaxSize(Long videoMaxSize) {
        this.videoMaxSize = videoMaxSize;
    }

    public String[] getVideoAllowFiles() {
        return videoAllowFiles;
    }

    public void setVideoAllowFiles(String[] videoAllowFiles) {
        this.videoAllowFiles = videoAllowFiles;
    }

    public String getFileActionName() {
        return fileActionName;
    }

    public void setFileActionName(String fileActionName) {
        this.fileActionName = fileActionName;
    }

    public String getFileFieldName() {
        return fileFieldName;
    }

    public void setFileFieldName(String fileFieldName) {
        this.fileFieldName = fileFieldName;
    }

    public String getFilePathFormat() {
        return filePathFormat;
    }

    public void setFilePathFormat(String filePathFormat) {
        this.filePathFormat = filePathFormat;
    }

    public String getFileUrlPrefix() {
        return fileUrlPrefix;
    }

    public void setFileUrlPrefix(String fileUrlPrefix) {
        this.fileUrlPrefix = fileUrlPrefix;
    }

    public Long getFileMaxSize() {
        return fileMaxSize;
    }

    public void setFileMaxSize(Long fileMaxSize) {
        this.fileMaxSize = fileMaxSize;
    }

    public String[] getFileAllowFiles() {
        return fileAllowFiles;
    }

    public void setFileAllowFiles(String[] fileAllowFiles) {
        this.fileAllowFiles = fileAllowFiles;
    }

    public String getImageManagerActionName() {
        return imageManagerActionName;
    }

    public void setImageManagerActionName(String imageManagerActionName) {
        this.imageManagerActionName = imageManagerActionName;
    }

    public String getImageManagerListPath() {
        return imageManagerListPath;
    }

    public void setImageManagerListPath(String imageManagerListPath) {
        this.imageManagerListPath = imageManagerListPath;
    }

    public Integer getImageManagerListSize() {
        return imageManagerListSize;
    }

    public void setImageManagerListSize(Integer imageManagerListSize) {
        this.imageManagerListSize = imageManagerListSize;
    }

    public String getImageManagerUrlPrefix() {
        return imageManagerUrlPrefix;
    }

    public void setImageManagerUrlPrefix(String imageManagerUrlPrefix) {
        this.imageManagerUrlPrefix = imageManagerUrlPrefix;
    }

    public String getImageManagerInsertAlign() {
        return imageManagerInsertAlign;
    }

    public void setImageManagerInsertAlign(String imageManagerInsertAlign) {
        this.imageManagerInsertAlign = imageManagerInsertAlign;
    }

    public String[] getImageManagerAllowFiles() {
        return imageManagerAllowFiles;
    }

    public void setImageManagerAllowFiles(String[] imageManagerAllowFiles) {
        this.imageManagerAllowFiles = imageManagerAllowFiles;
    }

    public String getFileManagerActionName() {
        return fileManagerActionName;
    }

    public void setFileManagerActionName(String fileManagerActionName) {
        this.fileManagerActionName = fileManagerActionName;
    }

    public String getFileManagerListPath() {
        return fileManagerListPath;
    }

    public void setFileManagerListPath(String fileManagerListPath) {
        this.fileManagerListPath = fileManagerListPath;
    }

    public String getFileManagerUrlPrefix() {
        return fileManagerUrlPrefix;
    }

    public void setFileManagerUrlPrefix(String fileManagerUrlPrefix) {
        this.fileManagerUrlPrefix = fileManagerUrlPrefix;
    }

    public Integer getFileManagerListSize() {
        return fileManagerListSize;
    }

    public void setFileManagerListSize(Integer fileManagerListSize) {
        this.fileManagerListSize = fileManagerListSize;
    }

    public String[] getFileManagerAllowFiles() {
        return fileManagerAllowFiles;
    }

    public void setFileManagerAllowFiles(String[] fileManagerAllowFiles) {
        this.fileManagerAllowFiles = fileManagerAllowFiles;
    }
}
