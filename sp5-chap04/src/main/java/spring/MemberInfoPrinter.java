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
			System.out.println("������ ����\n");
			return;
		}
		printer.print(member);
		System.out.println();
	}
	
	@Autowired // �޼��� autowired ����. �ƿ� ���� �ʵ� ���𿡼� autowired�� ���� ���� �ִ�.
	public void setMemberDao(MemberDao memberDao) { 
		this.memDao = memberDao;
	}
	
	@Autowired
	@Qualifier("printer")
	public void setPrinter(MemberPrinter printer) { // setPrinter���� �� �ٿ��ִ� "unsatisfiedDependencyException"�� ����. bean 2�� ��ٰ�.
		this.printer = printer;
	}
}
