package indi.zqc.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Title : Application.java
 * Package : indi.zqc.warehouse
 * Description : 主入口
 * Create on : 2018/1/22 9:28
 *
 * @author Zhu.Qianchang
 * @version v1.0.0
 */
@EnableTransactionManagement
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }
}
