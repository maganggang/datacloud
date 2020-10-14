package com.fei2e.filemanage.handle;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName UEditorSchedule
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/18 13:55
 * @Version 1.0
 **/
@Component
public class UEditorSchedule {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //每隔2秒执行一次
    @Scheduled(fixedRate = 2000)
    public void whileTasks() {
        System.out.println("定间隔时间任务执行时间：" + dateFormat.format(new Date()));
    }

    //每天3：05执行
    @Scheduled(cron = "0 05 03 ? * *")
    public void timeTasks() {
        System.out.println("固定的时间清除文件：" + dateFormat.format(new Date()));
    }
}
