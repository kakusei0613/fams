package xyz.kakusei.fams;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
@MapperScan("xyz.kakusei.fams.mapper")
public class FactoryAutomationManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FactoryAutomationManagementSystemApplication.class, args);
    }
}
