package test01.command;

import java.util.Scanner;

public class CommandUtil {
  static public boolean confirm(Scanner sc, String m) {
    String input = null;
    while(true) {
      System.out.print(m);
      input = sc.nextLine().toLowerCase();
      
      if(input.equals("y")) {
        return true;
      } else if(input.equals("n")) {
        return false;
      } else {
        System.out.println("다시 입력 하십시오.");
      }
    }
  }
}
