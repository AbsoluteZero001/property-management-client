package com.springboot.springboot2.controller;

import com.springboot.springboot2.pojo.PageResult;
import com.springboot.springboot2.pojo.ResponsePojo;
import com.springboot.springboot2.pojo.UnpaidOwner;
import com.springboot.springboot2.pojo.User;
import com.springboot.springboot2.service.RoomService;
import com.springboot.springboot2.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

/**
 * 用户控制器
 * 提供与用户相关的REST API接口，包括用户增删改查、分页查询、欠费名单等功能
 */
@Tag(name = "业主相关 API")
@RestController //转换为 JSON
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    // ============================================================
    // ✅ 公共工具方法
    // ============================================================

    /**
     * 生成加密账号
     * 使用MD5加密算法，结合用户名和时间戳生成唯一账号
     * @param username 用户名
     * @return 加密后的账号字符串
     */
    private String generateAccount(String username) {
        return DigestUtils.md5DigestAsHex((username + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 默认密码加密
     * 返回系统默认密码，如果setPassword内部自动加密，这里保留明文即可
     * @return 默认密码字符串
     */
    private String defaultPassword() {
        return "123456"; // 如果 setPassword 内部自动加密，这里保留明文即可
    }

    // ============================================================
    // ✅ 新增业主信息（表单）
    // ============================================================


    @PostMapping(value = "/insert", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "新增业主信息（表单）",
            description = """
                新增一个业主用户：
                - 默认为未激活状态；
                - 使用默认密码；
                - 自动生成账号；
                - roomId 通过级联菜单获得。
                """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "新增成功",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponsePojo.class))),
                    @ApiResponse(responseCode = "500", description = "服务器异常")
            }
    )

    public ResponsePojo<Integer> insertUser(
            @Parameter(description = "用户名", example = "张三") @RequestParam String username,
            @Parameter(description = "身份证号", example = "510101200001010001") @RequestParam String idcard,
            @Parameter(description = "性别 0=未知,1=男,2=女", example = "1") @RequestParam Short gender,
            @Parameter(description = "年龄", example = "25") @RequestParam Short age,
            @Parameter(description = "用户类型 1=管理员,2=员工,3=业主", example = "3") @RequestParam Long userType,
            @Parameter(description = "用户状态 1=正常,2=禁用,3=未激活", example = "3") @RequestParam(required = false) Long userStatus,
            @Parameter(description = "房间ID", example = "11") @RequestParam Short userRoomId
    ) {
        try {
            // 创建新用户对象并设置基本信息
            User user = new User();
            user.setUsername(username);
            user.setIdcard(idcard);
            user.setGender(gender);
            user.setAge(age);
            user.setUserType(userType);
            user.setUserRoomId(userRoomId);
            // 设置用户状态，默认为未激活(3)
            user.setUserStatus(userStatus != null ? userStatus : 3L);
            // 生成账号和设置默认密码
            user.setAccount(generateAccount(username));
            user.setPassword(defaultPassword());

            // 调用服务层插入用户并返回结果
            int result = userService.insertUser(user);
            return ResponsePojo.success(result, "新增用户成功");
        } catch (Exception e) {
            // 异常处理并返回错误信息
            return ResponsePojo.error("新增用户失败：" + e.getMessage());
        }
    }

    // ============================================================
    // ✅ 根据ID查询用户
    // ============================================================
    @GetMapping(value = "/id/{userid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "根据用户ID查询用户详细信息")
    public ResponsePojo<User> queryById(
            @Parameter(description = "用户ID", required = true) @PathVariable("userid") Integer id) {

        // 根据ID查询用户信息
        User user = userService.queryById(id);
        // 判断查询结果并返回相应响应
        return (user != null)
                ? ResponsePojo.success(user)
                : ResponsePojo.fail(null, "该 ID 不存在对应用户");
    }

    // ============================================================
    // ✅ 分页查询业主信息
    // ============================================================
    @GetMapping("/pageOfOwners")
    @Operation(summary = "分页查询业主信息",
            description = "分页查询业主信息，默认为第一页，每页默认10条")
    public ResponsePojo<PageResult<User>> pageOfOwners(
            @Parameter(description = "当前页", example = "1") @RequestParam(defaultValue = "1") Integer current,
            @Parameter(description = "每页行数", example = "10") @RequestParam(defaultValue = "10") Integer size) {

        // 确保页码和每页大小不小于1
        current = Math.max(1, current);
        size = Math.max(1, size);

        // 调用服务层获取分页数据
        PageResult<User> page = userService.pageOfOwner(current, size);
        return ResponsePojo.success(page);
    }

    // ============================================================
    // ✅ 欠费名单查询
    // ============================================================
    @GetMapping("/unpaidOwnerList")
    @Operation(summary = "单项费用的欠费名单查询")
    public ResponsePojo<PageResult<UnpaidOwner>> unpaidOwnerList(
            @Parameter(description = "当前页", example = "1") @RequestParam(defaultValue = "1") Integer current,
            @Parameter(description = "每页行数", example = "10") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "费用类型ID") @RequestParam Integer typeId) {

        // 确保页码和每页大小不小于1
        current = Math.max(1, current);
        size = Math.max(1, size);

        // 调用服务层获取欠费名单分页数据
        PageResult<UnpaidOwner> pageResult = userService.pageOfUnpaidOwnerList(current, size, typeId);
        return ResponsePojo.success(pageResult);
    }
}
