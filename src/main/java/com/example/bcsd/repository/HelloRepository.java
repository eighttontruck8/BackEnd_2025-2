// (controller->) service -> db에 필요한 자료 요청
// only 자료구조만 저장해놓자.
package com.example.bcsd.repository;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository {
    public String getName() {
        return "윤해인";
    }

    public int getAge() {
        return 24;
    }
}
