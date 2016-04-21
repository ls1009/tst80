package test01.controller;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import command.CommandUtil;
import test01.dao.ProjectDao;
import test01.domain.Project;

public class ProjectController {
  private ProjectDao pDao;
  public void setpDao(ProjectDao pDao) {
    this.pDao = pDao;
  }
  
  public void add(Scanner keyScan) {
    Project p = new Project();
    
    System.out.print("프로젝트 번호? ");
    p.setNo(Integer.parseInt(keyScan.nextLine()));
    
    System.out.print("제목? ");
    p.setTitle(keyScan.nextLine());

    System.out.print("시작일? ");
    p.setSd(Date.valueOf(keyScan.nextLine()));

    System.out.print("종료일? ");
    p.setEd(Date.valueOf(keyScan.nextLine()));

    System.out.print("설명? ");
    p.setDec(keyScan.nextLine());
    
    System.out.print("팀 번호? ");
    p.setTeamNo(Integer.parseInt(keyScan.nextLine()));
    
    System.out.print("담당 강사 번호? ");
    p.setTeachNo(Integer.parseInt(keyScan.nextLine()));
    
    if (CommandUtil.confirm(keyScan, "저장하시겠습니까?")) {
      try {
        pDao.insert(p);
        System.out.println("저장하였습니다.");
      } catch (Exception e) {
        System.out.println("데이터를 저장하는데 실패했습니다.");
      }
    } else {
      System.out.println("저장을 취소하였습니다.");
    }
  }
  
  public void delete(Scanner keyScan) {
    try {
      System.out.print("삭제할 회원 번호는? ");
      int no = Integer.parseInt(keyScan.nextLine());
  
      if (CommandUtil.confirm(keyScan, "정말 삭제하시겠습니까?")) {
        int count = pDao.delete(no);
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
      List<Project> Projects = pDao.selectList();
      
      for (Project p : Projects) {
        System.out.printf("%d, %s, %s, %s\n", p.getNo(),
            p.getSd(), p.getEd(), p.getDec());
      }
    } catch (Exception e) {
      throw new RuntimeException("프로젝트 데이터 로딩 실패!", e);
    }
  }
  
  public void update(Scanner keyScan) {
    try {
      System.out.print("변경할 프로젝트 번호는? ");
      int no = Integer.parseInt(keyScan.nextLine());
  
      Project project = pDao.selectOne(no);
      if (project == null) {
        System.out.println("유효하지 않은 번호입니다.");
        return;
      }
      
      System.out.printf("제목(%s)? ", project.getTitle());
      project.setTitle(keyScan.nextLine());
  
      System.out.printf("시작일(%s)? ", project.getSd());
      project.setSd(Date.valueOf(keyScan.nextLine()));
  
      System.out.printf("종료일(%s)? ", project.getEd());
      project.setEd(Date.valueOf(keyScan.nextLine()));
  
      System.out.printf("설명(%s)? ", project.getDec());
      project.setDec(keyScan.nextLine());
      
      System.out.printf("담당 강사 번호(%s)? ", project.getTeachNo());
      project.setTeachNo(Integer.parseInt(keyScan.nextLine()));
  
      if (CommandUtil.confirm(keyScan, "변경하시겠습니까?")) {
        int count = pDao.update(project);
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
      e.printStackTrace();
    }
  }
  
}//
