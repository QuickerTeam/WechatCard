$().ready(function(){
  var cardType = "groupBuying";
	var ismoreCard = false;
  var checkOk = true;  //涓�treu浠ｈ〃琛ㄥ��妫�楠����杩�
	$("#upimg").val(null);     // ���濮����涓�浼���剧�����value
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


$("#upimg").on("change", function(){                  //涓�浼���剧�������堕��瑙�涓�浼������剧��
    // Get a reference to the fileList
    var files = !!this.files ? this.files : [];
    var nextElement = $(".addimg").nextAll("i");
 
    // If no files were selected, or no FileReader support, return
    if (!files.length || !window.FileReader) return;
 
    // Only proceed if the selected file is an image
    if (/^image/.test( files[0].type)){                     //楠�璇���剧��������������
 
        // Create a new instance of the FileReader
        var reader = new FileReader();
 
        // Read the local file as  DataURL
        reader.readAsDataURL(files[0]);
 
        // When loaded, set image data as background of div
        checkOk = true;
        
  		  reader.onloadend = function(){
  		$(".addimg").empty();
		$("<img src='"+this.result+"' class = 'imgview'>").appendTo($(".addimg"));  
        };
        if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
            };
        if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
            };
        nextElement.addClass('icon-ok').attr({title: ""});
    }else{
    	  if (nextElement.hasClass('icon-ok')) {
              nextElement.removeClass('icon-ok');
          };
        if (nextElement.hasClass('icon-remove')) {
              nextElement.removeClass('icon-remove');
          };
          nextElement.addClass('icon-remove').attr({title: "璇烽����╁�剧��涓�浼�"});
          checkOk = true;
    }
 
});

  $(".addimg").click(function(event) {               
  	$("#upimg").trigger("click");
  });

  $("#chooseColor").on('click',  function(event) {        //��剧ず�����╅����茬����茬��
  	event.preventDefault();
  	$(".colorTable").css({display:"inline-block"});
  });

  $(".colorView").on('click', function(event) {             //��������叉��
  	event.preventDefault();
  	var color = $(this).attr("title");
  	$("#chooseColor").css({
  		backgroundColor: color
  	});
  	$(".colorTable").hide("400");
  });


  $(".form-control").blur(function(event) {    //琛ㄥ��楠�璇�
  	var idname = $(this).attr("id");  
  	var nextElement = $(this).nextAll("i");
  	switch(idname){
  		case "cardTitle":                          //楠�璇���″�告�����������
  					if ($(this).val().length>9) {
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
  						nextElement.addClass('icon-remove').attr({title: "瀛���颁����借��杩�9"});
              checkOk = false;
  					}else if ($(this).val().length == 0) {
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
  						nextElement.addClass('icon-remove').attr({title: "瀛���颁����借��杩�0"});
              checkOk = false;
  					}else{
  						if (nextElement.hasClass('icon-remove')) {
  							nextElement.removeClass('icon-remove');
  						};
  						if (nextElement.hasClass('icon-ok')) {
  							nextElement.removeClass('icon-ok');
  						};
  						nextElement.addClass('icon-ok').attr({title: ""});
              checkOk = true;
  					};
  					break;
  		case "cardNumber":                          //楠�璇���″�稿��琛���伴��
  						if ($(this).val()>100000000) {
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
  						nextElement.addClass('icon-remove').attr({title: "��″�告�伴��杩�澶�"});
              checkOk = false;
  					}else if ($(this).val().length == 0) {
                if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
  						nextElement.addClass('icon-remove').attr({title: "瀛���拌����ヨ�戒负绌�"});
              checkOk = false;
  					}else if (isNaN($(this).val()) || $(this).val()<0) {
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
  						nextElement.addClass('icon-remove').attr({title: "璇疯����ユ�ｆ�存��"});
              checkOk = false;
  					}else{
  						if (nextElement.hasClass('icon-remove')) {
  							nextElement.removeClass('icon-remove');
  						};
  						if (nextElement.hasClass('icon-ok')) {
  							nextElement.removeClass('icon-ok');
  						};
  						nextElement.addClass('icon-ok').attr({title: ""});
              checkOk = true;
  					};
  					break;
 case "useView":                                  //楠�璇���″�镐娇��ㄦ�����
 	            if ($(this).val().length>16) {
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              nextElement.addClass('icon-remove').attr({title: "瀛���颁����借��杩�16"});
              checkOk = false;
            }else if ($(this).val().length == 0) {
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              nextElement.addClass('icon-remove').attr({title: "杈���ヤ����戒负绌�"});
              checkOk = false;
            }else{
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              if(nextElement.hasClass("notMust")){
                nextElement.removeClass('notMust');
              };
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              nextElement.addClass('icon-ok').attr({title: ""});
              checkOk = true;
            };
            break;  
  case "serverTel":                     //楠�璇���″�稿�㈡����佃��
             var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
             if (!isPhone.test($(this).val())) {
              nextElement.empty();
               if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              nextElement.addClass('icon-remove').attr({title: "杈���ュ�风��������"});
              checkOk = false;
            }else if ($(this).val().length == 0) {
              nextElement.empty();
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              nextElement.addClass('notMust').attr({title: ""}).text('*���濉�');
              checkOk = false;
            }else{
              nextElement.empty();
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if(nextElement.hasClass("notMust")){
                nextElement.removeClass('notMust');
              };
              nextElement.addClass('icon-ok').attr({title: ""});
              checkOk = true;
            };
            break;
    case "favourView":                      //��蜂��浼����淇℃��
             if ($(this).val().length>18) {
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              nextElement.addClass('icon-remove').attr({title: "瀛���颁����借��杩�18"});
              checkOk = false;
            }else if ($(this).val().length == 0) {
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              nextElement.addClass('icon-remove').attr({title: "杈���ヤ����戒负绌�"});
              checkOk = false;
            }else{
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              nextElement.addClass('icon-ok').attr({title: ""});
              checkOk = true;
            };
            break;
    case "numberPlace":                         //���浜哄��棰���″�告�伴��楠�璇�
             if (isNaN($(this).val())) {
              nextElement.empty();
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              nextElement.addClass('icon-remove').attr({title: "璇疯����ユ�ｆ�存��"});
              checkOk = true;
             }else{
             if ($(this).val()>50) {
              nextElement.empty();
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              nextElement.addClass('icon-remove').attr({title: "杈���ユ�伴��涓���借��杩�50"});
              checkOk = false;
            }else if ($(this).val().length == 0) {
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              nextElement.addClass('notMust').attr({title: ""}).text('*���濉�');
             checkOk = false;
            }else{
              nextElement.empty();
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if(nextElement.hasClass("notMust")){
                nextElement.removeClass('notMust');
              };
              nextElement.addClass('icon-ok').attr({title: ""});
              checkOk  =true;
            };
          };
            break;
    case "groupDescription":
    if ($(this).val()>1024) {
             if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              nextElement.addClass('icon-remove').attr({title: "杈���ュ����颁����借��杩�1024"});
              checkOk = false;
            }else if ($(this).val().length == 0) {
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              nextElement.addClass('icon-remove').attr({title: "杈���ヤ����戒负绌�"});
             checkOk = false;
            }else{
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              nextElement.addClass('icon-ok').attr({title: ""});
              checkOk = true;
            };
        
            break;
    case "groupDetail":               //��㈣喘��稿�蜂��淇℃��楠�璇�
           if ($(this).val()>1024) {
             if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              nextElement.addClass('icon-remove').attr({title: "杈���ュ����颁����借��杩�1024"});
              checkOk = false;
            }else if ($(this).val().length == 0) {
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              nextElement.addClass('icon-remove').attr({title: "杈���ヤ����戒负绌�"});
             checkOk = false;
            }else{
              if (nextElement.hasClass('icon-remove')) {
                nextElement.removeClass('icon-remove');
              };
              if (nextElement.hasClass('icon-ok')) {
                nextElement.removeClass('icon-ok');
              };
              nextElement.addClass('icon-ok').attr({title: ""});
              checkOk = true;
            };
        
            break;
    default:
            break;
  	};
  	
  });

var isChange = "0";    // 琛ㄧず�����ㄧ����堕�寸被���锛�0琛ㄧず浣跨�ㄦ�堕�村�洪�达��1琛ㄧず�����ㄦ�堕�撮�跨��
$("#changeType").on('click', function(event) {             //�����㈡�堕�寸被���
  event.preventDefault();
  if (isChange == "0") {
  $(".typeView").animate({"top":"-54px"}, "400");
  isChange = "1";
}else{
 $(".typeView").animate({"top":"0px"}, "400");
  isChange = "0";
} 
});

 $("#upCard").on('click', function(event) {      //���������寤哄�″�哥��
   event.preventDefault();
   if (isChange == 0) {                     //��规��瀹㈡�烽����╃����″�告�������������堕�寸被���杩�琛�涓�������妫�楠�
      var sY = $("#starTimeY").val();
      var sM = $("#starTimeM").val();
      var sD = $("#starTimeD").val();
      var eY = $("#endTimeY").val();
      var eM = $("#endTimeM").val();
      var eD = $("#endTimeD").val(); 
      if (sY || sM || sD || eY || eM || eD) {
       $(".errup").text("杈���ユ�堕�翠����戒负绌�");
       $(".errup").slideDown('slow', function() {
         $(this).delay(1000).slideUp('slow');
         checkOk = false;
         return;
      });
     }else if (sY > eY || ((sY <= eY)&&(sM > eM)) ||  ((sY <= eY) && (sM <= eM) && (sD > eD))) {
       $(".errup").text("寮�濮���堕�翠����芥��浜�缁������堕��");
       $(".errup").slideDown('slow', function() {
         $(this).delay(1000).slideUp('slow');
         checkOk = false;
         return;
      });
     }else if (isNaN(sY) ||isNaN(sM)||isNaN(sD)||isNaN(eY)||isNaN(eM)||isNaN(eD) || (sM>12) || (sD >31) || (eM >12) || (eD > 31)) {
        $(".errup").text("杈���ョ����堕�存�煎��涓�姝ｇ‘");
        $(".errup").slideDown('slow', function() {
         $(this).delay(1000).slideUp('slow');
         checkOk = false;
         return;
       });
   }
 }else{
      timeRange = $("#timeRange").val();
      if (isNaN(timeRange)) {
         $(".errup").text("杈���ョ����堕�翠����戒负璐�");
        $(".errup").slideDown('slow', function() {
         $(this).delay(1000).slideUp('slow');
         checkOk = false;
         return;
       });
      };
 }


   if (checkOk == false) {
       $(".errup").text(" ���寤哄�″�稿け璐ワ��璇锋����ュ～���淇℃�����������璇�");
       $(".errup").slideDown('slow', function() {
         $(this).delay(1000).slideUp('slow');
         return;
   });
   }else{
      var title = $(".cardTitle").val();
      var card_color =  $("#chooseColor").css(backgroundColor);   //��″�搁�����
      var left_card_amount = $("#cardNumber").val(); //�����峰�����搴�瀛�
      var sub_title = $("#favourView").val(); //��″�稿�蜂��浼����淇℃��
      var notice = $("#useView").val(); //��″�镐娇��ㄦ�����
      var description = $("#groupDescription").text();//��″�镐娇��ㄨ�存��
      var dataTypee = isChange;//浣跨�ㄦ�堕�寸被���
      switch(cardType){                             //��规��涓���������″�哥被���涓�浼�涓�������瀹圭��缁������″��
        case groupBuying:               //��㈣喘��� 
              var deal_detail = $("#groupDetail").text();//��㈣喘绁ㄤ�����璇�缁�淇℃��
              if (dataType == "0") {
                var beginTimeYear = $("#starTimeY").val();
                var beginTimeMonth = $("#starTimeM").val();
                var beginTimeDay = $("#starTimeD").val();
                var endTimeYear = $("#endTimeY").val();
                var endTimeMonth = $("#endTimeM").val();
                var endTimeDay = $("#endTimeD").val();
                $.ajax({
                  url: '/path/to/file',
                  type: 'post',
                  dataType: 'json',
                  data: {
                    "title": title, 
                    "card_color": card_color, 
                    "left_card_amount":left_card_amount,
                    "card_type":card_type,
                    "deal_detail":deal_detail,
                    "sub_title":sub_title,
                    "notice":notice,
                    "description":description,
                    "dataType":dataType,
                    "beginTimeYear":beginTimeYear,
                    "beginTimeMonth":beginTimeMonth,
                    "beginTimeDay":beginTimeDay,
                    "endTimeYear":endTimeYear,
                    "endTimeMonth":endTimeMonth,
                    "endTimeDay":endTimeDay},
                })
                .done(function() {
                   $(".suup").slideDown('slow', function() {
                    $(this).delay(1000).slideUp('slow');
          
                   });
      return false;
                })
                .fail(function() {
                  console.log("error");
                })
                .always(function() {
                  console.log("complete");
                });
                
              }else{
                var fixdTerm = $("#fixdTerm").val();
                $.ajax({
                  url: '/path/to/file',
                  type: 'post',
                  dataType: 'json',
                  data: {
                    "title": title, 
                    "card_color": card_color, 
                    "left_card_amount":left_card_amount,
                    "card_type":card_type,
                    "deal_detail":deal_detail,
                    "sub_title":sub_title,
                   " notice":notice,
                    "description":description,
                   " dataType":dataType,
                    "fixdTerm":fixdTerm},
                })
                .done(function() {
                   $(".suup").slideDown('slow', function() {
                   $(this).delay(1000).slideUp('slow');
          
                   });
                   return false;
                })
                .fail(function() {
                  console.log("error");
                })
                .always(function() {
                  console.log("complete");
                });
                }
              break;
      case "voucher":            //浠ｉ�����
            var least_cost = $("#leastCost").val();     //浠ｉ����镐����ㄥ�����
            var reduce_cost = $("#reduceCost").val();
            if (dataType == "0") {
                var beginTimeYear = $("#starTimeY").val();
                var beginTimeMonth = $("#starTimeM").val();
                var beginTimeDay = $("#starTimeD").val();
                var endTimeYear = $("#endTimeY").val();
                var endTimeMonth = $("#endTimeM").val();
                var endTimeDay = $("#endTimeD").val();
                $.ajax({
                  url: '/path/to/file',
                  type: 'post',
                  dataType: 'json',
                  data: {
                    "title": title, 
                    "card_color": card_color, 
                    "left_card_amount":left_card_amount,
                    "card_type":card_type,
                    "sub_title":sub_title,
                    "notice":notice,
                    "description":description,
                    "least_cost":least_cost,
                    "reduce_cost":reduce_cost,
                    "dataType":dataType,
                    "beginTimeYear":beginTimeYear,
                    "beginTimeMonth":beginTimeMonth,
                    "beginTimeDay":beginTimeDay,
                    "endTimeYear":endTimeYear,
                    "endTimeMonth":endTimeMonth,
                    "endTimeDay":endTimeDay},
                })
                .done(function() {
                   $(".suup").slideDown('slow', function() {
                    $(this).delay(1000).slideUp('slow');
          
                   });
      return false;
                })
                .fail(function() {
                  console.log("error");
                })
                .always(function() {
                  console.log("complete");
                });
                
              }else{
                var fixdTerm = $("#fixdTerm").val();
                $.ajax({
                  url: '/path/to/file',
                  type: 'post',
                  dataType: 'json',
                  data: {
                    "title": title, 
                    "card_color": card_color, 
                    "left_card_amount":left_card_amount,
                    "card_type":card_type,
                    "least_cost":least_cost,
                    "reduce_cost":reduce_cost,
                    "sub_title":sub_title,
                    " notice":notice,
                    "description":description,
                    " dataType":dataType,
                    "fixdTerm":fixdTerm},
                })
                .done(function() {
                   $(".suup").slideDown('slow', function() {
                   $(this).delay(1000).slideUp('slow');
          
                   });
                   return false;
                })
                .fail(function() {
                  console.log("error");
                })
                .always(function() {
                  console.log("complete");
                });
              }

              break;
      case "countCard":
            var discount = $("#discount").val();    //�����ｅ�镐����ㄥ��娈�
            if (dataType == "0") {
                var beginTimeYear = $("#starTimeY").val();
                var beginTimeMonth = $("#starTimeM").val();
                var beginTimeDay = $("#starTimeD").val();
                var endTimeYear = $("#endTimeY").val();
                var endTimeMonth = $("#endTimeM").val();
                var endTimeDay = $("#endTimeD").val();
                $.ajax({
                  url: '/path/to/file',
                  type: 'post',
                  dataType: 'json',
                  data: {
                    "title": title, 
                    "card_color": card_color, 
                    "left_card_amount":left_card_amount,
                    "card_type":card_type,
                    "sub_title":sub_title,
                    "notice":notice,
                    "description":description,
                    "discount":discount,
                    "dataType":dataType,
                    "beginTimeYear":beginTimeYear,
                    "beginTimeMonth":beginTimeMonth,
                    "beginTimeDay":beginTimeDay,
                    "endTimeYear":endTimeYear,
                    "endTimeMonth":endTimeMonth,
                    "endTimeDay":endTimeDay},
                })
                .done(function() {
                   $(".suup").slideDown('slow', function() {
                    $(this).delay(1000).slideUp('slow');
          
                   });
      return false;
                })
                .fail(function() {
                  console.log("error");
                })
                .always(function() {
                  console.log("complete");
                });
                
              }else{
                var fixdTerm = $("#fixdTerm").val();
                $.ajax({
                  url: '/path/to/file',
                  type: 'post',
                  dataType: 'json',
                  data: {
                    "title": title, 
                    "card_color": card_color, 
                    "left_card_amount":left_card_amount,
                    "card_type":card_type,
                    "discount":discount,
                    "sub_title":sub_title,
                    " notice":notice,
                    "description":description,
                    " dataType":dataType,
                    "fixdTerm":fixdTerm},
                })
                .done(function() {
                   $(".suup").slideDown('slow', function() {
                   $(this).delay(1000).slideUp('slow');
          
                   });
                   return false;
                })
                .fail(function() {
                  console.log("error");
                })
                .always(function() {
                  console.log("complete");
                });
              }
              break;
      case "giftCard":
            var gift = $("#gift").val();   //绀煎����镐�����瀛�娈�
            if (dataType == "0") {
                var beginTimeYear = $("#starTimeY").val();
                var beginTimeMonth = $("#starTimeM").val();
                var beginTimeDay = $("#starTimeD").val();
                var endTimeYear = $("#endTimeY").val();
                var endTimeMonth = $("#endTimeM").val();
                var endTimeDay = $("#endTimeD").val();
                $.ajax({
                  url: '/path/to/file',
                  type: 'post',
                  dataType: 'json',
                  data: {
                    "title": title, 
                    "card_color": card_color, 
                    "left_card_amount":left_card_amount,
                    "card_type":card_type,
                    "sub_title":sub_title,
                    "notice":notice,
                    "description":description,
                    "gift":gift,
                    "dataType":dataType,
                    "beginTimeYear":beginTimeYear,
                    "beginTimeMonth":beginTimeMonth,
                    "beginTimeDay":beginTimeDay,
                    "endTimeYear":endTimeYear,
                    "endTimeMonth":endTimeMonth,
                    "endTimeDay":endTimeDay},
                })
                .done(function() {
                   $(".suup").slideDown('slow', function() {
                    $(this).delay(1000).slideUp('slow');
          
                   });
      return false;
                })
                .fail(function() {
                  console.log("error");
                })
                .always(function() {
                  console.log("complete");
                });
                
              }else{
                var fixdTerm = $("#fixdTerm").val();
                $.ajax({
                  url: '/path/to/file',
                  type: 'post',
                  dataType: 'json',
                  data: {
                    "title": title, 
                    "card_color": card_color, 
                    "left_card_amount":left_card_amount,
                    "card_type":card_type,
                    "gift":gift,
                    "sub_title":sub_title,
                    " notice":notice,
                    "description":description,
                    " dataType":dataType,
                    "fixdTerm":fixdTerm},
                })
                .done(function() {
                   $(".suup").slideDown('slow', function() {
                   $(this).delay(1000).slideUp('slow');
          
                   });
                   return false;
                })
                .fail(function() {
                  console.log("error");
                })
                .always(function() {
                  console.log("complete");
                });
              }

              break;
      case "coupon":
             var default_detail = $("#defaultDetail").text();   //绀煎����镐�����瀛�娈�
            if (dataType == "0") {
                var beginTimeYear = $("#starTimeY").val();
                var beginTimeMonth = $("#starTimeM").val();
                var beginTimeDay = $("#starTimeD").val();
                var endTimeYear = $("#endTimeY").val();
                var endTimeMonth = $("#endTimeM").val();
                var endTimeDay = $("#endTimeD").val();
                $.ajax({
                  url: '/path/to/file',
                  type: 'post',
                  dataType: 'json',
                  data: {
                    "title": title, 
                    "card_color": card_color, 
                    "left_card_amount":left_card_amount,
                    "card_type":card_type,
                    "sub_title":sub_title,
                    "notice":notice,
                    "description":description,
                    "default_detail":default_detail,
                    "dataType":dataType,
                    "beginTimeYear":beginTimeYear,
                    "beginTimeMonth":beginTimeMonth,
                    "beginTimeDay":beginTimeDay,
                    "endTimeYear":endTimeYear,
                    "endTimeMonth":endTimeMonth,
                    "endTimeDay":endTimeDay},
                })
                .done(function() {
                   $(".suup").slideDown('slow', function() {
                    $(this).delay(1000).slideUp('slow');
          
                   });
      return false;
                })
                .fail(function() {
                  console.log("error");
                })
                .always(function() {
                  console.log("complete");
                });
                
              }else{
                var fixdTerm = $("#fixdTerm").val();
                $.ajax({
                  url: '/path/to/file',
                  type: 'post',
                  dataType: 'json',
                  data: {
                    "title": title, 
                    "card_color": card_color, 
                    "left_card_amount":left_card_amount,
                    "card_type":card_type,
                    "default_detail":default_detail,
                    "sub_title":sub_title,
                    " notice":notice,
                    "description":description,
                    " dataType":dataType,
                    "fixdTerm":fixdTerm},
                })
                .done(function() {
                   $(".suup").slideDown('slow', function() {
                   $(this).delay(1000).slideUp('slow');
          
                   });
                   return false;
                })
                .fail(function() {
                  console.log("error");
                })
                .always(function() {
                  console.log("complete");
                });
              }


              break;
      default:
              break;
    }
   }
 
 });

$(".card").on('click', function(event) {
  event.preventDefault();
  cardType = $(this).attr("id");
  $(".card").removeClass('thisCard');

  switch(cardType){
    case "groupBuying":
      $(".sbody").load("/WechatCard Maven Webapp/src/main/webapp/WEB-INF/jsp/groupBuying.html");
      $(this).addClass('thisCard');
      break;

    case "voucher":
      $(".sbody").load("/WechatCard Maven Webapp/src/main/webapp/WEB-INF/jsp/voucher.html");
      $(this).addClass('thisCard');
      break;
    case "countCard":
      $(".sbody").load("/WechatCard Maven Webapp/src/main/webapp/WEB-INF/jsp/discount.html");
      $(this).addClass('thisCard');
      break;
    case "giftCard":
      $(".sbody").load("/WechatCard Maven Webapp/src/main/webapp/WEB-INF/jsp/giftCard.html");
      $(this).addClass('thisCard');
      break;
    case "coupon":
      $(".sbody").load("/WechatCard Maven Webapp/src/main/webapp/WEB-INF/jsp/coupon.html");
      $(this).addClass('thisCard');
      break;
    default:break;
  };
});
});