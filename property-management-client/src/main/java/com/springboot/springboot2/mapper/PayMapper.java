package com.springboot.springboot2.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.springboot2.pojo.Pay;
import com.springboot.springboot2.pojo.PayInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@Mapper
public interface PayMapper {

    Page<PayInfo> selectPayInfoOnCondition(
            Page<PayInfo> page,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end,
            @Param("username") String username,
            @Param("idCard") String idCard,
            @Param("floorId") Integer floorId,
            @Param("buildingId") Integer buildingId
    );

    // 子查询
    Pay queryById(Integer id);
}
