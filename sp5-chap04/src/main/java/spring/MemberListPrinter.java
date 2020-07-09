package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
// import java.util.Collection;

public class MemberListPrinter {
	
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public MemberListPrinter() {
		
	}
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Autowired
	@Qualifier("summaryPrinter") // MemberPrinter를 상속 받아 이름이 겹치는 bean에 새로운 한정자를 지정해 붙여줌.
	public void setMemberPrinter(MemberPrinter printer) { // 위와 같은 효과를 보려면 MemberSummaryPrinter를 자동 주입 받도록 하면 된다.
		this.printer = printer;
	}
	/*
	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m -> printer.print(m));
	} */
}
