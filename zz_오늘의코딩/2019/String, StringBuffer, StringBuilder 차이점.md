# String, StringBuffer, StringBuilder 설명

공통점 : 모두다 String을 관리하는 Class

String
 
* String은 immutable -> String을 new연산을 통해 생성되면 인스턴스의 메모리 공간은 변하지 않습니다. 
Concat, + 연산 등으로 수정시 새로운 String객체를 new로 만들어서 새로운 메모리에 공간을 만든다. 
새로운 문자열을 만들면 기존 문자열은 GC대상이 되고 내부적으로 char배열을 사용하여 계속해서 객체를 만드는 오버헤드가 발생하면서 
성능이 떨어진다.  

* immutable 성격상 단순하게 읽는 조회연산시 타 클래스보다 빠르게 읽을 수 있다 (멀티쓰레드환경에서도 동기화를 신경 쓸 필요가 없다)

사용처
* String Class는 멀티쓰레드 환경에서 사용, 문자열 연산이 적고 조회가 많을 때.

StringBuffer & StringBuilder

* StringBuffer와 StringBuilder 클래스는 mutable. 문자열 연산에 있어서 클래스를 한번만 만들고(new), 연산이 필요할 때 크기를 변경시켜서 문자열을 변경한다.  
StringBuffer는 멀티쓰레드환경에서 synchronized키워드가 가능하므로 동기화가 가능하다.  즉, thread-safe하다.  
StringBuilder는 동기화를 지원하지 않기 때문에 멀티쓰레드환경에서는 적합하지 않다.  대신 StringBuilder가 동기화를 고려하지 않기 때문에 싱글쓰레드 환경에서 StringBuffer에 비해 연산처리가 빠르다.

사용처
* 문자열 연산이 많을 때 멀티쓰레드환경에서는 StringBuffer, 싱글쓰레드또는 쓰레드를 신경쓰지 않아도 되는 환경에서는 StringBuilder를 사용하는 것이 적절하다.  

* JDK1.5이상부터 String에서 +연산으로 작성하더라도 StringBuilder로 컴파일하게 만듬 String클래스의 객체 생성하는 부분을 동일하므로 StringBuffer,StringBuilder 사용이 필요함.

* StringBuffer, StringBuilder의 경우 buffer size를 초기화 중 생성, 확장 오버로드가 걸려 버퍼사이즈를 잘못 초기화할 경우 성능이 저하의 우려가 있습니다.

