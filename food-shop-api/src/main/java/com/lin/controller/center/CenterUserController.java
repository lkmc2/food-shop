package com.lin.controller.center;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.lin.bo.center.CenterUserBO;
import com.lin.controller.BaseController;
import com.lin.pojo.Users;
import com.lin.resource.FileUpload;
import com.lin.service.center.CenterUserService;
import com.lin.utils.CookieUtils;
import com.lin.utils.JsonResult;
import com.lin.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 用户信息 Controller（用户中心使用）
 * @author lkmc2
 * @date 2020/3/15 21:15
 */
@Api(value = "用户信息接口（用户中心使用）", tags = "用户信息（用户中心使用）相关接口")
@RestController
@RequestMapping("userInfo")
public class CenterUserController extends BaseController {

    @Autowired
    private CenterUserService centerUserService;

    @Autowired
    private FileUpload fileUpload;

    @ApiOperation(value = "用户头像修改", notes = "用户头像修改")
    @PostMapping("/uploadFace")
    public JsonResult uploadFace(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "file", value = "用户头像", required = true)
            MultipartFile file,
            HttpServletRequest request,
            HttpServletResponse response) {

        // 文件上传路径
        String basePath = fileUpload.getImageUserFaceLocation();

        // 在路径上为每一个用户添加一个 userId ，用于区分不同用户上传
        String uploadPathPrefix = File.separator + userId;

        // 开始文件上传
        if (file != null) {
            // 获取文件上传的文件名称
            String fileName = file.getOriginalFilename();

            if (StrUtil.isNotBlank(fileName)) {
                // 文件后缀名
                String suffix = FileUtil.extName(fileName);

                // 文件重命名：face-{userId}.png
                String newFileName = StrUtil.format("face-{}.{}", userId, suffix);

                // 上传的头像最终保存位置
                String finalFacePath = basePath + uploadPathPrefix + File.separator + newFileName;

                // 用于提供给 web 服务访问的地址
                uploadPathPrefix += ("/" + newFileName);

                File outFile = new File(finalFacePath);

                // 文件不存在时，则创建
                if (!FileUtil.exist(outFile)) {
                    FileUtil.touch(outFile);
                }

                // 文件输出保存到目标路径
                try (FileOutputStream outputStream = new FileOutputStream(outFile)) {
                    InputStream inputStream = file.getInputStream();

                    IoUtil.copy(inputStream, outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return JsonResult.errorMsg("文件不能为空！");
        }

        // 获取图片服务地址
        String imageServerUrl = fileUpload.getImageServerUrl();

        // 由于浏览器可能存在缓存的情况，所以需要加上时间戳保存更新后的图片可以及时刷新
        // 最终头像地址
        String finalUserFaceUrl = imageServerUrl + uploadPathPrefix + "?=" + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss");

        // 更新用户头像到数据库
        Users userResult = centerUserService.updateUserFace(userId, finalUserFaceUrl);

        // 设置用户信息的敏感字段为空
        setNullProperty(userResult);

        // 设置 cookie
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);

        // todo：后续将增加令牌 token ，会整合进 redis ，分布式会话

        return JsonResult.ok(userResult);
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @PostMapping("/update")
    public JsonResult update(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @RequestBody @Valid CenterUserBO centerUserBO,
            BindingResult bindingResult,
            HttpServletRequest request,
            HttpServletResponse response) {

        // 实体类验证出现错误信息
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = getErrors(bindingResult);

            return JsonResult.errorMap(errorMap);
        }

        // 修改用户信息
        Users userResult = centerUserService.updateUserInfo(userId, centerUserBO);

        // 设置用户信息的敏感字段为空
        setNullProperty(userResult);

        // 设置 cookie
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);

        // todo：后续将增加令牌 token ，会整合进 redis ，分布式会话

        return JsonResult.ok(userResult);
    }

    /**
     * 获取实体类验证错误信息
     * @param bindingResult 验证结果
     * @return 实体类验证错误信息 Map
     */
    private Map<String, String> getErrors(BindingResult bindingResult) {
        Map<String, String> resultMap = Maps.newHashMap();

        List<FieldError> errorList = bindingResult.getFieldErrors();

        for (FieldError error : errorList) {
            // 发生认证错误的属性名
            String errorField = error.getField();
            // 错误提示信息
            String errorMsg = error.getDefaultMessage();

            resultMap.put(errorField, errorMsg);
        }

        return resultMap;
    }

    /**
     * 设置用户信息的敏感字段为空
     * @param user 用户信息
     */
    private void setNullProperty(Users user) {
        user.setPassword(null);
        user.setMobile(null);
        user.setEmail(null);
        user.setCreateTime(null);
        user.setUpdateTime(null);
        user.setBirthday(null);
    }

}
