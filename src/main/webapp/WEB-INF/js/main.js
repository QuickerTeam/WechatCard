$().ready(function(){
	var ismoreCard = false;
	var bodyH;
	var bodyW;
	bodyH = $(window).height();
	bodyW = $(window).width();
	$(".waitDiv").css({
		"width": bodyW,
		"height": bodyH
	});
	
	function getDate(strDate) {        //将字符串转化为时间类型 
        var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,    
         function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');    
        return date;    
    } 

(function($) {
$.extend({ 
  myTime: {             
    /**
     * 当前时间戳
     * @return <int>    unix时间戳(秒) 
     */
    CurTime: function(){
      return Date.parse(new Date())/1000;
    },
    /**       
     * 日期 转换为 Unix时间戳
     * @param <string> 2014-01-01 20:20:20 日期格式       
     * @return <int>    unix时间戳(秒)       
     */
    DateToUnix: function(string) {   //  将时间类型转化为时间戳
      var f = string.split(' ', 2);
      var d = (f[0] ? f[0] : '').split('-', 3);
      var t = (f[1] ? f[1] : '').split(':', 3);
      return (new Date(
          parseInt(d[0], 10) || null,
          (parseInt(d[1], 10) || 1) - 1,
          parseInt(d[2], 10) || null,
          parseInt(t[0], 10) || null,
          parseInt(t[1], 10) || null,
          parseInt(t[2], 10) || null
          )).getTime() / 1000;
    },
    /**       
     * 时间戳转换日期       
     * @param <int> unixTime  待时间戳(秒)       
     * @param <bool> isFull  返回完整时间(Y-m-d 或者 Y-m-d H:i:s)       
     * @param <int> timeZone  时区       
     */
    UnixToDate: function(unixTime, isFull, timeZone) {          //将时间戳转化为时间类型
      if (typeof (timeZone) == 'number')
      {
        unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
      }
      var time = new Date(unixTime * 1000);
      var ymdhis = "";
      ymdhis += time.getUTCFullYear() + "-";
      ymdhis += (time.getUTCMonth()+1) + "-";
      ymdhis += time.getUTCDate();
      if (isFull === true)
      {
        ymdhis += " " + time.getUTCHours() + ":";
        ymdhis += time.getUTCMinutes() + ":";
        ymdhis += time.getUTCSeconds();
      }
      return ymdhis;
    }
  }
});
})(jQuery);
	
	$("#moreCard").click(function(event) {
		if(ismoreCard == false){
			$(".cardView").slideDown('400');
			$(".fontSign").removeClass('icon-double-angle-left').addClass('icon-double-angle-down');
			ismoreCard = true;
		}else{
			$(".cardView").slideUp('400');
			$(".fontSign").removeClass('icon-double-angle-down').addClass('icon-double-angle-left');
			ismoreCard = false;
		}
	});

	function setBodyH(){
		  var viewportH = $(window).height();
		  if($(".sbody").height() < viewportH-60){
		    $(".sbody").css('minHeight', viewportH-60);
		    $(".leftMenu").css('minHeight', viewportH-60);
		  }else{
		    $(".leftMenu").css('minHeight', $(".sbody").height());
		  }
		  $(".waitDiv").css("display","none");
		}

	function setsBodyH(){
		  var viewportH = $(window).height();
		  if($(".sbody").height() < viewportH-60){
		    $(".sbody").css('minHeight', viewportH-60);
		    $(".leftMenu").css('minHeight', viewportH-60);
		  }else{
		    $(".leftMenu").css('minHeight', $(".sbody").height());
		  }
		}

$(".card").on('click', function(event) {
	$(".waitDiv").css("display","block");
  event.preventDefault();
  cardType = $(this).attr("id");
  $(".card").removeClass('thisCard');

  switch(cardType){
    case "groupBuying":

      

      $(".sbody").load("/WechatCard/jsp/groupBuying.html",setBodyH);

      $(this).addClass('thisCard');

      break;

    case "voucher":

      $(".sbody").load("/WechatCard/jsp/voucher.html",setBodyH);
      $(this).addClass('thisCard');
      break;
   
   
   
    case "countCard":
      $(".sbody").load("/WechatCard/jsp/discount.html",setBodyH);
      $(this).addClass('thisCard');
      break;
      
    case "giftCard":
      $(".sbody").load("/WechatCard/jsp/giftCard.html",setBodyH);
      $(this).addClass('thisCard');
      break;
      
    case "coupon":
      $(".sbody").load("/WechatCard/jsp/coupon.html",setBodyH);

      $(this).addClass('thisCard');
      break;
    default:break;
  };
});

$(".leftbtn").on('click', function(event) {
	  event.preventDefault();

	  sbodyView = $(this).attr("id");
	  $(".thisCard").removeClass('thisCard');
	  switch(sbodyView){
	    case "sendCardM":
	        $(".sbody").load("/WechatCard/jsp/sendCard.html",setsBodyH);
	        $(this).addClass('thisCard');
	      break;
	    case "cancelCardM":
	        $(".sbody").load("/WechatCard/jsp/cancelCard.html",setsBodyH);
	        $(this).addClass('thisCard');
	      break;
	    case "manageCardM":
	        $(".sbody").load("/WechatCard/jsp/manageCard.html",setsBodyH);
	        $(this).addClass('thisCard');
	    break;
	    default:
	      break;
	  }
	});
$(".sbody").load("/WechatCard/jsp/manageCard.html",setsBodyH);
$(this).addClass('thisCard');
  
});