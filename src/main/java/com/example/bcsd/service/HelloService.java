// service는 모델. 데이터 처리, 가공 등 실질적인 >로직<을 수행함
// 모든 머리쓰는 것들은 service에게 맡겨주세요...

package com.example.bcsd.service;
import com.example.bcsd.repository.HelloRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HelloService {
    private final HelloRepository helloRepository;

    // 생성자 주입
    public HelloService(HelloRepository helloRepository){
        this.helloRepository = helloRepository;
    }
    // 1) /introduce에 html 반환
    // 필요없음

    // 2) /introduce/string?name=이름 -> "안녕하세요. 제 이름은 000 입니다."
    // 필요없음

    // 3) /introduce/json -> {"name": "...", "age": ...}
    public Map<String, Object> getHelloJson(){
        Map<String, Object> output = new HashMap<>();
        output.put("name", helloRepository.getName());
        output.put("age", helloRepository.getAge());
        return output;
    }
}
