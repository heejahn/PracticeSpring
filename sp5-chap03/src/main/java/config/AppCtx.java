package config;
// 컨테이너 작성. 스프링이 어떤 객체를 생성하고 의존을 어떻게 주입할지 정의해야 한다.
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;
import spring.MemberPrinter;
import spring.MemberListPrinter;
import spring.MemberInfoPrinter; // setter method 객체 주입 방식을 사용한 클래스
import spring.VersionPrinter; // 기본 데이터 타입 값 설정

@Configuration // 스프링 설정 클래스
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
	
	@Bean // 스프링 bean
	public MemberDao memberDao() { // memberDao가 bean 객체 이름이고, MemberDao가 bean 객체 타입이다.
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao()); // memberDao 메서드 호출. 이는 memberDao가 생성한 객체를 Member.. 생성자를 통해 주입한다.
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