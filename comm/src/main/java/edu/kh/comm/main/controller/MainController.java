package edu.kh.comm.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/main")
	public String mainForward() {
		
		// Dispatcher servlet -> view resorver로 이동 거기서 접두사 접미사 붙여서 해당페이지로 이동
		return "common/main";
	}
}
