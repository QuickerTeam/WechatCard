$().ready(function(){
	var ismoreCard = false;
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






$(".card").on('click', function(event) {
  event.preventDefault();
  cardType = $(this).attr("id");
  $(".card").removeClass('thisCard');

  switch(cardType){
    case "groupBuying":
      $(".sbody").load("/WechatCard/src/main/webapp/WEB-INF/jsp/groupBuying.html");
      $(this).addClass('thisCard');

      break;

    case "voucher":
      $(".sbody").load("/WechatCard/src/main/webapp/WEB-INF/jsp/voucher.html");
      $(this).addClass('thisCard');
      break;
    case "countCard":
      $(".sbody").load("/WechatCard/src/main/webapp/WEB-INF/jsp/discount.html");
      $(this).addClass('thisCard');
      break;
    case "giftCard":
      $(".sbody").load("/WechatCard/src/main/webapp/WEB-INF/jsp/giftCard.html");
      $(this).addClass('thisCard');
      break;
    case "coupon":
      $(".sbody").load("/WechatCard/src/main/webapp/WEB-INF/jsp/coupon.html");
      $(this).addClass('thisCard');
      break;
    default:break;
  };
});

  $("#moreCard").trigger('click');
  $("#groupBuying").trigger('click');
});