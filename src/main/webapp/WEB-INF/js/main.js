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
      $(".sbody").load("./groupBuying.html");
      $(this).addClass('thisCard');

      break;

    case "voucher":
      $(".sbody").load("./voucher.html");
      $(this).addClass('thisCard');
      break;
    case "countCard":
      $(".sbody").load("./discount.html");
      $(this).addClass('thisCard');
      break;
    case "giftCard":
      $(".sbody").load("./giftCard.html");
      $(this).addClass('thisCard');
      break;
    case "coupon":
      $(".sbody").load("./coupon.html");
      $(this).addClass('thisCard');
      break;
    default:break;
  };
});

  $("#moreCard").trigger('click');
  $("#groupBuying").trigger('click');
});