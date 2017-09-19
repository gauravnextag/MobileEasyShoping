
function uploadFile() {
    //alert("abc");
    $("#uploadcntrl").slideDown("slow");
    $('.alert').show();
    setTimeout(function () {
        $('.alert').slideUp('slow');
    }, 3000
    );

}

function showArchives() {
    $(".archivetable").slideDown("slow");
}

$('button[data-loading-text]')
    .on('click', function (e) {
        e.preventDefault();
        var btn = $(this)
        btn.button('loading')
        setTimeout(function () {
            btn.button('reset')
        }, 3000)
    });


$(function () {

    $("#dateto").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd/M/yy"
    });

    $("#datefrom").datepicker({
        changeMonth: true,
        changeYear: true,
        dateFormat: "dd/M/yy"
    });

});