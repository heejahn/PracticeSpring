package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
// 자바 설정에서 정보를 읽어와 bean 객체를 생성하고 관리
public class Main {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = // 객체를 생성하며 AppContext 클래스를 생성자 파라미터로 전달
				new AnnotationConfigApplicationContext(AppContext.class); // 그러면 @bean 정보를 읽어와 Greeter 객체 생성,초기화
		
		Greeter g = ctx.getBean("greeter", Greeter.class); //getBean()은 bean 객체를 검색 (bean 객체 이름, 객체 타입)
		String msg = g.greet("스프링"); // greet 메서드 실행. = String.format("%s, 안녕하세요!", "스프링")
		System.out.println(msg);
		ctx.close();
	}
}
// 스프링의 핵심 기능 = 객체를 생성하고 초기화
// AnnotationConfigApplicationContext 클래스는 자바 클래스에서 정보를 읽어와 객체 생성과 초기화를 담당
