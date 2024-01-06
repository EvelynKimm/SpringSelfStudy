package config;

import annotation.ManualBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring.MemberDao;
import spring.MemberPrinter;

@Configuration
@ComponentScan(basePackages = {"spring"},
        excludeFilters = {
                @Filter(type= FilterType.REGEX, pattern = "spring//..*Dao"),
                @Filter(type= FilterType.ANNOTATION, classes = ManualBean.class)
        })
public class AppCtxWithExclude {

    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }
}
