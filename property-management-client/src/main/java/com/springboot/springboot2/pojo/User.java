package com.springboot.springboot2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Data
@Schema(description = "用户信息，包含管理员、物业人员和住户")
public class User {

    @Schema(description = "用户账号")
    private String account;

    @Schema(description = "用户姓名")
    private String username;

    @Schema(description = "用户密码")
    private String password;

    @Schema(description = "用户类型")
    private Long userType;

    @Schema(description = "用户ID")
    private Integer userId;

    @Schema(description = "用户性别")
    private Short gender;

    @Schema(description = "用户年龄")
    private Short age;

    @Schema(description = "用户状态")
    private Long userStatus;

    @Schema(description = "用户所属房屋ID")
    private Short userRoomId;

    @Schema(description = "用户身份证号")
    private String idcard;

    @Schema(description = "用户房间信息, 可能一个人有多套房")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Room> rooms;

    // ===== MyBatis 单个房间映射 =====
    public void setRoom(Room room) {
        if (room != null) {
            this.rooms = List.of(room);
        }
    }

    // ===== 自定义 setPassword（MD5加密）=====
    public void setPassword(String password) {
        if (password != null && password.length() != 32) {
            this.password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        } else {
            this.password = password;
        }
    }
}

