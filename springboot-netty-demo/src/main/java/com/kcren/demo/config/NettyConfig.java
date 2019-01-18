package com.kcren.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yuzc
 * @Description: nett配置
 * @date 2019/1/17 17:31
 */
@Component
@ConfigurationProperties(prefix = "sms.gateway.customer.netty")
@Data
public class NettyConfig {
    private String url;
    private int port;
}
