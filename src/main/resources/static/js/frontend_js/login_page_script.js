
$('[data-toggle="tooltip"]').tooltip();
$(".preloader").fadeOut();

$('#to-recover').on("click", function() {
    $("#loginform").slideUp();
    $("#recoverform").fadeIn();
});
$('#to-login').click(function(){
    $("#recoverform").hide();
    $("#loginform").fadeIn();
});