package spring;

import java.time.format.DateTimeFormatter;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class MemberPrinter {
	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"); // 필드값 초기화
	}
		
	public void print(Member member) {
		if(dateTimeFormatter == null) {
			System.out.printf("회원 정보 : 아이디 = %d, 이메일 = %s, 이름 = %s, 등록일 = %tF \n", 
					member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
		}else {
			System.out.printf(
				"회원 정보 : 아이디 = %d, 이메일 = %s, 이름 = %s, 등록일 = %s \n",
				member.getId(), member.getEmail(), member.getName(), 
				dateTimeFormatter.format(member.getRegisterDateTime()));
		}
	}
		
	@Autowired
	public void setDateFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	} // 해당 메서드의 문제는 Autowired로 됐을 때 의존할 수 있는 bean이 존재하지 않으면 에러가 발생한다. 
}    // 따라서 null값이 들어 있더라도 실행이 가능하도록 설정을 해줘야 한다. required 속성, 자바8의 optional, @Nullable 방법이 있다.

/*
 // required
@Autowired(required = false)
	public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}
// Optional
@Autowired
  public void setDateFormatter(Optional<DateTimeFormatter> formatterOpt) {
  	if(formatterOpt.isPresent()){
      this.dateTimeFormatter = formatterOpt.get();
    } else {
      this.dateTimeFormatter = null;
    }
  }
// @Nullable
@Autowired
  public void setDateFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}
 */


