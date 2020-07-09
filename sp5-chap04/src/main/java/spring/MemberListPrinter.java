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
	@Qualifier("summaryPrinter") // MemberPrinter�� ��� �޾� �̸��� ��ġ�� bean�� ���ο� �����ڸ� ������ �ٿ���.
	public void setMemberPrinter(MemberPrinter printer) { // ���� ���� ȿ���� ������ MemberSummaryPrinter�� �ڵ� ���� �޵��� �ϸ� �ȴ�.
		this.printer = printer;
	}
	/*
	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m -> printer.print(m));
	} */
}