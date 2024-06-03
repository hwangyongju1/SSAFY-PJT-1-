package com.ssafy.myssafit.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.myssafit.model.dao")
public class DBConfig {

}
