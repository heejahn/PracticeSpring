package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

public class Assembler {

	private MemberDao memberDao;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	
	public Assembler() {
		memberDao = new MemberDao(); // ���߿� memberDao�� ��� ���迡 ������ �� �ٸ� �ٲ��ָ� �� 
		regSvc = new MemberRegisterService(memberDao); // ��ü ���� ����
		pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao);
	}
	
	public MemberDao getMemberDao() { // main���� ��ü�� �ʿ��� �� �θ� �� ����
		return memberDao;
	}
	
	public MemberRegisterService getMemberRegisterService() {
		return regSvc;
	}
	
	public ChangePasswordService getChangePasswordService() {
		return pwdSvc;
	}
}
