package xyz.kakusei.fams;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("xyz.kakusei.fams.mapper")
public class FactoryAutomationManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FactoryAutomationManagementSystemApplication.class, args);
    }

}
