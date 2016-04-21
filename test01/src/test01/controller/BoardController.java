package test01.controller;

import java.util.List;
import java.util.Scanner;

import test01.command.CommandUtil;
import test01.dao.BoardDao;
import test01.domain.Board;

public class BoardController {
	private BoardDao boardDao;
	private Scanner sc = new Scanner(System.in);

	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	public String prompt() {
		System.out.print("게시판 관리> ");
		return sc.nextLine().toLowerCase();
	}

	public void service() {
		String input = null;
		do {
			input = prompt();
			
			if (input.equals("add")) {
				doAdd(sc);
			} else if (input.equals("list")) {
				doList();
			} else if (input.equals("update")) {
				doUpdate(sc);
			} else if (input.equals("delete")) {
				doDelete(sc);
			} else if (input.equals("quit")){
				System.out.println("종료");
			} else {
			System.out.println("잘못된 명령어입니다.");
			}
		} while (!input.equals("quit"));
	}

	public void doAdd(Scanner sc) {
		Board board = new Board();
		System.out.print("프로젝트번호? ");
		board.setPno(Integer.parseInt(sc.nextLine()));
		System.out.print("제목? ");
		board.setTitle(sc.nextLine());
		System.out.print("내용? ");
		board.setContent(sc.nextLine());
		System.out.print("비밀번호? ");
		board.setPassword(sc.nextLine());
		System.out.print("작성자? ");
		board.setWriter(sc.nextLine());
		if (CommandUtil.confirm(sc, "저장하시겠습니까?")) {
			try {
				boardDao.insert(board);
				System.out.println("저장하였습니다.");
			} catch (Exception e) {
				System.out.println("데이터를 저장할 수 없습니다.");
				e.printStackTrace();
			}
		} else {
			System.out.println("저장을 취소하였습니다.");
		}
	}

	public void doList() {
		try {
			List<Board> boards = boardDao.selectList();

			for (Board board : boards) {
				System.out.printf("%d, %d, %s, %s, %s, %s, %s\n", board.getPno(), board.getNo(), board.getTitle(),
						board.getContent(), board.getCreateDate(), board.getEditDate(), board.getWriter());
			}
		} catch (Exception e) {
			throw new RuntimeException("게시물 데이터 로딩 실패!", e);
		}
	}

	public void doUpdate(Scanner sc) {
		try {
			System.out.print("변경할 게시물 번호?");
			int no = Integer.parseInt(sc.nextLine());

			Board board = boardDao.selectOne(no);
			if (board == null) {
				System.out.println("유효하지 않은 번호입니다.");
				return;
			}
			System.out.printf("제목(%s)? ", board.getTitle());
			board.setTitle(sc.nextLine());
			System.out.printf("내용(%s)? ", board.getContent());
			board.setContent(sc.nextLine());
			System.out.printf("비밀번호(%s)? ", board.getPassword());
			board.setPassword(sc.nextLine());

			if (CommandUtil.confirm(sc, "변경하시겠습니까?")) {
				int count = boardDao.update(board);
				if (count > 0) {
					System.out.println("변경하였습니다.");
				} else {
					System.out.println("유효하지 않은 번호이거나, 삭제된 번호입니다.");
				}
			} else {
				System.out.println("변경을 취소하였습니다.");
			}
		} catch (Exception e) {
			System.out.println("데이터 처리에 실패했습니다.");
			e.printStackTrace();
		}
	}

	public void doDelete(Scanner sc) {
		try {
			System.out.print("삭제할 게시물 번호?");
			int no = Integer.parseInt(sc.nextLine());

			if (CommandUtil.confirm(sc, "삭제하시겠습니까?")) {
				int count = boardDao.delete(no);
				if (count > 0) {
					System.out.println("삭제하였습니다.");
				} else {
					System.out.println("유효하지 않은 번호이거나, 삭제된 번호입니다.");
				}
			} else {
				System.out.println("삭제를 취소하였습니다.");
			}
		} catch (Exception e) {
			System.out.println("데이터 처리에 실패했습니다.");
		}
	}
}
