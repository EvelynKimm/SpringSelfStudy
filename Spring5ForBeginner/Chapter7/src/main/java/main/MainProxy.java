package main;

import chap07.ExeTimeCalculator;
import chap07.ImpeCalculator;
import chap07.RecCalculator;

public class MainProxy {
    public static void main(String[] args) {
        // 기존 코드의 변경 없이 실행 시간을 출력할 수 있다.
        // factorial() 기능 자체를 구현하기 보다는 다른 객체에 factorial()의 실행을 위임한다.
        // 계산 기능 외에 다른 부가적인 기능을 실행한다. (여기서 부가적인 기능 : 실행시간측정)
        // 이와 같이 핵심 기능의 실행은 다른 객체에 위임하고 부가적인 기능을 제공하는 객체를 프록시(proxy)라 부른다.
        ExeTimeCalculator ttCal1 = new ExeTimeCalculator(new ImpeCalculator());
        System.out.println(ttCal1.factorial(20));

        ExeTimeCalculator ttCal2 = new ExeTimeCalculator(new RecCalculator());
        System.out.println(ttCal2.factorial(20));
    }
}
