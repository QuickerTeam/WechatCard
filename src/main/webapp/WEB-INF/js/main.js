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
	    case "cancleCardM":
	        $(".sbody").load("/WechatCard/jsp/cancelCard.html",setsBodyH);
	        $(this).addClass('thisCard');
	      break;
	    case "manageCardM":
	        $(".sbody").load("/WechatCard/jsp/manageCaed.html",setsBodyH);
	        $(this).addClass('thisCard');
	    break;
	    default:
	      break;
	  }
	});
  
});