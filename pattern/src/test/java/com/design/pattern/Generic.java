package com.design.pattern;

public class Generic {
  public static void main(String[] args) {
    MyList<Integer> myList = new MyList<>();

    myList.add(100);
    System.out.println(myList.get(0));

    myList.add("hello"); // 제네릭으로 제한을 건 Integer이외의 타입은 컴파일 에러 발생!

  }
}

class MyList<T>{
  Object[] elements;

  public MyList(){
    this.elements = new Object[10];
  }

  public MyList(int arraySize){
    this.elements = new Object[arraySize];
  }

  public boolean add(T element){
    this.elements[0] = element;
    return true;
  }

  public T get(int index){
    return (T) this.elements[index];
  }
}