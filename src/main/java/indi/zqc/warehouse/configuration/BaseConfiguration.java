package indi.zqc.warehouse.configuration;

import indi.zqc.warehouse.util.SpringBeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Title : BaseConfiguration.java
 * Package : indi.zqc.warehouse.configuration
 * Description : 基础配置
 * Create on : 2018/1/26 16:36
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Configuration
public class BaseConfiguration {

    @Bean
    public SpringBeanUtils springBeanUtils() {
        return new SpringBeanUtils();
    }
}
