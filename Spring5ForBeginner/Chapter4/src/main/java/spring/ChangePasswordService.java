package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class ChangePasswordService {

    // @Autowired를 붙이면 설정 클래스에서 의존 주입할 필요 X
    @Autowired
    private MemberDao memberDao;

    public void changePassword(String email, String oldPwd, String newPwd) {
        // member 데이터 찾기 위해 email 사용
        Member member = memberDao.selectByEmail(email);
        if (member == null)
            throw new MemberNotFoundException();

        member.changePassword(oldPwd, newPwd);

        memberDao.update(member);
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
