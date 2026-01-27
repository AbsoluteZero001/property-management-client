package com.springboot.springboot2.service;

import com.springboot.springboot2.mapper.FreightBasisMapper;
import com.springboot.springboot2.pojo.FreightBasis;
import org.springframework.stereotype.Service;

@Service
public class FreightBasisServiceImpl implements FreightBasisService {

        private final FreightBasisMapper freightBasisMapper;

        // ✅ 构造函数注入
        public FreightBasisServiceImpl(FreightBasisMapper freightBasisMapper) {
                this.freightBasisMapper = freightBasisMapper;
        }

        @Override
        public int updateById(FreightBasis freightBasis) {
                return freightBasisMapper.updateById(freightBasis);
        }
}
