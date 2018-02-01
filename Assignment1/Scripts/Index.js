var educationType = "";
var semester = "";
var option = "";
$(function () {
    $("#education li a").click(function () {
        var edu = $(this).text();
        $(this).parents('.btn-group').find('.dropdown-toggle').html(edu + ' <span class="caret"></span>');
        educationType = $(this).text();
    });

    $("#sem li a").click(function () {
        var sem = $(this).text();
        $(this).parents('.btn-group').find('.dropdown-toggle').html(sem + ' <span class="caret"></span>');
        semester = $(this).text();
    });

    $("#career_option li a").click(function () {
        var op = $(this).text();
        $(this).parents('.btn-group').find('.dropdown-toggle').html(op + ' <span class="caret"></span>');
        option = $(this).text();
    });
    $("#courses").hide();


});

function courseAvailable() {
    $("#Submit").click(function () {
        $("#error").text("");
        if (educationType == "Masters" && semester == "Spring" && option == "Computer Science") {
            $("#availableClasses").show();
            $('#availableClasses').parents('div.dataTables_wrapper').first().show();
            $("#lee").show();
            $("#choi").hide();
            $("#zheng").hide();
        } else if (educationType == "" || semester == "" || option == "") {
            $("#error").text("Please select all options");
        } else {
            $("#availableClasses").show();
            $('#availableClasses').parents('div.dataTables_wrapper').first().show();
            $("#lee").hide();
            $("#choi").show();
            $("#zheng").show();
        }


    });


}
