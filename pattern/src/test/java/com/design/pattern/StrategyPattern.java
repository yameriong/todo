package com.design.pattern;


import org.junit.Test;

public class StrategyPattern {

//테스트

  @Test
    public void test(){

      D d = new D(); //클라이언트 객체 생성
      d.setA(new B()); //전략 전달
      System.out.println(d.playStrategy()); //전략 실행
      d.setA(new C()); //전략 교체
      System.out.println(d.playStrategy()); //전략 실행


    }
}

interface A{

  public String getString();

}



//인터페이스 A 구현

class B implements A{

  @Override
  public String getString(){
    return "Hello World";
  }
}



//인터페이스 A 구현

class C implements A{

  @Override
  public String getString(){
    return "Hello LichKing";
  }
}



//클라이언트 클래스

class D{
  A a;

  public void setA(A a){
    this.a = a;
  }

  public String playStrategy(){
    return a.getString();
  }
}
