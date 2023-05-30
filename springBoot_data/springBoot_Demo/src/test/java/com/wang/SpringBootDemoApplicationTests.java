package com.wang;

import com.wang.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootDemoApplicationTests {

	//使用UserService
	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
		//userService.addUser("lihua","11111");
		userService.findUserName("lihua");
	}



}
