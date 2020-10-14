package com.fei2e.filemanage.ueditor.define;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MIMEType
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/14 14:54
 * @Version 1.0
 **/
public class MIMEType {
    public static final Map<String, String> types = new HashMap<String, String>(){{
        put( "image/gif", ".gif" );
        put( "image/jpeg", ".jpg" );
        put( "image/jpg", ".jpg" );
        put( "image/png", ".png" );
        put( "image/bmp", ".bmp" );
    }};

    public static String getSuffix ( String mime ) {
        return MIMEType.types.get( mime );
    }

}
