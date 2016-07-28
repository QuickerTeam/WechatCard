
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
	<title>learn Bootsrap</title>
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>cssbootsrap-theme.css">
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>main.css">
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>cssreset.css">
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>font-awesome.css">
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>font-awesome-ie7.css">
	<script type="text/javascript" src = "<%= StaticConstant.jsFilePath %>jquery.js"></script>
	<script type="text/javascript" src = "<%= StaticConstant.jsFilePath %>bootsrap.js"></script>
</head>
<body>
		<p class="suup smaview">创建成功</p>
		<p class="errup smaview" ></p>
		<header>
			<div class="container">
<<<<<<< HEAD
				<img src="<%= StaticConstant.imgFilePath %>>logo.png">
=======
				<img src="<%= StaticConstant.imgFilePath %>logo.png">
>>>>>>> 53d53cc5a45503e6cc554f4d049f171063069c12
				<span class="view">袋鼠帮卡券发送平台</span>
			</div>
		</header>
		<nav class="leftMenu">
			<ul>
				<li id="moreCard">创建卡券<i class="icon-double-angle-left fontSign"></i></li>
				<li class="cardView">
					<ul>
						<li class="card thisCard" id="groupBuying">团购券</li>
						<li class="card" id="voucher">代金券</li>
						<li class="card" id="countCard">折扣券</li>
						<li class="card" id="giftCard">礼品券</li>
						<li class="card" id="coupon">优惠券</li>
					</ul>
				</li>
				<li>投放卡券</li>
				<li>核销卡券</li>
				<li>管理卡券</li>
			</ul>
		</nav>
		<p class="suup smaview">创建成功</p>
		<p class="errup smaview" ></p>
		<div class="sbody">
			<div class="container">
				<div>
					<span>选择核销卡券类型</span>
					<div>
						<img src="">
						<span></span>
					</div>
				</div>
				<div class="input-group">
		        	<span class="input-group-addon">输入卡券号</span>
		         	<input type="text" class="form-control onlyForm" id="cardNumber" placeholder="上限100000000"><i class="icon"></i>
		     	</div>
			</div>
		</div>
		<footer></footer>
<<<<<<< HEAD
=======
	</div>
>>>>>>> 53d53cc5a45503e6cc554f4d049f171063069c12
	<script type="text/javascript" src="<%= StaticConstant.jsFilePath %>main.js"></script>
</body>
</html>