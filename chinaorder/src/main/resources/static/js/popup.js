/** ************* Popup **************/
$(document).ready(function() {
    $(".btn-edit").click(function() {
        var name = $(this).attr('data-name');
        var id = $(this).attr('data-id');
        console.log(name)
        console.log(id)
        $("#name").val(name);
        $("#id").val(id);
        $(".popup").slideDown(300);
    });

    $(".close-btn").click(function() {
        $(".popup").slideUp(300);
    });

    // Optional: Close the popup when clicking outside the content
    $(window).click(function(event) {
        if ($(event.target).hasClass("popup")) {
            $(".popup").slideUp(300);
        }
    });
});


/** ************* Popup **************/
$(document).ready(function() {
    if($("#color_eidt")){
        $(".btn-edit").click(function() {
            var color_eidt = $(this).attr('data-color');
            var abbreviations_eidt = $(this).attr('data-abbreviations');
            var hexCode_eidt = $(this).attr('data-hexCode');
            var id = $(this).attr('data-id');
            $("#id").val(id);
            $("#color_eidt").val(color_eidt);
            $("#abbreviations_eidt").val(abbreviations_eidt);
            $("#hexCode_eidt").val(hexCode_eidt);
            $(".popup").slideDown(300);
        });
    }
});