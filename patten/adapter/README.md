어댑터 패턴
---

220V 를 사용하는 쓰던 기기들을, 어댑터를 사용하면 110V 를 쓰는곳에 가서도 그대로 쓸 수 있다. 이처럼, 호환성이 없는 인터페이스 때문에 함께 동작할 수 없는 클래스들이 함께 작동하도록 해주는 패턴이 어댑터 패턴이라고 할 수 있겠다. 이를 위해 어댑터 역할을 하는 클래스를 새로 만들어야 한다.

기존에 있는 시스템에 새로운 써드파티 라이브러리가 추가된다던지, 레거시 인터페이스를 새로운 인터페이스로 교체하는 경우에 코드의 재사용성을 높일 수 있는 방법이 어댑터 패턴을 사용하는 것이다.

![Alt text](/img/adapter_01.jpg)

**개념 : 이미 제공하는것과 필요한것 과의 사이의 간격을 메우는 패턴**

1) 의도  
클래스, 인터페이스를 클라이언트라 원하는 형태의 인터페이스로 변환. **호환성이 없는 클래스를 개조하여 함께 작동하도록 해준다.**
> * 클래스에 의한 클래스 어댑터 패턴
> * 인스턴스에 의한 객체 어댑터 (위임을 이용함)
2) 

2) 활용
* 기존 클래스를 사용해야 하나 인터페이스가 수정이되어야하는 경우
* 이미 만들어진 것을 재사용할때 참조하고 있는 라이브러리를 수정할 수 없는 경우
* 서브클래스 상속으로 인터페이스를 모두 변경하는 것이 어렵다면 객체 어댑터방식으로 인터페이스 변경

3) 결과 
클래스 어댑터 - 한 어댑터 객체만 만들어 내고 adaptee에 대한 어떤 참조자도 필요하지 않음. (Adaptee에 정의된 행위 재정의 가능)
객체 어댑터 – 어댑터 클래스가 하나만 존재해도 많은 adaptee 클래스와 동작 가능. (Adaptee에 정의된 행위 재정의 어려움)

* 클래스 어댑터 패턴
> * 단점 : 상속을 활용하기 때문에 유연하지 못함.
```{.java}
public class Member extends Adapter, Targer{
        /*java 다중상속 지원 X */
} 
```
단 편법으로 다중상속 지원  

> * 장점 : 어댑터를 전체를 다시 구현할 필요가 없다(속도 향상)

* 클래스에 의한 Adapter 패턴 (상속)  
![Alt text](/img/adapter_02.png)  

I) Print 인터페이스 구현 (Target 역할)
```{.java}
public interface Print {
    public abstract void printWeak();
    public abstract void printStrong();
}
```
II) Banner 클래스 구현 (Adaptee 역할)
```{.java}
public class Banner {
    private String string;
    public Banner(String string) {
        this.string = string;
    }
    public void showWithParen() {
        System.out.println("(" + string + ")");
    }
    public void showWithAster() {
        System.out.println("*" + string + "*");
    }
}
```
III) PrintBanner 구현 (Adapter 역할)
```{.java}
public class PrintBanner extends Banner implements Print {
    public PrintBanner(String string) {
        super(string);
    }
    public void printWeak() {
        showWithParen();
    }
    public void printStrong() {
        showWithAster();
    }
}
```
(a) 어뎁터의 역할을 완수합니다.
(b) 준비된 Banner 클래스를 확장(extends)해서 showWithParen 메소드와 showWithAster 메소드를 상속합니다.
(c) 또한 필요한 Print 인터페이스를 구현(implement)해서 PrintWeak 메소드와 PrintStrong 메소드를 구현합니다.

* 인스턴스에 의한 Adapter 패턴 (위임)
> * 단점: 어댑터(Adapter)클래스의 대부분의 코드를 구현해야하기때문에 효율적이지 못하다
> * 장점: 구성(Composition)을 사용하기 때문에 더 뛰어나다.(유연하다)  
![Alt text](/img/adapter_03.png)  
I) Print 인터페이스 구현 (Target 역할)
```{.java}
public interface Print {
    public abstract void printWeak();
    public abstract void printStrong();
}
```
II) Banner 클래스 구현 (Adaptee 역할)
```{.java}
public class Banner {
    private String string;
    public Banner(String string) {
        this.string = string;
    }
    public void showWithParen() {
        System.out.println("(" + string + ")");
    }
    public void showWithAster() {
        System.out.println("*" + string + "*");
    }
}
```
III) PrintBanner 구현 (Adapter 역할)
```{.java}
public class PrintBanner extends Print {
    private Banner banner;
    public PrintBanner(String string) {
        this.banner = new Banner(string);
    }
    public void printWeak() {
        banner.showWithParen();
    }
    public void printStrong() {
        banner.showWithAster();
    }
}
```

* 관련 패턴 
 > * Decorator 패턴  
 다른 인터페이스의 변경없이도 객체에 새로운 행위를 추가할 수 있도록 한다. 이것이 adapter보다는 애플리케이션을 위한 훨씬 좋은 방법이고, 순수한 어댑터로는 불가능한 재귀적 합성을 가능하게 한다