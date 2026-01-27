package com.springboot.springboot2.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * 响应时返回 map 结构
 * @param <K> key 类型
 * @param <V> value 类型
 */
@Data
@AllArgsConstructor
@Deprecated(since = "响应时返回 map 结构")
public class ResponseMap<K, V> {

    @Schema(description = "map 结构，key 通常为 String，value 为具体返回数据")
    private Map<K, V> map;

}
