package com.springboot.springboot2.constant.FreightBasisData;

import com.springboot.springboot2.mapper.FreightBasisMapper;
import com.springboot.springboot2.pojo.FreightBasis;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@Scope("singleton")
@Schema(description = "费率基础数据")
public class FreightBasisData {

    private final FreightBasisMapper freightBasisMapper;

    @Schema(description = "水费信息")
    private FreightBasis water;

    @Schema(description = "电费信息")
    private FreightBasis electricity;

    @Schema(description = "物业费信息")
    private FreightBasis property;

    // 构造器注入 Mapper 并初始化数据
    public FreightBasisData(FreightBasisMapper freightBasisMapper) {
        this.freightBasisMapper = freightBasisMapper;
        flushData();
    }

    /**
     * 刷新数据的方法
     * 根据 basisName 字段匹配 water、electricity、property
     * 避免依赖数据库返回顺序
     */
    public void flushData() {
        List<FreightBasis> list = freightBasisMapper.findAll();
        if (list == null || list.isEmpty()) {
            throw new IllegalStateException("FreightBasis 数据为空，无法初始化 water/electricity/property");
        }

        synchronized (this) { // 保证线程安全
            for (FreightBasis f : list) {
                if (f.getBasisName() == null) continue;
                switch (f.getBasisName().toLowerCase()) {
                    case "水费", "water" -> this.water = f;
                    case "电费", "electricity" -> this.electricity = f;
                    case "物业费", "property" -> this.property = f;
                }
            }
            // 最后检查是否全部初始化成功
            if (this.water == null || this.electricity == null || this.property == null) {
                throw new IllegalStateException("FreightBasis 数据不足，无法初始化全部字段");
            }
        }
    }
}
