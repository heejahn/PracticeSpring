package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
	
@Configuration // AppContext 클래스를 스프링 설정 클래스로 지정
public class AppContext{
		
	@Bean // 9~14 라인. 객체를 생성하고 초기화하는 설정
	public Greeter greeter() { // bean 객체 참조용 = greeter
			Greeter g = new Greeter();
			g.setFormat("%s, 안녕하세요!");
			return g;
	}
}

