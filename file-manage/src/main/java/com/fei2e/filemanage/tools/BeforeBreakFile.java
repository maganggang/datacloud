package com.fei2e.filemanage.tools;

import java.io.*;

/**
 * @ClassName BeforeBreakFile
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/21 14:31
 * @Version 1.0
 **/
public class BeforeBreakFile {

    public static int getSize(String path) {
        int rownum = 1;
        try {
            FileReader read = new FileReader(path);
            BufferedReader br = new BufferedReader(read);
            String row;
            while ((row = br.readLine()) != null) {
                rownum ++;
            }
            System.out.println("rownum="+rownum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rownum;
    }
    public static void main(String args[]) {
        try {
            FileReader read = new FileReader("D:\\Users\\dell\\AppData\\Roaming\\google\\WIN-202010\\safe(2).sql");
            BufferedReader br = new BufferedReader(read);
            String row;

            int rownum = 1;

            int fileNo = 1;
            FileWriter fw = new FileWriter("D:/text"+fileNo +".sql");
            while ((row = br.readLine()) != null) {
                rownum ++;
                fw.append(row + "\r\n");

                if((rownum / 464183) > (fileNo - 1)){
                    fw.close();
                    fileNo ++ ;
                    fw = new FileWriter("D:/text"+fileNo +".sql");
                }
            }
            fw.close();
            System.out.println("rownum="+rownum+";fileNo="+fileNo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
