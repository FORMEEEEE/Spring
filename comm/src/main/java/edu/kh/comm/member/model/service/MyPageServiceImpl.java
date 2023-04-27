package edu.kh.comm.member.model.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.comm.member.model.vo.Member;
import edu.kh.comm.model.dao.MyPageDAO;

@Service
public class MyPageServiceImpl implements MyPageService{

	@Autowired
	private MyPageDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	private Logger logger = LoggerFactory.getLogger(MyPageServiceImpl.class);

	@Override
	public int changePw(Member loginMember, String newPw) {
		
		int result = 0;
		
		// 비밀번호 확인
		String currentPw = dao.checkPw(loginMember);
		
		// 평문 -> 암호화가 일치할때 newPw를 loginMember에 저장
		if(bcrypt.matches(loginMember.getMemberPw(), currentPw)) {
			newPw = bcrypt.encode(newPw);
			loginMember.setMemberPw(newPw);
			result = dao.changePw(loginMember);
		}else {
			result = 0;
		}
		
		return result;
	}

	@Override
	public int changeInfo(Member loginMember, Map<String, Object> paramMap, String[] updateAddress) {
		
		loginMember.setMemberNickname((String)paramMap.get("updateNickname"));
		loginMember.setMemberTel((String)paramMap.get("updateTel"));
		
		if(loginMember.getMemberAddress() != null) {
			
			String address = String.join("", updateAddress);
			loginMember.setMemberAddress(address);
		}else {
			String address = null;
			loginMember.setMemberAddress(address);
		}
		
		
		return dao.changeInfo(loginMember);
	}

	//회원탈퇴
	@Override
	public int secession(Member loginMember, String memberPw) {
		
		int result = 0;
		
		loginMember.setMemberPw(memberPw);
		
		String checkedPw = dao.checkPw(loginMember);
		
		if(bcrypt.matches(loginMember.getMemberPw(), checkedPw)) {
			
			memberPw = bcrypt.encode(memberPw);
			
			loginMember.setMemberPw(memberPw);
			
			 result = dao.secession(loginMember);
			
		}else {
			result = 0;
		}
		
		return result;
	}
	
	
	
}
