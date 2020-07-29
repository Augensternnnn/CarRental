package com.Augenstern.sys.task;

import com.Augenstern.sys.constant.SysConstant;
import com.Augenstern.sys.utils.AppFileUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 回收临时图片
 */
@Component
@EnableScheduling   // 开启定时任务
public class RecycleTempFileTask {
    /**
     * 每天晚上12点执行
     */
    @Scheduled(cron = "0 0 0 * * ? ")
    public void recycleTempFile() {
        File file = new File(AppFileUtils.PATH);
        deleteFile(file);
    }

    /**
     * 删除图片
     */
    public void deleteFile(File file) {
        if(null != file) {
            File[] listFiles = file.listFiles();
            if(null != listFiles && listFiles.length > 0) {
                for (File f : listFiles) {
                    if(f.isFile()) {
                        if(f.getName().endsWith(SysConstant.FILE_UPLOAD_TEMP))
                            f.delete();
                    } else {
                        // 是文件夹，递归删除
                        deleteFile(f);
                    }
                }
            }
        }
    }
}
