$(function(){
	$.mCustomScrollbar.defaults.scrollButtons.enable=true; 
			$.mCustomScrollbar.defaults.axis="yx"; 
			$("#content-dtk").mCustomScrollbar({theme:"dark-thick"});
			$(".all-themes-switch a").click(function(e){
			e.preventDefault();
			var $this=$(this),
			rel=$this.attr("rel"),
			el=$(".content");
		switch(rel){
			case "toggle-content":
			el.toggleClass("expanded-content");
			break;
		}
	});
	
})