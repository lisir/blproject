/**
 * MaskLayer - jQuery EasyUI Plugin
 * 
 * Author: austin 
 * 
 * Dependencies:
 * 
 */
(function($){
	function createMaskLayer(target){
		var opts = $.data(target, 'MaskLayer').options;
		var wrapper=$(target), msg=opts.msg;
		$('<div class="masklayer"></div>').css({
			display:'block', zIndex:opts.dept,
			width: wrapper.width(),
			height: wrapper.height()
		}).appendTo(wrapper);
		$('<div class="masklayer-msg"></div>').html(msg).appendTo(wrapper).css({
			display:'block', zIndex:opts.dept,
			left:(wrapper.width()-$('.masklayer-msg',wrapper).outerWidth())/2,
			top:(wrapper.height()-$('.masklayer-msg',wrapper).outerHeight())/2
		}).css('display',opts.showmsg?'block':'none');
	}	
	function destroyMaskLayer(target){
		var wrapper=$(target);
		$('.masklayer', wrapper).remove();
		$('.masklayer-msg', wrapper).remove();
	}
	$.fn.MaskLayer = function(options){
		if (typeof options == 'string'){
			switch(options){
			case 'show':
				return this.each(function(){
					createMaskLayer(this);
				});
			case 'hide':
				return this.each(function(){
					destroyMaskLayer(this);
				});
			}
		}
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'MaskLayer');
			if (state){
				$.extend(state.options, options);
			} else {
				state = $.data(this, 'MaskLayer', {
					options: $.extend({}, $.fn.MaskLayer.defaults, {}, options)
				});
			}
		});
	};
	$.fn.MaskLayer.defaults={
		showmsg: true,
		msg: 'Loading...',
		dept: 1000
	};
})(jQuery);