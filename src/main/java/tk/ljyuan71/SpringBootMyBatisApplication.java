package tk.ljyuan71;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("tk.ljyuan71.*.dao")
@SpringBootApplication
@EnableTransactionManagement
public class SpringBootMyBatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMyBatisApplication.class, args);
    }

}
