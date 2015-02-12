var DOM = YAHOO.util.Dom;
var EVENT = YAHOO.util.Event;
var autosaving = false;

String.prototype.trim = function() {
	a = this.replace(/^\s+/, '');
	return a.replace(/\s+$/, '');
};

var clearText = false;
var clearText2 = false;
var clearText3 = false;
var clearText4 = false;

function clearDefaultText(tb) {
	if(!clearText) {
		tb.value = "";
		clearText = true;
	} 
}
function clearDefaultText2(tb) {
	if(!clearText2) {
		tb.value = "";
		clearText2 = true;
	}
}
function clearDefaultText3(tb) {
	if(!clearText3) {
		tb.value = "";
		clearText3 = true;
	}
}
function clearDefaultText4(tb) {
	if(!clearText4) {
		tb.value = "";
		clearText4 = true;
	}
}

function showLoadingImage() {
	var img = DOM.get('loading-image');
	DOM.removeClass(img,'hide');
}

function hideLoadingImage() {
	var img = DOM.get('loading-image');
	DOM.addClass(img,'hide');
}

function goToPage() {
	showLoadingImage();
	var page = DOM.get('page').value;
	var img = DOM.get('loading-image');
	location.href = page;
	img.src = img.src;
}

function validKeyCode(keyCode) {
	if((keyCode >= 48 && keyCode <= 90) || 
	   (keyCode >= 96 && keyCode<= 105) ||
	    keyCode == 8 ||
	    keyCode == 46) {
		return true;
	}
	return false;
}

/**
	This Class extends the MooTools accordion class to allow for multiple toggles to 
	be open at once.
*/
var multipleOpenAccordion = new Class({
	Extends: Accordion,
	options: {
		allowMultipleOpen: true
	},
	initialize: function(togglers,togglees,elements,options){
		this.parent(togglers,togglees,elements,options);
	},
	display: function(index){
		this.index = index;
		index = ($type(index) == 'element') ? this.elements.indexOf(index) : index;
		if ((this.timer && this.options.wait) || (index === this.previous && !this.options.alwaysHide)) return this;
		
		var obj = {};
		if(this.options.allowMultipleOpen){
			if(index > -1) {
				var el = this.elements[index];
				obj[index] = {};
				var hide = (el.offsetHeight > 0);
				this.fireEvent(hide ? 'onBackground' : 'onActive', [this.togglers[index], el]);
				for (var fx in this.effects) obj[index][fx] = hide ? 0 : el[this.effects[fx]];
			}
		}else{
			this.previous = index;
			this.elements.each(function(el, i){
				obj[i] = {};
				var hide = (i != index) || (this.options.alwaysHide && (el.offsetHeight > 0));
				this.fireEvent(hide ? 'onBackground' : 'onActive', [this.togglers[i], el]);
				for (var fx in this.effects) obj[i][fx] = hide ? 0 : el[this.effects[fx]];
			}, this);
			
		}
		return this.start(obj);
	}
});


function handleError(o) {
	alert('AJAX ERROR');
}

function highlightEl(el) {
	DOM.addClass(el,'highlight');
}

function unhighlightEl(el) {
	DOM.removeClass(el,'highlight');
}