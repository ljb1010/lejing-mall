package cn.alphahub.mall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 商品服务
 *
 * @author liuwenjing
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"cn.**.product.feign"})
@MapperScans(value = {@MapperScan(value = {"cn.**.dao", "cn.**.mapper"})})
public class LejingProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(LejingProductApplication.class, args);
    }
}
