package indi.zqc.warehouse.configuration;

import indi.zqc.warehouse.freemarker.ButtonDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * Title : FreemarkerConfiguration.java
 * Package : indi.zqc.warehouse.configuration
 * Description : Freemarker配置
 * Create on : 2018/2/24 12:35
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Configuration
public class FreemarkerConfiguration {

    @Autowired
    private FreeMarkerProperties properties;

    @Bean
    public ButtonDirective buttonDirective() {
        return new ButtonDirective();
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPaths(properties.getTemplateLoaderPath());
        Map<String, Object> variables = new HashMap<>();
        variables.put("buttonRight", buttonDirective());
        configurer.setFreemarkerVariables(variables);
        return configurer;
    }

}
