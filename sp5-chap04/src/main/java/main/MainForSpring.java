package main; 
// Spring �����̳ʸ� �̿��� main �ۼ�
import java.io.*;

import org.springframework.context.ApplicationContext; // �����̳�
import org.springframework.context.annotation.AnnotationConfigApplicationContext; //��ü ���� �ҷ�����

//import assembler.Assembler;
import config.AppCtx; // �ۼ��� �����̳� import
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.WrongIdPasswordException;
import spring.MemberListPrinter; // ������ 2���� ��ü�� ����
import spring.MemberInfoPrinter; // setter method�� �̿��� ��ü ����
import spring.VersionPrinter; // �⺻ ������ Ÿ�� �� ����

public class MainForSpring {
	
	private static ApplicationContext ctx = null; //�����̳ʸ� �������� ���� ��ü �ʱ�ȭ

	public static void main(String[] args) throws IOException{
		
		ctx = new AnnotationConfigApplicationContext(AppCtx.class); // AppCtx���� ������ �����ͼ� ������ �����̳� ����
		// �� �ٿ��� 2���� ���� ������ �����ٸ� (AppConf1.class, AppConf2.class);��� �����ָ� ����. �׸��� ���� �� �ִ� ��..
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
			} else if(command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			/*} else if(command.equals("list")) {
				processListCommand();
				continue;*/
			} else if(command.equals("info")) {
				processInfoCommand(command.split(" "));
				continue;
			} else if(command.equals("version")) {
				processVersionCommand();
				continue;
			}
			printHelp(); // ��ɾ �߸� �Է��ϸ� ����Ǵ� ����
		}
	}
	
	//private static Assembler assembler = new Assembler(); //Assembler ��ü ����. �̶� ����� ��ü�� ��� ����
	
	private static void processNewCommand(String[] arg) {
		if(arg.length != 5) {
			printHelp();
			return;
		}
		//MemberRegisterService regSvc = assembler.getMemberRegisterService(); 
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class); // AppCtx ���� memberRegSvc �� ��ü �ҷ�����
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
		//ChangePasswordService changePwdSvc = assembler.getChangePasswordService();
		ChangePasswordService changePwdSvc = ctx.getBean("ChangePwdSvc", ChangePasswordService.class);
		
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("��ȣ�� ������. \n");
		} catch (MemberNotFoundException e) {
			System.out.println("�������� �ʴ� �̸����Դϴ�.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("�̸��ϰ� ��ȣ�� ��ġ���� �ʽ��ϴ�.\n");
		}
	}
	/*
	private static void processListCommand() {
		MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAll();
	} */
	
	private static void processInfoCommand(String[] arg) {
		if(arg.length != 2) {
			printHelp();
			return;
		}
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(arg[1]);
	}
	
	private static void processVersionCommand() {
		VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
		versionPrinter.print();
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
