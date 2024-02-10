package umc.kittenback.config.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import umc.kittenback.KittenBackApplication;

@Configuration
@EnableFeignClients(basePackageClasses = KittenBackApplication.class)
public class FeignClientConfig {
}
