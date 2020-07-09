package spring;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;

public class MemberRegisterService {
	@Autowired
	private MemberDao memberDao;
	
	public MemberRegisterService() {
		// 인자 없는 기본 생성자
	} 

	public MemberRegisterService(MemberDao memberDao) { 
		this.memberDao = memberDao; 
	}
	
	public Long regist(RegisterRequest req) { // 주입 받은 의존 객체의 메서드를 사용
		Member member = memberDao.selectByEmail(req.getEmail()); // 동일 이메일인지 체크
		if(member != null) {
			throw new DuplicateMemberException("dup email" + req.getEmail());
		}
		Member newMember = new Member( // 새로운 이메일이면 회원 내용 저장
				req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
}
