package main;

import java.io.*;

import assembler.Assembler;
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.WrongIdPasswordException;

public class MainForAssembler {

	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // �ܼ� �Է� �ޱ�
		
		while(true) {
			System.out.println("��ɾ �Է��ϼ��� : ");
			String command = reader.readLine(); // ���ڿ� �� �� �Է� ����
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("�����մϴ�.");
				break;
			}
			if(command.startsWith("new ")) {
				processNewCommand(command.split(" ")); // ������ �����ڷ� �ν��� �Է¹��� ���ڿ��� �迭�� ����
				continue;
			}else if(command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			}
			printHelp(); // ��ɾ �߸� �Է��ϸ� ����Ǵ� ����
		}
	}
	
	private static Assembler assembler = new Assembler(); //Assembler ��ü ����. �̶� ����� ��ü�� ��� ����
	
	private static void processNewCommand(String[] arg) {
		if(arg.length != 5) {
			printHelp();
			return;
		}
		MemberRegisterService regSvc = assembler.getMemberRegisterService(); // assembler��ü�� �ٸ� ��ü�� �ҷ���
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("��ȣ�� Ȯ���� ��ġ���� ����");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("����߽��ϴ�. \n"); // �ٸ� ���⼱ db�� ������ ���� �ʾ� ���α׷��� �����ϸ� �޸𸮿� �ִ� ȸ�� ������ �� ����
		} catch(DuplicateMemberException e) {
			System.out.println("�̹� �����ϴ� �̸����Դϴ�.");
		}
	} // Assembler��ü�� MemberRegisterService ��ü�� ������ �� MemberDao��ü�� �����ߴ�. 
	   // �׷��� ȸ�������� ������ MemberDao ��ü�� Map Ÿ���ʵ��� map�� ȸ�� �����Ͱ� �߰��ȴ�.
	
	private static void processChangeCommand(String[] arg) {
		if(arg.length != 4) {
			printHelp();
			return;
		}
		ChangePasswordService changePwdSvc = assembler.getChangePasswordService();
		
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("��ȣ�� ������. \n");
		} catch (MemberNotFoundException e) {
			System.out.println("�������� �ʴ� �̸����Դϴ�.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("�̸��ϰ� ��ȣ�� ��ġ���� �ʽ��ϴ�.\n");
		}
	}
	
	private static void printHelp() {
		System.out.println();
		System.out.println("�Ʒ� ��ɾ� ������ Ȯ���ϼ���.");
		System.out.println("��ɾ� ���� (���� ����): ");
		System.out.println("new �̸��� �̸� ��ȣ ��ȣȮ��");
		System.out.println("change �̸��� ������ ������");
		System.out.println();
	}
}
