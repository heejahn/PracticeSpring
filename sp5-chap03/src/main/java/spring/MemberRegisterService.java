package spring;

import java.time.LocalDateTime;

public class MemberRegisterService {
	private MemberDao memberDao;
	
	public MemberRegisterService(MemberDao memberDao) { // �����ڸ� ���� ���� ��ü�� ���� ����
		this.memberDao = memberDao; // ���� ���� ��ü�� �ʵ忡 �Ҵ�
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
