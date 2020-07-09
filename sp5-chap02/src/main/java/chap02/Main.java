package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
// �ڹ� �������� ������ �о�� bean ��ü�� �����ϰ� ����
public class Main {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = // ��ü�� �����ϸ� AppContext Ŭ������ ������ �Ķ���ͷ� ����
				new AnnotationConfigApplicationContext(AppContext.class); // �׷��� @bean ������ �о�� Greeter ��ü ����,�ʱ�ȭ
		
		Greeter g = ctx.getBean("greeter", Greeter.class); //getBean()�� bean ��ü�� �˻� (bean ��ü �̸�, ��ü Ÿ��)
		String msg = g.greet("������"); // greet �޼��� ����. = String.format("%s, �ȳ��ϼ���!", "������")
		System.out.println(msg);
		ctx.close();
	}
}
// �������� �ٽ� ��� = ��ü�� �����ϰ� �ʱ�ȭ
// AnnotationConfigApplicationContext Ŭ������ �ڹ� Ŭ�������� ������ �о�� ��ü ������ �ʱ�ȭ�� ���
