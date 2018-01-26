$(document).ready(function(){
    var touch = $('#touch-navigation');
    var menu = $('.menu');

    $(touch).on('click', function(e) {
        e.preventDefault();
        menu.slideToggle();
    });
    $(window).resize(function() {
        var wid = $(window).width();
        if (wid > 760 && menu.is(':hidden')) {
            menu.removeAttr('style');
        }
    });
});