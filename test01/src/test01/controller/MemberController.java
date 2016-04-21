package test01.controller;

import java.util.List;
import java.util.Scanner;

import test01.annotation.Controller;
import test01.annotation.RequestMapping;
import test01.command.CommandUtil;
import test01.dao.MemberDao;
import test01.domain.Member;

@Controller
@RequestMapping("member/")
public class MemberController {
  private MemberDao memberDao;

  public void setMemberDao(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @RequestMapping("add.do")
  public void add(Scanner keyScan) {
    Member member = new Member();

    
    System.out.print("이름? ");
    member.setName(keyScan.nextLine());

    System.out.print("이메일? ");
    member.setEmail(keyScan.nextLine());

    System.out.print("암호? ");
    member.setPassword(keyScan.nextLine());

    System.out.print("전화? ");
    member.setTel(keyScan.nextLine());
    
    System.out.print("역할? ");
    member.setPart(keyScan.nextLine());
    

    if (CommandUtil.confirm(keyScan, "저장하시겠습니까?")) {
      try {
        memberDao.insert(member);
        System.out.println("저장하였습니다.");
      } catch (Exception e) {
        System.out.println("데이터를 저장하는데 실패했습니다.");
        e.printStackTrace();
      }
    } else {
      System.out.println("저장을 취소하였습니다.");
    }
  }
  
  @RequestMapping("delete.do")
  public void delete(Scanner keyScan) {
    try {
      System.out.print("삭제할 회원 번호는? ");
      int no = Integer.parseInt(keyScan.nextLine());
  
      if (CommandUtil.confirm(keyScan, "정말 삭제하시겠습니까?")) {
        int count = memberDao.delete(no);
        if (count > 0) {
          System.out.println("삭제하였습니다.");
        } else {
          System.out.println("유효하지 않은 번호이거나, 이미 삭제된 항목입니다.");
        }
      } else {
        System.out.println("삭제를 취소하였습니다.");
      }
      
    } catch (Exception e) {
      System.out.println("데이터 처리에 실패했습니다.");
    }
  }
  
  @RequestMapping("list.do")
  public void list() {
    try {
      List<Member> members = memberDao.selectList();
      
      for (Member member : members) {
        System.out.printf("%d, %s, %s, %s, %s\n", member.getNo(),
            member.getName(), member.getEmail(), member.getTel(), member.getPart());
      }
    } catch (Exception e) {
      throw new RuntimeException("회원 데이터 로딩 실패!", e);
    }
  }
  
  @RequestMapping("update.do")
  public void update(Scanner keyScan) {
    try {
      System.out.print("변경할 회원 번호는? ");
      int no = Integer.parseInt(keyScan.nextLine());
  
      Member member = memberDao.selectOne(no);
      if (member == null) {
        System.out.println("유효하지 않은 번호입니다.");
        return;
      }
      
      System.out.printf("이름(%s)? ", member.getName());
      member.setName(keyScan.nextLine());
  
      System.out.printf("이메일(%s)? ", member.getEmail());
      member.setEmail(keyScan.nextLine());
  
      System.out.printf("암호(%s)? ", member.getPassword());
      member.setPassword(keyScan.nextLine());
  
      System.out.printf("전화(%s)? ", member.getTel());
      member.setTel(keyScan.nextLine());
      
      System.out.printf("역할(%s)? ", member.getPart());
      member.setPart(keyScan.nextLine());
  
      if (CommandUtil.confirm(keyScan, "변경하시겠습니까?")) {
        int count = memberDao.update(member);
        if (count > 0) {
          System.out.println("변경하였습니다.");
        } else {
          System.out.println("유효하지 않은 번호이거나, 이미 삭제된 항목입니다.");
        }
      } else {
        System.out.println("변경을 취소하였습니다.");
      }
    } catch (Exception e) {
      System.out.println("데이터 처리에 실패했습니다.");
    }
  }
}










