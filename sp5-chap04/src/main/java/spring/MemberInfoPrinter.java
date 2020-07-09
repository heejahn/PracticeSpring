package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberInfoPrinter {
	
	private MemberDao memDao;
	private MemberPrinter printer;
	
	//private MemberDao memDao; // Autowired
	// private MemberPrinter printer; // Autowired
	
	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if (member == null) {
			System.out.println("데이터 없음\n");
			return;
		}
		printer.print(member);
		System.out.println();
	}
	
	@Autowired // 메서드 autowired 예시. 아예 위에 필드 선언에서 autowired를 해줄 수도 있다.
	public void setMemberDao(MemberDao memberDao) { 
		this.memDao = memberDao;
	}
	
	@Autowired
	@Qualifier("printer")
	public void setPrinter(MemberPrinter printer) { // setPrinter에서 안 붙여주니 "unsatisfiedDependencyException"이 떴다. bean 2개 뜬다고.
		this.printer = printer;
	}
}
