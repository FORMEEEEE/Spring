package edu.kh.comm.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.comm.member.model.service.MemberService;
import edu.kh.comm.member.model.service.MemberServiceImpl;
import edu.kh.comm.member.model.vo.Member;

// POJO 기반 프레임워크 : 외부 라이브러리 상속 X

// class : 객체를 만들기 위한 설계도
// -> 객체로 생성 되여야지 기능 수행 가능
// --> IOC(제어의 역전, 객체 생명주기를 스프링이 관리)를 이용하여 객체 생성
// ** 이 때, 스프링이 생성한 객체를 bean이라고 함 **

// bean 등록 == 스프링이 객체로 만들어서 가지고 있으라는 뜻 

// @Component // 해당 클래스를 bean으로 등록하라고 프로그램에게 알려주는 주석(annotation)

@Controller // 생성된 bean이 Controller 임을 명시 + bean으로 등록
@RequestMapping("/member") // localhost:8080/comm/member 이하의 요청을 처리하는 controller
public class MemberController {
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
//	private MemberService service = new MemberServiceImpl(); // IOC(제어의 역전) : new 연산자를 통해서 개발자가 직접 객체 생성하지 않는다
				// 부							// 자식		
	
	@Autowired //bean으로 등록된 객체 중 타입이 같거나, 상속관계인 bean을 주입해주는 역활
	private MemberService service;
	
	// Controller : 요청/응답을 제어하는 역활을 하는 클래스
	
	 /*
	  * @RequestMapping : 클라이언트 요청(url)에 맞는 클래스 or 메서드를 연결 시켜주는 어노테이션
	  * 
	  * [위치에 따른 해석]
	  *  - 클래스 레벨 : 공통 주소
	  *  - 메서드 레벨 : 공통 주소 외 나머지 주소
	  *  
	  *  단, 클래스레벨에 @RequestMapping이 존재하지 않는다면
	  *  - 메서드 레벨 : 단독 요청 처리 주소
	  * 
	  * [작성법에 따른 해석]
	  * 
	  * 1) @RequestMapping("url")
	  * --> 요청 방식(GET/POST) 관계 없이 url이 일치하는 요청 처리
	  * 
	  * 2) @RequestMapping(value = "url" , method = RequestMethod.GET | POST)
	  * --> 요청 방식에 따라 요청 처리함
	  * 
	  * ** 메서드 레벨에서 GET/POST 방식을 구분하여 매핑할 경우 **
	  * @GetMapping("url")  /  @PostMapping("url") 사용하는것이 일반적
	  * (메서드 레벨에서만 작성 가능)
	  * 
	  * */
	
	// 로그인
	// 요청 시 파라미터를 얻어오는 방법 1
	// HttpServletRequest 이용
	
	
	/*
	 * // @RequestMapping("/login") // public String login(HttpServletRequest req) {
	 * // logger.info("로그인 요청됨"); // // String inputEmail =
	 * req.getParameter("inputEmail"); // String inputPw =
	 * req.getParameter("inputPw"); // // logger.debug("inputEmail : " +
	 * inputEmail); // logger.debug("inputPw : " + inputPw); // // return
	 * "redirect:/"; //sendRedirect 안써도 됨 이게 리다이렉트 구문 // }
	 */	
	
	/*
	 * // 요청 시 파라미터를 얻어오는 방법 2 // -> @RequestParam 어노테이션 사용
	 * 
	 * // RequestParam("name속성값")자료형 변수명 // -> 클라이언트 요청 시 같이 전달된 파라미터를 변수에 저장 // -->
	 * 어떤 파라미터를 변수에 저장할지는 "name속성값"을 이용해 지정
	 * 
	 * // 매개변수 지정 시 데이터 타입 파싱을 자유롭게 진행 할 수 있음 ex) String -> int로 변환
	 * 
	 * // [속성]
	 * value : input 태그의 name 속성값
	 * 
	 * required : 입력된 name 속성값이 필수적으로 파라미터에 포함되어야 하는지 지정
	 * 			required=true/false (default값은 true)
	 * 
	 * defaultValue : required가 false인 상태에서 parameter가 존재하지 않을 경우의 값을 지정
	 * required가 false인데 defaultValue가 작성되지 않으면 null 반환
	 * 
	 */
	
	
	/*
	 * @RequestMapping("/login") public String login(@RequestParam("inputEmail")
	 * String inputEmail,
	 * 
	 * @RequestParam("inputPw") String inputPw,
	 * 
	 * @RequestParam(value = "inputName", required = false, defaultValue =
	 * "홍길동")String inputName ) {
	 * 
	 * logger.debug("inputEmail : " + inputEmail); logger.debug("inputPw : " +
	 * inputPw); logger.debug("inputName : " + inputName);
	 * 
	 * // email 숫자만 입력받는다고 가정 // logger.debug(inputEmail + 100 + "");
	 * 저기서 +""는 문자열로 변환한다는 뜻
	 * 
	 * return "redirect:/";
	 * 
	 * }
	 */
	
	// 요청 시 파라미터를 얻어오는 방법 3
	// -> @ModelAttribute 어노테이션 사용
	
	// @ModelAttribute VO타입 변수명
	// -> 파라미터 중 name 속성값이 VO의 필드와 일치하면
	// 해당 VO 객체의 필드에 값을 세
	
	// *** @ModelAttribute를 이용해서 객체에 값을 직접 담는 경우에 대한 주의 사항 ***
	// -- 반드시 필요한 내용
	// -- VO 기본생성자
	// -- VO필드에 대한 Setter
	
	@PostMapping("/login")
	public String login( @ModelAttribute Member inputMember) {
		
		logger.info("로그인 기능 수행됨");
		
		// 아이디, 비밀번호가 일치하는 회원정보를 조회하는 service 호출 후 결과 반환 받기
		Member loginMember = service.login(inputMember);
		
		return "redirect:/";
	}
	
	// 회원 가입 화면 전환
	@GetMapping("/signUp") // get방식 : /comm/member/signUp 요청
	public String signUp() {
		
		return "member/signUp";
	}	
	
}