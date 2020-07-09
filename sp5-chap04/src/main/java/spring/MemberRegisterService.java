package spring;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;

public class MemberRegisterService {
	@Autowired
	private MemberDao memberDao;
	
	public MemberRegisterService() {
		// ���� ���� �⺻ ������
	} 

	public MemberRegisterService(MemberDao memberDao) { 
		this.memberDao = memberDao; 
	}
	
	public Long regist(RegisterRequest req) { // ���� ���� ���� ��ü�� �޼��带 ���
		Member member = memberDao.selectByEmail(req.getEmail()); // ���� �̸������� üũ
		if(member != null) {
			throw new DuplicateMemberException("dup email" + req.getEmail());
		}
		Member newMember = new Member( // ���ο� �̸����̸� ȸ�� ���� ����
				req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
}
