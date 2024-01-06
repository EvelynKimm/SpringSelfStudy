package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.Client;
import spring.Client2;

@Configuration
public class AppCtx {

    @Bean
    public Client client() {
        Client client = new Client();
        client.setHost("host");
        return client;
    }

    @Bean(initMethod = "connect", destroyMethod = "close")
    // 각각 초기화와 소멸 과정에서 사용할 메서드 이름을 넣어준 것
    public Client2 client2() {
        Client2 client = new Client2();
        client.setHost("host");
        return client;
    }
}
