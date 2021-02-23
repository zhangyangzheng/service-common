package com.yz.work.common.framework.configuration;

import com.yz.work.common.framework.utils.SpringContextUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * FrameworkAutoConfiguration
 *
 * @Author YangZheng.Zhang
 * @Date 2020/9/16
 */
@Configuration
@ComponentScan(basePackages = {"com.yz.work.common.framework"})
public class FrameworkAutoConfiguration {

    @Bean
    public SpringContextUtils springContextUtils() {
        return new SpringContextUtils();
    }

}