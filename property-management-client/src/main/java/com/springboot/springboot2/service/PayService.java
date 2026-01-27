package com.springboot.springboot2.service;

import com.springboot.springboot2.pojo.PageResult;
import com.springboot.springboot2.pojo.PayInfo;

import java.time.LocalDate;

public interface PayService {

    /**
     * 支付信息分页查询接口
     *
     * @param current     当前页数
     * @param size        每页大小
     * @param start       开始日期
     * @param end         结束日期
     * @param username    用户姓名
     * @param idCard      身份证号
     * @param floorId     楼层ID
     * @param buildingId  建筑ID
     * @return 分页结果 PageResult<PayInfo>
     */
    PageResult<PayInfo> pageOfPayInfo(Integer current,
                                      Integer size,
                                      LocalDate start,
                                      LocalDate end,
                                      String username,
                                      String idCard,
                                      Integer floorId,
                                      Integer buildingId);
}
