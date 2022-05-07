package xyz.kakusei.fams.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource("classpath:fams-config.properties")
public class SystemSetting {
    @Value("${pageSize}")
    private Integer pageSize;
}
