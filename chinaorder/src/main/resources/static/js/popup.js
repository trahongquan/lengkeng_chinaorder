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