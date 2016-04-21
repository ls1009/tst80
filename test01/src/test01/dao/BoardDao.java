package test01.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import test01.domain.Board;

public class BoardDao {
	SqlSessionFactory sqlSessionFactory;
	public BoardDao() {
	}
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public List<Board> selectList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("BoardDao.selectList");
		} finally {
			sqlSession.close();
		}
	}

	public Board selectOne(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("no", no);

			return sqlSession.selectOne("BoardDao.selectOne", paramMap);
		} finally {
			sqlSession.close();
		}
	}

	public Board selectOneByEmail(String email) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("email", email);

			return sqlSession.selectOne("BoardDao.selectOne", paramMap);
		} finally {
			sqlSession.close();
		}
	}

	public int insert(Board Board) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		try {
			return sqlSession.insert("BoardDao.insert", Board);

		} finally {
			sqlSession.close();
		}
	}

	public int update(Board Board) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.update("BoardDao.update", Board);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	public int delete(int no) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.delete("BoardDao.delete", no);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}

	public boolean isBoard(String email, String password) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			HashMap<String, String> paramMap = new HashMap<>();
			paramMap.put("email", email);
			paramMap.put("password", password);
			int count = sqlSession.selectOne("BoardDao.isBoard", paramMap);
			if (count > 0)
				return true;
			else
				return false;
		} finally {
			sqlSession.close();
		}
	}
}
