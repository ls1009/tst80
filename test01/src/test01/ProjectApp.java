package test01;

import java.io.InputStream;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import test01.command.Session;
import test01.controller.AuthController;
import test01.controller.BoardController;
import test01.controller.MemberController;
import test01.controller.ProjectController;
import test01.dao.BoardDao;
import test01.dao.MemberDao;
import test01.dao.ProjectDao;
import test01.domain.Member;

public class ProjectApp {

  static MemberDao memberDao = new MemberDao();
  static ProjectDao projectDao = new ProjectDao();
  static BoardDao boardDao = new BoardDao();
  
  static Session session = new Session();
  static AuthController ac = new AuthController();
  static ProjectController pc = new ProjectController();
  static BoardController bc = new BoardController();
  static MemberController mc = new MemberController();
  
  static Scanner keyScan = new Scanner(System.in);
  
  public static void main(String[] args) {
    
    try {
      InputStream inputStream = Resources.getResourceAsStream(
          "test01\\mybatis-config.xml");
      projectDao.setSqlSessionFactory(
          new SqlSessionFactoryBuilder().build(inputStream));
    } catch (Exception e) {
      System.out.println("mybatis 준비 중 오류 발생!\n시스템을 종료하겠습니다.");
      e.printStackTrace();
      return;
    }
    
    ac.setMemberDao(memberDao);
    Member member = null;
    
    do {
      ac.service();
      member = (Member) session.getAttribute("loginUser");
      if(member.getPart().equals("팀장")) {
        one();
      }
      
      if(member.getPart().equals("팀원")) {
        two();
      }
            
    } while (!member.equals("quit"));
    
    
  }// main
  
  
  public static void one() {
    String input = null;
    while(true) {
      System.out.println("1)프로젝트 관리");
      System.out.println("2)팀원 관리");
      System.out.println("3)게시물 관리");
      input = keyScan.nextLine();
      if(input.equals("1")) {
        mc.service();
      } else if(input.equals("2")) {
    	pc.service();
      } else if(input.equals("3")) {
        bc.service();
      } else {
        System.out.println("없는 명령입니다.");
      }
    }
  }
  
  public static void two() {
    String input = null;
    while(true) {
      System.out.println("1)프로젝트 리스트");
      System.out.println("2)팀원 리스트");
      System.out.println("3)게시물 관리");
      input = keyScan.nextLine();
      if(input.equals("1")) {
    	  mc.service();
      } else if(input.equals("2")) {
    	  pc.service();
      } else if(input.equals("3")) {
    	  bc.service();
      } else {
        System.out.println("없는 명령입니다.");
      }
      
    }
  }

}
