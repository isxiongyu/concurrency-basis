package cn.xiongyu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: TestController
 * Package: cn.xiongyu.controller
 * Description:
 * Date: 19-8-7 下午10:17
 * Author: xiongyu
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
