package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import spring.Client;
import spring.Client2;

@Configuration
public class AppCtxWithPrototype {

    // 프로토타입 범위의 빈을 설정할 수도 있다. 빈의 범위를 프로토타입으로 지정하면
    // 빈 객체를 구할 때마다 매번 새로운 객체를 생성한다.
    @Bean
    @Scope("prototype")
    public Client client() {
        Client client = new Client();
        client.setHost("host");
        return client;
    }

    // 싱글톤 범위를 명시적으로 지정하고 싶다면 @Scope 애노테이션 값으로 "singleton"을 주면 된다.
    @Bean(initMethod = "connect", destroyMethod = "close")
    @Scope("singleton")
    public Client2 client2() {
        Client2 client = client2();
        client.setHost("host");
        return client;
    }
}
