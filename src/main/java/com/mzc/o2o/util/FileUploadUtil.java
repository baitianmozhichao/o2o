package com.mzc.o2o.util;

import com.mzc.o2o.common.CommonConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

/**
 * @auther: mzc
 */
@Slf4j
public class FileUploadUtil {

    public static String uploadFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String url = "";
        try {
            FastDFSClient fastDFSClient = new FastDFSClient();
            String path = fastDFSClient.uploadFile(file.getBytes(), extName);
            url = CommonConst.FILE_SERVER_URL + path;
        } catch (Exception e) {
            log.error("shop:上传图片失败..." + e);
        }
        return url;
    }
}
