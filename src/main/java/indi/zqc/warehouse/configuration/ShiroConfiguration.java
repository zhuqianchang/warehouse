package indi.zqc.warehouse.configuration;

import indi.zqc.warehouse.shiro.AuthRealm;
import indi.zqc.warehouse.shiro.UserAuthenticationService;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Title : ShiroConfiguration.java
 * Package : indi.zqc.warehouse.configuration
 * Description : Shiro配置
 * Create on : 2018/1/25 15:42
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public AuthRealm authRealm(UserAuthenticationService userAuthenticationService) {
        AuthRealm authRealm = new AuthRealm(userAuthenticationService);
        return authRealm;
    }

    @Bean
    public SecurityManager securityManager(AuthRealm authRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        //登录页面
        factoryBean.setLoginUrl("/login");
        //登录成功页面
        factoryBean.setSuccessUrl("/index");
        //未授权页面
        factoryBean.setUnauthorizedUrl("/login");

        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //管理页面不拦截
        filterChainDefinitionMap.put("/manage/**", "anon");
        //登录登出不拦截
        filterChainDefinitionMap.put("/doLogin", "anon");
        filterChainDefinitionMap.put("/logout", "anon");
        //静态资源不拦截
        filterChainDefinitionMap.put("/bin/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/login/**", "anon");
        filterChainDefinitionMap.put("/themes/**", "anon");
        filterChainDefinitionMap.put("/dwz.frag.xml", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        //其它页面均拦截
        filterChainDefinitionMap.put("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }
}
