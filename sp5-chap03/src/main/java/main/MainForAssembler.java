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
			}else if(command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			}
			printHelp(); // 명령어를 잘못 입력하면 실행되는 도움말
		}
	}
	
	private static Assembler assembler = new Assembler(); //Assembler 객체 생성. 이때 사용할 객체가 모두 생성
	
	private static void processNewCommand(String[] arg) {
		if(arg.length != 5) {
			printHelp();
			return;
		}
		MemberRegisterService regSvc = assembler.getMemberRegisterService(); // assembler객체로 다른 객체를 불러옴
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
		ChangePasswordService changePwdSvc = assembler.getChangePasswordService();
		
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호를 변경함. \n");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
		}
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
