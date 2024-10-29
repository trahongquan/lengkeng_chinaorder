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

    if($('#image_variant')) {
        // Hàm tạo các thẻ hình ảnh và thêm vào #card-body
        function createImages(images, variant_id) {
            var cardBody = $("#card-body");
            console.log(images)
            console.log(variant_id)
            images.forEach(image => {
                if (image.variant.id == variant_id) { // Ensure variant exists and IDs match
                var img = $('<img>')
                        .attr('src', '/' + image.imgurl)
                        .addClass('product-image')
                        .css({
                            maxWidth: '20%' // Giới hạn chiều rộng tối đa của ảnh là 20% chiều rộng của phần tử cha
                        }); // Use 'url' property for clarity
                    cardBody.append(img);
                } else {
                    var p = $('<p>').text('Biến thể chưa có hình ảnh')
                    cardBody.append(p);
                    console.warn(`Image skipped: variant ID ${variant_id} not found in image object.`);
                }
        });
        }
        // // Sự kiện click vào nút "Xem ảnh"
        // $(".image_variant").click(function() {
        //     $(".popup-image").slideDown(300);
        // });

        // Sự kiện click vào hình ảnh
        $(document).on('click', '.product-image', function() {
            var clickedImage = $(this);
            var imgSrc = clickedImage.attr('src');

            // Tạo một lớp phủ (overlay) để chứa ảnh phóng to
            var overlay = $('<div>').addClass('image-overlay').appendTo('body');
            var imgOverlay = $('<img>').attr('src', imgSrc).addClass('enlarged-image').appendTo(overlay);

            // Hiển thị overlay và ảnh phóng to
            overlay.fadeIn();
            imgOverlay.css({
                position: 'fixed',
                top: 0,
                left: 0,
                width: '100%',
                height: '100%',
                objectFit: 'contain' // Giữ tỷ lệ ảnh và căn giữa
            });

            // Tạo nút đóng
            var closeBtn = $('<button>').addClass('close-btn').text('X').appendTo(overlay);
            closeBtn.click(function() {
                overlay.fadeOut();
            });
            console.log('clicked')
        });


        $(".image_variant").click(function() {
            var variant_id = $(this).attr('data-id');
            $("#card-body").empty();
            createImages(images, variant_id);
            $(".popup-image").slideDown(300);
        });

        $(window).click(function(event) {
            if ($(event.target).hasClass("popup-image")) {
                $(".popup-image").slideUp(300);
            }
        });
        $(".close-btn").click(function() {
            $(".popup-image").slideUp(300);
        });
    }
});