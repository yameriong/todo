# Extract Method

* 그룹으로 함께 묶을 수 있는 코드조각이 있다면 코드의 목적이 잘 드러나도록 메소드를 분리, 이름을 짓습니다.  
* 하나의 메소드는 하나의 값만 리턴하는 것을 권장한다.  

* 짧고 이해하기 쉬운 이름으로 된 메소드의 이점   
>* 재사용될 확률이 높아짐
>* 메소드를 사용하는 메소드쪽에서 봤을 때 일련의 주석을 읽는 것 과 같은 느낌을 준다.
>* 이해하기 쉽다
>* 오버라이드 하기가 쉽다.


```{.java}
Before

void print (double point){
  printBar();
  System.out.println("amount : " + amount);
}
```

```{.java}
After

void print (double point){
  printBar();
  printDetails(point);
}

void printDetails (double point){
  System.out.println("amount : " + amount);
}
```


* 하나의 메소드는 하나의 값만 리턴하는 것을 권장한다.
* 짧고 이해하기 쉬운 이름으로 된 메소드. 
