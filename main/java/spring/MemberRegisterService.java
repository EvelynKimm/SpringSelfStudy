package spring;

import java.time.LocalDateTime;

public class MemberRegisterService {
    private MemberDao memberDao;

    // 생성자를 통해 의존객체를 전달 받음
    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Long regist(RegisterRequest req) {

        // memberDao, selectByEmail() 메서드를 이용해 동일한 이메일을 갖는 회원 데이터가 존재하는지 확인
        // -> 존재하면 DuplicateMemberException을 발생시킨다

        Member member = memberDao.selectByEmail(req.getEmail());
        if (member != null) {
            throw new DuplicateMemberException("dup email " + req.getEmail());
        }

        Member newMember = new Member(
                req.getEmail(), req.getPassword(), req.getName(),
                LocalDateTime.now());
        memberDao.insert(newMember);

        return newMember.getId();
    }
}
