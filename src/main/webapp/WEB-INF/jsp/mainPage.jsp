
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
	<link rel="stylesheet" type="text/css" href="　<%= StaticConstant.cssFilePath %>bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>cssbootsrap-theme.css">
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>main.css">
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>cssreset.css">
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>font-awesome.css">
	<link rel="stylesheet" type="text/css" href="<%= StaticConstant.cssFilePath %>font-awesome-ie7.css">
	<script type="text/javascript" src = "<%@ StaticConstant.jsFilePath %>jquery.js"></script>
	<script type="text/javascript" src = "<%@ StaticConstant.jsFilePath %>bootsrap.js"></script>
</head>
<body>
		<p class="suup smaview">åå»ºæå</p>
		<p class="errup smaview" ></p>
		<header>
			<div class="container">
				<img src="<%@ StaticConstant.imgFilePath %>/logo.png">
				<span class="view">è¢é¼ å¸®å¡å¸åéå¹³å°</span>
			</div>
		</header>
		<nav class="leftMenu">
			<ul>
				<li id="moreCard">åå»ºå¡å¸<i class="icon-double-angle-left fontSign"></i></li>
				<li class="cardView">
					<ul>
						<li class="card thisCard" id="groupBuying">å¢è´­å¸</li>
						<li class="card" id="voucher">ä»£éå¸</li>
						<li class="card" id="countCard">ææ£å¸</li>
						<li class="card" id="giftCard">ç¤¼åå¸</li>
						<li class="card" id="coupon">ä¼æ å¸</li>
					</ul>
				</li>
				<li>ææ¾å¡å¸</li>
				<li>æ ¸éå¡å¸</li>
				<li>ç®¡çå¡å¸</li>
			</ul>
		</nav>
		<p class="suup smaview">åå»ºæå</p>
		<p class="errup smaview" ></p>
		<div class="sbody">
			<div class="container">
				<div class="row">
					<span class="groupBuying col-md-12">å¢è´­å¸</span>
					 <form class="inputGroupView col-md-11" role = "form">            <!-- å¢è´­å¸-->
					 		<div class="remind">
						 	    <div class="input-group">
		        					<span class="input-group-addon">å¸å</span>
		         					<input type="text" class="form-control onlyForm" id="cardTitle" placeholder="ä¸è¶è¿9ä¸ªå­"><i class="icon"></i><!-- <i class="icon-remove warnning icon" title="å­æ°è¶è¿ä¹ä¸ªå­"></i> -->
		         					<!-- <i class="icon-ok icon"></i> -->
		     					</div>

	     					 </div>

	     					 <div class="remind">
		     					 <div class="input-group">
		        					<span class="input-group-addon">å¡å¸æ»æ°</span>
		         					<input type="text" class="form-control onlyForm" id="cardNumber" placeholder="ä¸é100000000"><i class="icon"></i>
		     					 </div>
	     					 </div>
	     					 <div class="remind">
		     					  <div class="input-group">
		        					<span class="input-group-addon">å¡å¸ä½¿ç¨æé</span>
		         					<input type="text" class="form-control onlyForm" id="useView" placeholder="ä¸è¶è¿16ä¸ªå­"><i class="icon"></i>
		     					 </div>
	     					 </div>
	     					 	 <div class="remind">
		     					 <div class="input-group">
		        					<span class="input-group-addon">å®¢æçµè¯</span>
		         					<input type="text" class="form-control onlyForm" id="serverTel" placeholder="éé¢"><i class="notMust icon">*éå¡«</i>
		     					 </div>
	     					 </div>
	     					 <div class="remind">
		     					 <div class="input-group">
		        					<span class="input-group-addon">å·ä½ä¼æ ä¿¡æ¯</span>
		         					<input type="text" class="form-control onlyForm" id="favourView" placeholder="ä¸è¶è¿18ä¸ªå­"><i class="icon"></i>
		     					 </div>
	     					 </div>
	     					 <div class="remind">
		     					 <div class="input-group">
		        					<span class="input-group-addon">ä¸ªäººé¢å¸éé¢</span>
		         					<input type="text" class="form-control onlyForm" id="numberPlace" placeholder="50"><i class="notMust icon">*éå¡«</i>
		     					 </div>
	     					 </div>
	     					 <div class="chooseTimeType">
	     					 	<span>éæ©æ¶é´æ¨¡å¼</span>
	     					 	<button class="btn btn-info" type="button" title="ç¹å»åæ¢" id="changeType">åæ¢</button>
	     					 </div>
	     					 <div class="showType">
	     					 	<ul class="typeView">
	     					 		<li class="typeOne" >
				     					  <div class="inputviews">
					     					 <div class="input-group">
					        					<span class="input-group-addon">å¡å¸ä½¿ç¨å¼å§æ¶é´</span>
					         					<input type="text" class="form-control formTime" id="starTimeY"  placeholder="number">
					         					<span class="input-group-addon">å¹´</span>
					         					<input type="text" class="form-control formTime" id="starTimeM"  placeholder="number">
					         					<span class="input-group-addon">æ</span>
					         					<input type="text" class="form-control formTime" id="starTimeD"  placeholder="number">
					         					<span class="input-group-addon">æ¥</span>
					     					 </div>
				     					 </div>
				     					 <div class="inputviews">
					     					 <div class="input-group">
					        					<span class="input-group-addon">å¡å¸ä½¿ç¨ç»ææ¶é´</span>
					         					<input type="text" class="form-control formTime" id="endTimeY" placeholder="number">
					         					<span class="input-group-addon">å¹´</span>
					         					<input type="text" class="form-control formTime" id="endTimeM" placeholder="number">
					         					<span class="input-group-addon">æ</span>
					         					<input type="text" class="form-control formTime" id="endTimeD" placeholder="number">
					         					<span class="input-group-addon">æ¥</span>
					     					 </div>
				     					 </div>
				     				</li>
				     				<li class="typeTwo" å¡å¸æææä¸ºæç»­æ¶é¿>
				     					 <div class="inputviews">
					     					 <div class="input-group" id="rangeTime">
					        					<span class="input-group-addon">å¡å¸ä½¿ç¨æç»­æ¶é¿</span>
					         					<input type="text" class="form-control" id="timeRange" placeholder="number">
					         					<span class="input-group-addon">å¤©</span>
					     					 </div>
				     					 </div>
				     				</li>
				     			</ul>
				     		</div>
	     					 <div class="stag">
								<span class="upView">ä¸ä¼ åå®¶å¾æ </span>
								<div class="addimg">
									<i class="icon-plus-sign"></i>
								</div>
								<i class="icon"></i>
								<input type="file" name="" id="upimg"/>
							</div>	





							<div class="remind">
								<span class="upView">éæ©å¡çèæ¯é¢è²</span>
								<div id="chooseColor"></div>
								<table class="colorTable">
									<tr>
										<td class="colorView Color010" title="#63b359"></td>
										<td class="colorView Color020" title="#2c9f67"></td>
										<td class="colorView Color030" title="#509fc9"></td>
										<td class="colorView Color040" title="#5885cf"></td>
										<td class="colorView Color050" title="#9062c0"></td>
										<td class="colorView Color060" title="#d09a45"></td>
										<td class="colorView Color070" title="#e4b138"></td>
									</tr>
									<tr>
										<td class="colorView Color080" title="#ee903c"></td>
										<td class="colorView Color081" title="#f08500"></td>
										<td class="colorView Color082" title="#a9d92d"></td>
										<td class="colorView Color090" title="#dd6549"></td>
										<td class="colorView Color100" title="#cc463d"></td>
										<td class="colorView Color101" title="#cf3e36"></td>
										<td class="colorView Color102" title="#5E6671s"></td>
									</tr>
								</table>
							</div>

							<div class="form-group">
    							<label for="name">å¡å¸ä½¿ç¨è¯´æ</label>
    							<p>
    								<textarea class="form-control" rows="3" id="groupDescription"></textarea>
    								<i class="icon"></i>
    							</p>
  							</div>
							<div class="form-group">
    							<label for="name">å¢è´­è¯¦æ</label>
    							<p>
    								<textarea class="form-control" rows="3" id="groupDetail"></textarea>
    								<i class="icon"></i>
    							</p>
  							</div>
	     					 <button class="btn btn-primary col-md-1 col-md-offset-7" id="upCard">åå»ºå¡å¸</button>
					 </form>
				 </div>
			</div>
		</div>
		<footer></footer>
	</div>
	<script type="text/javascript" src="<%@ StaticConstant.jsFilePath %>main.js"></script>
</body>
</html>