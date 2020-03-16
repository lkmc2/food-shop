package com.lin.resource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 文件上传配置信息
 * @author lkmc2
 * @date 2020/3/16 20:23
 */
@Component
@ConfigurationProperties(prefix = "file")
@PropertySource("classpath:file-upload-dev.properties")
@Data
public class FileUpload {

    /** 用户上传头像的地址 **/
    private String imageUserFaceLocation;

    /** 图片服务地址 **/
    private String imageServerUrl;

}
