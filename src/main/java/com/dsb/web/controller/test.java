package com.dsb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {
	@RequestMapping(value = "/aaa")
	private String aaa() {
		// TODO Auto-generated method stub
		return "hello";
	}

}
