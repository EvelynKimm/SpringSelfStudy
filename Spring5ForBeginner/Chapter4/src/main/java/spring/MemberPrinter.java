package spring;

import jakarta.annotation.Nullable;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberPrinter {
    private DateTimeFormatter dateTimeFormatter;

    public void print(Member member) {
        if (dateTimeFormatter == null) {
            System.out.printf("회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
                    member.getId(), member.getEmail(),
                    member.getName(), member.getRegisterDateTime());
        } else {
            System.out.printf("회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
                    member.getId(), member.getEmail(),
                    member.getName(), dateTimeFormatter.format(member.getRegisterDateTime()));
        }
    }

//    // 1. required = false로 지정하면 매칭되는 빈이 없어도 익셉션이 발생하지 않으며 자동 주입을 수행하지 않음
//    // 이 상황에서는 DateTimeFormatter 타입의 빈이 존재하지 않으면 익셉션을 발생하지 않고 setDateFormatter() 메서드를 실행하지 않는다.
//    // Spring5 부터는 이 방법 대신 Optional 사용 가능
//    @Autowired(required = false)
//    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
//        this.dateTimeFormatter = dateTimeFormatter;
//    }

    // 2. 자동 주입 대상 타입이 Optional인 경우,
    // 일치하는 빈이 존재하지 않으면 값이 없는 Optional을 인자로 전달(예외 발생 X)
    // 일치하는 빈이 존재하면 해당 빈을 값으로 갖는 Optional을 인자로 전달
//    @Autowired
//    public void setDateFormatter(Optional<DateTimeFormatter> formatterOpt) {
//        if (formatterOpt.isPresent()) {
//            this.dateTimeFormatter = formatterOpt.get();
//        } else {
//            this.dateTimeFormatter = null;
//        }
//    }

    // 3. Nullable 애노테이션을 의존 주입 대상 파라미터에 붙이면, 스프링 컨테이너는 세터 메서드를 호출할 때
    // 자동 주입할 빈이 존재하면 해당 빈을 인자로 전달하고
    // 존재하지 않으면 인자로 null을 전달
    @Autowired
    public void setDateFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

}
