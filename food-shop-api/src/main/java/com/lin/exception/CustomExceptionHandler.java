package com.lin.exception;

import com.lin.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 自定义异常处理器
 * @author lkmc2
 * @date 2020/3/16 21:00
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 处理文件上传体积过大异常
     * @param exception 异常
     * @return 响应结果
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public JsonResult handlerMaxUploadFile(MaxUploadSizeExceededException exception) {
        return JsonResult.errorMsg("文件上传大小不能超过1M，请压缩图片或者降低图片分辨率再上传！");
    }

}
