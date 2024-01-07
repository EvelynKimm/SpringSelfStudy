package main;

import chap07.RecCalculator;
import config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspect {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

//        Calculator cal = ctx.getBean("calculator", Calculator.class);
        // 아래의 경우, "calculator" 빈의 실제 타입은 Calculator를 상속한 프록시 타입이므로
        // RecCalculator로 타입 변환을 할 수 없기 때문에 익셉션 발생
        // 이후 AppCtx.java에 proxyTargetClass = true 속성을 추가해줌으로서 해결!
        RecCalculator cal = ctx.getBean("calculator", RecCalculator.class);
        long fiveFact = cal.factorial(5);
        System.out.println("cal.factorial(5) = " + fiveFact);
        System.out.println(cal.getClass().getName());
        // 터미널을 통해 이 타입은 프록시 타입임을 알 수 있다. (RecCalculator 클래스 X)
        ctx.close();
    }
}
