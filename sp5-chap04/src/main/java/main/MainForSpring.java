package main; 
// Spring 컨테이너를 이용해 main 작성
import java.io.*;

import org.springframework.context.ApplicationContext; // 컨테이너
import org.springframework.context.annotation.AnnotationConfigApplicationContext; //객체 정보 불러오기

//import assembler.Assembler;
import config.AppCtx; // 작성한 컨테이너 import
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.WrongIdPasswordException;
import spring.MemberListPrinter; // 생성자 2개인 객체의 파일
import spring.MemberInfoPrinter; // setter method를 이용한 객체 주입
import spring.VersionPrinter; // 기본 데이터 타입 값 설정

public class MainForSpring {
	
	private static ApplicationContext ctx = null; //컨테이너를 가져오기 위해 객체 초기화

	public static void main(String[] args) throws IOException{
		
		ctx = new AnnotationConfigApplicationContext(AppCtx.class); // AppCtx에서 내용을 가져와서 스프링 컨테이너 생성
		// 위 줄에서 2개의 설정 파일을 가진다면 (AppConf1.class, AppConf2.class);라고 적어주면 끝남. 그리고 뭔가 더 있는 듯..
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // 콘솔 입력 받기
		
		while(true) {
			System.out.println("명령어를 입력하세요 : ");
			String command = reader.readLine(); // 문자열 한 줄 입력 받음
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다.");
				break;
			}
			if(command.startsWith("new ")) {
				processNewCommand(command.split(" ")); // 공백을 구분자로 인식해 입력받은 문자열을 배열로 저장
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
			printHelp(); // 명령어를 잘못 입력하면 실행되는 도움말
		}
	}
	
	//private static Assembler assembler = new Assembler(); //Assembler 객체 생성. 이때 사용할 객체가 모두 생성
	
	private static void processNewCommand(String[] arg) {
		if(arg.length != 5) {
			printHelp();
			return;
		}
		//MemberRegisterService regSvc = assembler.getMemberRegisterService(); 
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class); // AppCtx 통해 memberRegSvc 빈 객체 불러오기
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않음");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록했습니다. \n"); // 다만 여기선 db와 연동을 하지 않아 프로그램을 종료하면 메모리에 있던 회원 정보가 다 날라감
		} catch(DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.");
		}
	} // Assembler객체는 MemberRegisterService 객체를 생성할 때 MemberDao객체를 주입했다. 
	   // 그래서 회원정보를 담으면 MemberDao 객체의 Map 타입필드의 map에 회원 데이터가 추가된다.
	
	private static void processChangeCommand(String[] arg) {
		if(arg.length != 4) {
			printHelp();
			return;
		}
		//ChangePasswordService changePwdSvc = assembler.getChangePasswordService();
		ChangePasswordService changePwdSvc = ctx.getBean("ChangePwdSvc", ChangePasswordService.class);
		
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호를 변경함. \n");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
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
		System.out.println("아래 명령어 사용법을 확인하세요.");
		System.out.println("명령어 사용법 (띄어쓰기 주의): ");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println();
	}
}
