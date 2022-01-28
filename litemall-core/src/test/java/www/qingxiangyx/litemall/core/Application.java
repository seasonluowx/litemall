package www.qingxiangyx.litemall.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"www.qingxiangyx.litemall.db",
        "www.qingxiangyx.litemall.core"})
@MapperScan("www.qingxiangyx.litemall.db.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}