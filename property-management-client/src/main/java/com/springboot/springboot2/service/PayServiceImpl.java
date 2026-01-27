package com.springboot.springboot2.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.springboot2.mapper.PayMapper;
import com.springboot.springboot2.pojo.PageResult;
import com.springboot.springboot2.pojo.PayInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PayServiceImpl implements PayService {

    private final PayMapper payMapper;

    public PayServiceImpl(PayMapper payMapper) {
        this.payMapper = payMapper;
    }

    @Override
    public PageResult<PayInfo> pageOfPayInfo(Integer current,
                                             Integer size,
                                             LocalDate start,
                                             LocalDate end,
                                             String username,
                                             String idCard,
                                             Integer floorId,
                                             Integer buildingId) {

        // 参数校验
        current = (current == null || current < 1) ? 1 : current;
        size = (size == null || size < 1 || size > 100) ? 10 : size;

        // 创建分页对象
        Page<PayInfo> page = new Page<>(current, size);

        // 调用 Mapper 查询
        Page<PayInfo> resultPage = payMapper.selectPayInfoOnCondition(
                page, start, end, username, idCard, floorId, buildingId
        );

        // 防空处理
        if (resultPage == null) {
            resultPage = page; // 返回空分页对象，避免 NPE
        }

        // 封装结果返回
        return new PageResult<>(
                resultPage.getRecords(),
                resultPage.getTotal(),
                (int) resultPage.getCurrent(),
                (int) resultPage.getSize()
        );
    }
}

