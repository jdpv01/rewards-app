package co.eficacia.com.rewardsapp.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan({ "co.eficacia.com.task" })
public class SpringTaskConfig {
}
