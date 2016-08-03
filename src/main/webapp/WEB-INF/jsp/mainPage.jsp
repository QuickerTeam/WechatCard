<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="com.dsb.utils.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>learn Bootstrap</title>
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>bootstrap-theme.css">
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>main.css">
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>cssreset.css">
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>font-awesome.css">
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>font-awesome-ie7.css">
	<script type="text/javascript" src = "<%= StaticConstant.jsFilePath %>jquery.js"></script>
	<script type="text/javascript" src = "<%= StaticConstant.jsFilePath %>bootstrap.js"></script>
</head>
<body>
		<p class="suup smaview">创建成功</p>
		<p class="errup smaview" ></p>
		<header>
			<div class="container">

				

				<img src="<%= StaticConstant.imgFilePath %>logo.png">

				<span class="view">袋鼠帮卡券发送平台</span>
			</div>
		</header>
		<nav class="leftMenu">
			<ul>
				<li id="moreCard">创建卡券<i class="icon-double-angle-left fontSign"></i></li>
				<li class="cardView">
					<ul>
						<li class="boxli">
							 <div class="boxshandowTop"></div>
						</li>
						<li class="card thisCard" id="groupBuying">团购券</li>
						<li class="card" id="voucher">代金券</li>
						<li class="card" id="countCard">折扣券</li>
						<li class="card" id="giftCard">礼品券</li>
						<li class="card" id="coupon">优惠券</li>
						<li class="boxli">
							<div class="boxshandowButtom"></div>
						</li>
					</ul>
				</li>
				<li id = "sendCardM" class="leftbtn">投放卡券</li>
				<li id = "cancelCardM" class="leftbtn">核销卡券</li>
				<li id = "manageCardM" class="leftbtn">管理卡券</li>
			</ul> 
		</nav>
		
		<div class="sbody">
			
		</div>
		<footer></footer>

	</div>

	<script type="text/javascript" src="<%= StaticConstant.jsFilePath %>main.js"></script>
</body>
</html>