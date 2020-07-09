package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;
import spring.MemberPrinter;
import spring.MemberListPrinter;
import spring.MemberInfoPrinter; 
import spring.VersionPrinter; 

@Configuration
public class AppConf2 {
	@Autowired // spring �ڵ� ���� ���. �ش� Ÿ���� bean�� ã�� �ʵ忡 �Ҵ�
	private MemberDao memberDao;
	@Autowired
	private MemberPrinter memberPrinter;
	
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
		// infoPrinter.setMemberDao(memberDao); // Autowired�Ǵ� memberDao, memberPrinter �޼��� ��ȣ ()�� ���ش�
		// infoPrinter.setPrinter(memberPrinter);
		// MemberInfoPrinter���� ���� Autowired�� �ϸ� ���� ���� 2���� ���� �ʿ䰡 ����. ��¥ ���ϴ�!!! ���� ���Կ��� �ڵ� �������� �Ѿ�� ���̽�!
		return infoPrinter;
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao); // Autowired�Ǵ� memberDao �޼��� ��ȣ ()�� ���ش�
	}
	
	@Bean
	public ChangePasswordService ChangePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao); // Autowired�Ǵ� memberDao �޼��� ��ȣ ()�� ���ش�
		return pwdSvc;
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao, memberPrinter); // Autowired�Ǵ� memberDao, memberPrinter �޼��� ��ȣ ()�� ���ش�
	}
}
