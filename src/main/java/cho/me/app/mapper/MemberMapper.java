package cho.me.app.mapper;

import org.apache.ibatis.session.SqlSession;

import cho.me.app.dto.MemberDto;

public class MemberMapper /*implements IMemberMapper*/{
	  private SqlSession sqlSession;

	  public void setSqlSession(SqlSession sqlSession) {
	    this.sqlSession = sqlSession;
	  }

	  public MemberDto getMember() {
		System.out.println(sqlSession);
	    return sqlSession.selectOne("cho.me.app.mapper.MemberMapper.selectMemberOne");
	  }

	  public void putMember(){
	  	sqlSession.insert("cho.me.app.mapper.MemberMapper.insertMember");
	  }
}
