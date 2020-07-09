package chap02;

public class Greeter {
	private String format;
	
	public String greet(String guest) { // 문자열 포맷을 이용해 새로운 문자열 생성
		return String.format(format, guest);
	}
	
	public void setFormat(String format) { // greet 메서드에서 사용할 포맷 구성
		this.format = format;
	}
}
