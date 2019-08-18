(function() {
    
	main = function() {
    	self = this;
    	var timeId;
        this.form = document.forms.form;
        this.init();
    };
    /**
     * (공통)초기화
     */
    main.prototype.init = function() {
      this.bindBtn();
    };
    
    main.prototype.bindBtn = function() {
    	$('.menu').on('click',function(e){
    		e.preventDefault();
    		$('.menu').removeClass('active');
    		$(this).toggleClass( 'active' );
    		$('.convertTitle').html($(this).find('a').text());
    		
    		$("#url").val("");
    		$("#resultUrl").html("");
    	});
    	
    	$('#alertClose').on('click',function(e){
    		e.preventDefault();
    		$('#megAlert').hide();
    	});
    	
    	
    	$('#chgShotUrl').on('click',function(e){
    		event.preventDefault();
    		var urlType = $('.menu.active').find('a').hasClass('url');
    		var urlParm;
    		if(!urlType){
    			$('#url').attr('name','srtUrl');
    			urlParm = "/shorteningUrlConvert";
    		}else{
    			$('#url').attr('name','orgUrl');
    			urlParm = "/urlConvert";
    		}
    		
    		var url = $('#url').val();
    		if(url==""){
    			self.alert("error","Falied! URL을 입력해 주세요.");
    			return;
    		}
    		
    		$.ajax({
		    		url: urlParm,
		    		type: !urlType ? "GET" : "POST",
		    		dataType: "json",
		    		data: $('#form').serialize(),
		    		success: function(result){
		    			// ajax 통신 성공 시 로직 수행
		    			var resultUrl = !urlType ? result.resultUrl : result.resultCd=="error" ? "" : window.location.origin+"/"+result.resultUrl;
		    			var appendEl = "<a href='"+resultUrl+"' target='_blank'>"+resultUrl+"</a>";
		    			$('#resultUrl').html(appendEl);
		    			self.alert(result.resultCd,result.msg);
		    		},
	    			error : function(request, status, error ) {   // 오류가 발생했을 때 호출된다. 
	    				self.alert("error","오류가 발생해 URL변환에 실패했습니다.");
	    			}
    			});
    	});
    }
    
    main.prototype.alert = function(cd , msg) {
    	if(cd=="error"){
    		$('#megAlert').attr("class","alert alert-danger");
    	}else{
    		$('#megAlert').attr("class","alert alert-success");
    	}
    	$('#megAlert').html(msg);
		$('#megAlert').show();
		clearTimeout(self.timeId);
		self.timeId = setTimeout(function() {
	    	$('#megAlert').hide();
	    }, 3000);
    }
    
    $(function() {
        main = new main();
    });
    
})();