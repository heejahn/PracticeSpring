package spring;

public class DuplicateMemberException extends RuntimeException{ // 동일한 이메일로 회원이 가입하려 할 때 발생
	public DuplicateMemberException(String message) {
		super(message);
	}
}
