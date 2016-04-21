package test01.controller;

import java.util.List;
import java.util.Scanner;

import test01.command.CommandUtil;
import test01.dao.MemberDao;
import test01.domain.Member;

public class MemberController {
  private MemberDao memberDao;
  Scanner sc = new Scanner(System.in);

  public void setMemberDao(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
	public String prompt() {
		System.out.print("프로젝트 관리> ");
		return sc.nextLine().toLowerCase();
	}

	public void service() {
		String input = null;
		do {
			input = prompt();
			
			if (input.equals("add")) {
				add(sc);
			} else if (input.equals("list")) {
				list();
			} else if (input.equals("update")) {
				update(sc);
			} else if (input.equals("delete")) {
				delete(sc);
			} else if (input.equals("quit")){
				System.out.println("종료");
			} else {
			System.out.println("잘못된 명령어입니다.");
			}
		} while (!input.equals("quit"));
	}
 

  public void add(Scanner sc) {
    Member member = new Member();

    
    System.out.print("이름? ");
    member.setName(sc.nextLine());

    System.out.print("이메일? ");
    member.setEmail(sc.nextLine());

    System.out.print("암호? ");
    member.setPassword(sc.nextLine());

    System.out.print("전화? ");
    member.setTel(sc.nextLine());
    
    System.out.print("역할? ");
    member.setPart(sc.nextLine());
    

    if (CommandUtil.confirm(sc, "저장하시겠습니까?")) {
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
  

  public void delete(Scanner sc) {
    try {
      System.out.print("삭제할 회원 번호는? ");
      int no = Integer.parseInt(sc.nextLine());
  
      if (CommandUtil.confirm(sc, "정말 삭제하시겠습니까?")) {
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
  

  public void update(Scanner sc) {
    try {
      System.out.print("변경할 회원 번호는? ");
      int no = Integer.parseInt(sc.nextLine());
  
      Member member = memberDao.selectOne(no);
      if (member == null) {
        System.out.println("유효하지 않은 번호입니다.");
        return;
      }
      
      System.out.printf("이름(%s)? ", member.getName());
      member.setName(sc.nextLine());
  
      System.out.printf("이메일(%s)? ", member.getEmail());
      member.setEmail(sc.nextLine());
  
      System.out.printf("암호(%s)? ", member.getPassword());
      member.setPassword(sc.nextLine());
  
      System.out.printf("전화(%s)? ", member.getTel());
      member.setTel(sc.nextLine());
      
      System.out.printf("역할(%s)? ", member.getPart());
      member.setPart(sc.nextLine());
  
      if (CommandUtil.confirm(sc, "변경하시겠습니까?")) {
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


