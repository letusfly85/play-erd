/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    var $btn = $('#toPageTop a');
    var isHidden = true;

    $btn.hide();
    
    $(window).scroll(function () {
        if($(this).scrollTop() > 300){
            if(isHidden) {
                $btn.stop(true,true).fadeIn();
                isHidden = false;
            }
        } else {
            if(!isHidden) {
                $btn.stop(true,true).fadeOut();
                isHidden = true;
            }
        }
    })
})