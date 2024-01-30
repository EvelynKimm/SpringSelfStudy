package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        log.trace("trace log={}" , name);
        log.debug("debug log={}" , name);
        log.info("  info log={}" , name);
        log.warn("  warn log={}" , name);
        log.error("error log={}" , name);

        // 로그를 사용하지 않아도 a+b 계산 로직이 먼저 실행됨. 이런 방식으로는 사용 X
        log.debug("String concat log=" + name);
        // 이 방법을 사용해야 위같은 의미없는 연산이 발생하지 않음
        log.debug("debug log={}" , name);
        return "ok";

    }
}
