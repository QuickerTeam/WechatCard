package com.dsb.junit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.dsig.SignatureMethod;

public class Test extends HttpServlet implements SingleThreadModel {
	int i;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		i++;
		System.out.println(i);

	}
}
