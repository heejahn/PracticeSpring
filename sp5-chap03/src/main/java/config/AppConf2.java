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
	@Autowired // spring 자동 주입 기능. 해당 타입의 bean을 찾아 필드에 할당
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
		// infoPrinter.setMemberDao(memberDao); // Autowired되는 memberDao, memberPrinter 메서드 괄호 ()를 없앤다
		// infoPrinter.setPrinter(memberPrinter);
		// MemberInfoPrinter에서 직접 Autowired를 하면 굳이 위의 2줄을 써줄 필요가 없다. 진짜 편하다!!! 의존 주입에서 자동 주입으로 넘어온 케이스!
		return infoPrinter;
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao); // Autowired되는 memberDao 메서드 괄호 ()를 없앤다
	}
	
	@Bean
	public ChangePasswordService ChangePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao); // Autowired되는 memberDao 메서드 괄호 ()를 없앤다
		return pwdSvc;
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao, memberPrinter); // Autowired되는 memberDao, memberPrinter 메서드 괄호 ()를 없앤다
	}
}
