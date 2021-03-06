package com.fei2e.filemanage.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName FileType
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/15 14:21
 * @Version 1.0
 **/
public class FileType {

    //默认判断文件头前三个字节内容
    public static int CHECK_BYTES_NUMBER = 3;

    public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

    private FileType(){}
    static{
        getAllFileType(); //初始化文件类型信息
    }

    /**
     * Discription:[getAllFileType,常见文件头信息]
     */
    private static void getAllFileType()
    {
        FILE_TYPE_MAP.put("ffd8ffe000104a464946", "jpg"); //JPEG (jpg)
        FILE_TYPE_MAP.put("00000018667479706d70", "mp4,m4a"); //mp4 (华为手机拍摄)
        FILE_TYPE_MAP.put("0000001c667479706d70", "mp4"); //mp4 (网页下载)
        FILE_TYPE_MAP.put("89504e470d0a1a0a0000", "png"); //PNG (png)
        FILE_TYPE_MAP.put("38425053000100000000", "psd"); //Photoshop (psd)
        FILE_TYPE_MAP.put("2320416c696173204f42", "obj"); //3d模型obj (obj)
        // FILE_TYPE_MAP.put("2f2a0d0a4e6176696361", "sql");//navicat mysql 与内容有关
        // FILE_TYPE_MAP.put("494e5345525420494e54", "sql");//xml文件
        FILE_TYPE_MAP.put("4357530f8c9c0000789c", "swf"); //falsh有关动画文件 (swf)
        FILE_TYPE_MAP.put("504b0304140000080000", "fla"); //falsh动画文件 (fla)
        FILE_TYPE_MAP.put("504b0304140006000800", "vsdx,xlsx"); //Visio 绘图 excel表格
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "doc"); //MS Excel 注意：word、msi 和 excel的文件头一样
        FILE_TYPE_MAP.put("504b03040a0000000000", "docx");//docx文件
        FILE_TYPE_MAP.put("504b03040a0000080800", "zip");
        FILE_TYPE_MAP.put("504b0304140000000800", "zip");
        FILE_TYPE_MAP.put("504b0304140008080800", "jar");
        FILE_TYPE_MAP.put("4d5a9000030000000400", "exe");//可执行文件
        FILE_TYPE_MAP.put("3c3f786d6c2076657273", "xml");//xml开头

        FILE_TYPE_MAP.put("49492a00", "tif"); //TIFF (tif)

        FILE_TYPE_MAP.put("377abcaf271c0004", "7z");//7z压缩包

        FILE_TYPE_MAP.put("255044462d31", "pdf"); //Adobe Acrobat (pdf-1)
        FILE_TYPE_MAP.put("474946383961", "gif"); //GIF (gif)
        FILE_TYPE_MAP.put("526172211a07", "rar");

        FILE_TYPE_MAP.put("52494646d07d60074156", "avi");
        FILE_TYPE_MAP.put("424d228c010000000000", "bmp"); //16色位图(bmp)
        FILE_TYPE_MAP.put("424d8240090000000000", "bmp"); //24位位图(bmp)
        FILE_TYPE_MAP.put("424d8e1b030000000000", "bmp"); //256色位图(bmp)
        FILE_TYPE_MAP.put("49545346030000006000", "chm");//bat文件
        FILE_TYPE_MAP.put("cafebabe0000002e0041", "class");//bat文件

        FILE_TYPE_MAP.put("41433130313500000000", "dwg"); //CAD (dwg)
        FILE_TYPE_MAP.put("46726f6d3a203d3f6762", "eml"); //Email [Outlook Express 6] (eml)
        FILE_TYPE_MAP.put("464c5601050000000900", "flv"); //flv与f4v相同
        FILE_TYPE_MAP.put("1f8b0800000000000000", "gz");//gz文件
        FILE_TYPE_MAP.put("235468697320636f6e66", "ini");
        FILE_TYPE_MAP.put("5374616E64617264204A", "mdb"); //MS Access (mdb)
        FILE_TYPE_MAP.put("4d546864000000060001", "mid"); //MIDI (mid)
        FILE_TYPE_MAP.put("4d616e69666573742d56", "mf");//MF文件
        FILE_TYPE_MAP.put("49443303000000002176", "mp3");
        FILE_TYPE_MAP.put("000001ba210001000180", "mpg"); //传统的视频格式
        FILE_TYPE_MAP.put("04000000010000001300", "mxp");//bat文件
        FILE_TYPE_MAP.put("252150532D41646F6265", "ps");//Postscript

        FILE_TYPE_MAP.put("2e524d46000000120001", "rmvb"); //rmvb/rm相同
        FILE_TYPE_MAP.put("6431303a637265617465", "torrent");
        FILE_TYPE_MAP.put("7b5c727466315c616e73", "rtf"); //Rich Text Format (rtf)
        FILE_TYPE_MAP.put("52494646e27807005741", "wav"); //Wave (wav)
        FILE_TYPE_MAP.put("3026b2758e66cf11a6d9", "wmv"); //wmv与asf相同
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "wps");//WPS文字wps、表格et、演示dps都是一样的


        FILE_TYPE_MAP.put("504B030414000808", "APK");
        FILE_TYPE_MAP.put("504B03040A000000", "IPA");
        FILE_TYPE_MAP.put("6D6F6F76", "mov"); //Quicktime (mov)
        FILE_TYPE_MAP.put("FF575043", "wpd"); //WordPerfect (wpd)
        FILE_TYPE_MAP.put("CFAD12FEC5FD746F", "dbx"); //Outlook Express (dbx)
        FILE_TYPE_MAP.put("2142444E", "pst"); //Outlook (pst)
        FILE_TYPE_MAP.put("AC9EBD8F", "qdf"); //Quicken (qdf)
        FILE_TYPE_MAP.put("E3828596", "pwl"); //Windows Password (pwl)
        FILE_TYPE_MAP.put("2E7261FD", "ram"); //Real Audio (ram)
        FILE_TYPE_MAP.put("424D","bmp");//Windows Bitmap
        FILE_TYPE_MAP.put("41433130","dwg");//CAD
        FILE_TYPE_MAP.put("38425053","psd");//Adobe Photoshop
        FILE_TYPE_MAP.put("7B5C727466","rtf");//Rich Text Format
        FILE_TYPE_MAP.put("44656C69766572792D646174653A","eml");//Email [thorough only]
        FILE_TYPE_MAP.put("D0CF11E0",".doc");//MS Word/Excel
        FILE_TYPE_MAP.put("255044462D312E","pdf");//Adobe Acrobat
        FILE_TYPE_MAP.put("52617221","rar");//RAR Archive
        FILE_TYPE_MAP.put("57415645","wav");// Wave
        FILE_TYPE_MAP.put("41564920","avi");//AVI
        FILE_TYPE_MAP.put("2E524D46","rm");//Real Media
        FILE_TYPE_MAP.put("000001BA","mpg");//MPEG
        FILE_TYPE_MAP.put("000001B3","mpg");//MPEG
        FILE_TYPE_MAP.put("3026B2758E66CF11","asf");//Windows Media
        FILE_TYPE_MAP.put("4D546864","mid");//MIDI
    }


    /**
     * 根据制定文件的文件头判断其文件类型
     * @param filePath
     * @return
     */
    public static String getFileType(String filePath){
        String res = null;

        try {
            FileInputStream is = new FileInputStream(filePath);
            res=getFileType(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }
    public static void main(String[] args){
        String s=getFileType("C:\\Users\\dell\\Desktop\\a.xls");
        System.out.println(s);
    }
    public static String getFileType(InputStream in){
        String res = null;
        try {
            byte[] b = new byte[8];
            in.read(b, 0, b.length);
            String fileCode = bytesToHexString(b);
            System.out.println(fileCode);
            //这种方法在字典的头代码不够位数的时候可以用但是速度相对慢一点
            Iterator<String> keyIter = FILE_TYPE_MAP.keySet().iterator();
            while(keyIter.hasNext()){
                String key = keyIter.next();
                if(key.toLowerCase().startsWith(fileCode.toLowerCase()) || fileCode.toLowerCase().startsWith(key.toLowerCase())){
                    res = FILE_TYPE_MAP.get(key);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 得到上传文件的文件头
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static int getCheckBytesNumber() {
        return CHECK_BYTES_NUMBER;
    }

    public static void setCheckBytesNumber(int checkBytesNumber) {
        CHECK_BYTES_NUMBER = checkBytesNumber;
    }
}
