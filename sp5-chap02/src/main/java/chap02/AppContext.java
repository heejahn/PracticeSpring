package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
	
@Configuration // AppContext Ŭ������ ������ ���� Ŭ������ ����
public class AppContext{
		
	@Bean // 9~14 ����. ��ü�� �����ϰ� �ʱ�ȭ�ϴ� ����
	public Greeter greeter() { // bean ��ü ������ = greeter
			Greeter g = new Greeter();
			g.setFormat("%s, �ȳ��ϼ���!");
			return g;
	}
}

