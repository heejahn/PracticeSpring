package spring;

import java.time.format.DateTimeFormatter;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class MemberPrinter {
	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy�� MM�� dd��"); // �ʵ尪 �ʱ�ȭ
	}
		
	public void print(Member member) {
		if(dateTimeFormatter == null) {
			System.out.printf("ȸ�� ���� : ���̵� = %d, �̸��� = %s, �̸� = %s, ����� = %tF \n", 
					member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
		}else {
			System.out.printf(
				"ȸ�� ���� : ���̵� = %d, �̸��� = %s, �̸� = %s, ����� = %s \n",
				member.getId(), member.getEmail(), member.getName(), 
				dateTimeFormatter.format(member.getRegisterDateTime()));
		}
	}
		
	@Autowired
	public void setDateFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	} // �ش� �޼����� ������ Autowired�� ���� �� ������ �� �ִ� bean�� �������� ������ ������ �߻��Ѵ�. 
}    // ���� null���� ��� �ִ��� ������ �����ϵ��� ������ ����� �Ѵ�. required �Ӽ�, �ڹ�8�� optional, @Nullable ����� �ִ�.

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


