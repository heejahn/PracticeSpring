package chap02;

public class Greeter {
	private String format;
	
	public String greet(String guest) { // ���ڿ� ������ �̿��� ���ο� ���ڿ� ����
		return String.format(format, guest);
	}
	
	public void setFormat(String format) { // greet �޼��忡�� ����� ���� ����
		this.format = format;
	}
}
