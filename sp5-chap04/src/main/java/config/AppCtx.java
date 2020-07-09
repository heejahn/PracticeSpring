package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;
import spring.MemberSummaryPrinter;
import spring.MemberPrinter;
import spring.MemberListPrinter;
import spring.MemberInfoPrinter; // setter method ��ü ���� ����� ����� Ŭ����
import spring.VersionPrinter; // �⺻ ������ Ÿ�� �� ����

@Configuration
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
		// MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		//infoPrinter.setMemberDao(memberDao()); ������̼����� �ڵ� ����
		//infoPrinter.setPrinter(memberPrinter());
		return new MemberInfoPrinter();
	}
	
	@Bean // ������ bean
	public MemberDao memberDao() { 
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao()); 
	}
	
	@Bean
	public ChangePasswordService ChangePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		// pwdSvc.setMemberDao(memberDao()); Autowired�� ���� �ڵ� ������ ��������
		return pwdSvc;
	}
	
	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter();
		// return new MemberListPrinter(memberDao(), memberPrinter());
	}
}