package com.tp.users.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
 // 인터페이스안의 모든 메서드는 추상메서드, 추상메서드를 포함하기떄문에 객체생성불가
// 추상메서드 body가 없음 (실행되는것이 없기때문에 행위불가)
	
	//추상메서드(바디없이 ;으로 선언만 된것을 보고 추상메서드임을 알수있음)
	// 다형성을 위해 사용, 강제성을 부여(무조건 추상메서드 구현해야 사용가능)
	public ActionForward execute(HttpServletRequest request,
						HttpServletResponse response) throws Exception;
	
	
}
