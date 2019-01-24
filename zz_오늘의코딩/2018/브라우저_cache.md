####Chrome 브라우저.

**on-disk cache**

* memory 에 캐시를 저장하지 않고 disk 에 캐시를 저장하여 사용하는 방식.
* response header 에 Content-Encoding: gzip 일 경우 사용.
* request header 에 Accept-Encoding: gzip 이 있을 경우 사용 가능.
* gzip 압축 전송을 하기 위해 server side 의 cpu 사용량이 증가함.
* in-memory cache 에 비해 속도가 느림.
* network 데이터 전송량이 줄어들게 됨.
* gzip 의 압축 해제에 걸리는 시간이 용량 압축에 의한 시간 단축에 비해 길다면 사용자의 속도는 더 느려질 수 있음.

**in-memory cache**

* memory 에 캐시를 저장하여 사용하는 방식.
* response header 에 Content-Encoding 이 gzip 이 아닐 경우 사용.
* 빠른 속도.