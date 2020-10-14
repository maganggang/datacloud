package com.fei2e.filemanage.ueditor.upload;

import com.fei2e.filemanage.ueditor.PathFormat;
import com.fei2e.filemanage.ueditor.define.AppInfo;
import com.fei2e.filemanage.ueditor.define.BaseState;
import com.fei2e.filemanage.ueditor.define.FileType;
import com.fei2e.filemanage.ueditor.define.State;
import org.apache.commons.codec.binary.Base64;

import java.util.Map;

/**
 * @ClassName Base64Uploader
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/14 15:08
 * @Version 1.0
 **/
public class Base64Uploader {
    public static State save(String content, Map<String, Object> conf) {

        byte[] data = decode(content);

        long maxSize = ((Long) conf.get("maxSize")).longValue();

        if (!validSize(data, maxSize)) {
            return new BaseState(false, AppInfo.MAX_SIZE);
        }

        String suffix = FileType.getSuffix("JPG");

        String savePath = PathFormat.parse((String) conf.get("savePath"),
                (String) conf.get("filename"));

        savePath = savePath + suffix;
        String physicalPath = (String) conf.get("rootPath") + savePath;

        State storageState = StorageManager.saveBinaryFile(data, physicalPath);

        if (storageState.isSuccess()) {
            storageState.putInfo("url", PathFormat.format(savePath));
            storageState.putInfo("type", suffix);
            storageState.putInfo("original", "");
        }

        return storageState;
    }

    private static byte[] decode(String content) {
        return Base64.decodeBase64(content);
    }

    private static boolean validSize(byte[] data, long length) {
        return data.length <= length;
    }
}
