package com.mzc.o2o.web;

import com.mzc.o2o.common.CommonConst;
import com.mzc.o2o.util.FastDFSClient;
import com.mzc.o2o.vo.ResultVo;
import com.mzc.o2o.web.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传控制层
 */
@RestController
@Slf4j
@RequestMapping(value = "/upload")
public class UploadController extends BaseController {

//    TODO
//    @Value("${FILE_SERVER_URL}")
//    private String FILE_SERVER_URL;

    @PostMapping(value = "/uploadFile")
    public ResultVo<String> upload(@RequestParam("file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:config/fdfs_client.conf");
            log.info("上传图片开始...");
            String path = fastDFSClient.uploadFile(file.getBytes(), extName);
            log.info("上传图片完成...");
//            String url = FILE_SERVER_URL + path;
            String url = CommonConst.FILE_SERVER_URL + path;
            return buildResultVo(url, 1);
        }catch (Exception e){
            log.error("上传图片失败..."+e);
            return buildEmptyResultVo();
        }
    }
}
