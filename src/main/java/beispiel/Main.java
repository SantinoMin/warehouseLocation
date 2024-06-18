package beispiel;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 디버깅 예시
 */
public class Main {

  public static void main(String[] args) {
    String name = "santino";
    System.out.println("name =" + name);

  }

  static String hello(String name){
//    return "hello  "+ name;

    String mix = "hello  "+ name;
    System.out.println("mix = " + mix);
    return mix;

  }
}

