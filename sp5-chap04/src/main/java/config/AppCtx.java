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
import spring.MemberInfoPrinter; // setter method 객체 주입 방식을 사용한 클래스
import spring.VersionPrinter; // 기본 데이터 타입 값 설정

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
		//infoPrinter.setMemberDao(memberDao()); 어노테이션으로 자동 주입
		//infoPrinter.setPrinter(memberPrinter());
		return new MemberInfoPrinter();
	}
	
	@Bean // 스프링 bean
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
		// pwdSvc.setMemberDao(memberDao()); Autowired로 의존 자동 주입이 가능해짐
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