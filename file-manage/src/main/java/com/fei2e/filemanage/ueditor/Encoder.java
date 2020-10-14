package com.fei2e.filemanage.ueditor;

/**
 * @ClassName Encoder
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/14 14:36
 * @Version 1.0
 **/
public class Encoder {
    public static String toUnicode ( String input ) {

        StringBuilder builder = new StringBuilder();
        char[] chars = input.toCharArray();

        for ( char ch : chars ) {

            if ( ch < 256 ) {
                builder.append( ch );
            } else {
                builder.append( "\\u" +  Integer.toHexString( ch& 0xffff ) );
            }

        }

        return builder.toString();

    }

}
