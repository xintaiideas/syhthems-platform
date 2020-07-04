package top.sunriseydy.syhthems.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dengmin
 * @date 2019-04-09 17:05
 */
@SpringBootApplication(scanBasePackages = {"top.sunriseydy.syhthems"})
public class Web {
    public static void main(String[] args) {
        SpringApplication.run(Web.class, args);
    }
}
