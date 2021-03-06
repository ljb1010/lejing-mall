package cn.alphahub.mall.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 乐璟商城-ES搜索服务
 *
 * @author liuwenjing
 * @date 2021年3月6日
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LejingSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(LejingSearchApplication.class, args);
    }

}
