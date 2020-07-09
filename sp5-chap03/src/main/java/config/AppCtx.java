package config;
// �����̳� �ۼ�. �������� � ��ü�� �����ϰ� ������ ��� �������� �����ؾ� �Ѵ�.
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;
import spring.MemberPrinter;
import spring.MemberListPrinter;
import spring.MemberInfoPrinter; // setter method ��ü ���� ����� ����� Ŭ����
import spring.VersionPrinter; // �⺻ ������ Ÿ�� �� ����

@Configuration // ������ ���� Ŭ����
public class AppCtx{
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(6);
		return versionPrinter;
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		//infoPrinter.setMemberDao(memberDao());
		//infoPrinter.setPrinter(memberPrinter());
		return infoPrinter;
	}
	
	@Bean // ������ bean
	public MemberDao memberDao() { // memberDao�� bean ��ü �̸��̰�, MemberDao�� bean ��ü Ÿ���̴�.
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao()); // memberDao �޼��� ȣ��. �̴� memberDao�� ������ ��ü�� Member.. �����ڸ� ���� �����Ѵ�.
	}
	
	@Bean
	public ChangePasswordService ChangePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao(), memberPrinter());
	}
}