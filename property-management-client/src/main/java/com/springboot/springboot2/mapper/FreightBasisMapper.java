package com.springboot.springboot2.mapper;

import com.springboot.springboot2.pojo.FreightBasis;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreightBasisMapper {

    /**
     * 查询全部费率基础信息
     */
    List<FreightBasis> findAll();

    /**
     * 根据 ID 更新费用说明（可更新价格、说明、名称）
     */
    int updateById(FreightBasis fh);
}
