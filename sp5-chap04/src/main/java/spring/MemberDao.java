package spring;

import java.util.Collection;// 생성자에 전달할 의존 객체가 2개 이상일 때 주입 방식을 위해 적음
import java.util.HashMap;
import java.util.Map;

public class MemberDao {

		private static long nextId = 0;
		
		private Map<String, Member> map = new HashMap<>();
		
		public Member selectByEmail(String email) {
			return map.get(email);
		}
		
		public void insert(Member member) {
			member.setId(++nextId);
			map.put(member.getEmail(), member);
		}
		
		public void update(Member member) {
			map.put(member.getEmail(), member);
		}
		
		public Collection<Member> selectAll(){
			return map.values();
		}
}
