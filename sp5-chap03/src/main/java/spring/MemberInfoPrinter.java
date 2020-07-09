package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter {
	
	@Autowired
	private MemberDao memDao;
	@Autowired
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
	/*
	// setter method�� MemberDao�� MemberPrinter ��ü ����
	public void setMemberDao(MemberDao memberDao) { 
		this.memDao = memberDao;
	}
	
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	*/ //Autowired�� ���� ����
}
