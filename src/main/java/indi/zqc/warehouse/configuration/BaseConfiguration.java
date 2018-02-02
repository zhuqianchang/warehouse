package indi.zqc.warehouse.configuration;

import indi.zqc.warehouse.service.ECodeService;
import indi.zqc.warehouse.service.UserService;
import indi.zqc.warehouse.util.ECodeUtils;
import indi.zqc.warehouse.util.SpringBeanUtils;
import indi.zqc.warehouse.util.UserUtils;
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

    @Bean
    public ECodeUtils eCodeUtils(ECodeService eCodeService) {
        return new ECodeUtils(eCodeService);
    }

    @Bean
    public UserUtils userUtils(UserService userService) {
        return new UserUtils(userService);
    }
}
