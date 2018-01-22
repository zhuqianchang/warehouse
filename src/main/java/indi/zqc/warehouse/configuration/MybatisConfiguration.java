package indi.zqc.warehouse.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Title : MybatisConfiguraton.java
 * Package : indi.zqc.warehouse.configuration
 * Description : MyBatis配置
 * Create on : 2018/1/22 9:43
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@Configuration
@MapperScan("indi.zqc.warehouse.dao")
public class MybatisConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        //启用合理化，页码小于1时会查询第一页，页码大于总页数时会查询最后一页
        props.setProperty("reasonable", "true");
        //返回PageInfo类型
        props.setProperty("returnPageInfo", "check");
        //pageSize=0是查询所有数据
        props.setProperty("pageSizeZero", "true");
        //自动识别数据源，spring配置动态数据源时使用
        props.setProperty("autoRuntimeDialect", "false");
        //不支持通过Mapper接口参数来传递分页参数
        props.setProperty("supportMethodsArguments", "false");
        //分页参数,supportMethodsArguments=false时无效
        props.setProperty("params", "pageNum=pageNum;pageSize=numPerPage");
        pageHelper.setProperties(props);
        return pageHelper;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, PageHelper pageHelper) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        //添加PageHelper插件
        factoryBean.setPlugins(new Interceptor[]{pageHelper});
        //添加Xml目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources("classpath:sql-mappers/*.xml"));
        return factoryBean.getObject();
    }

}
