package config;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.MemberDao;
import spring.MemberPrinter;

@Configuration
@Import(AppConf2.class) // AppConf2 설정 클래스 함께 사용 가능
public class AppConfImport {
	
	@Bean 
	public MemberDao memberDao() { 
		return new MemberDao();
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
}
