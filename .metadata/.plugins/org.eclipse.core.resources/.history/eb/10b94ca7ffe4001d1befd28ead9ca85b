package edu.kh.comm.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.comm.member.model.vo.Member;

@Repository
public class MyPageDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private Logger logger = LoggerFactory.getLogger(MyPageDAO.class);

	
	public String checkPw(Member loginMember) {
		
		
		return sqlSession.selectOne("myPageMapper.checkPw", loginMember);
	}

	public int changePw(Member loginMember) {
		
		return sqlSession.update("myPageMapper.changePw", loginMember);
	}

	public int changeInfo(Member loginMember) {
		
		return sqlSession.update("myPageMapper.changeInfo", loginMember);
	}

	public int secession(Member loginMember) {
		
		return sqlSession.delete("myPageMapper.deleteInfo", loginMember);
	}
	
}
