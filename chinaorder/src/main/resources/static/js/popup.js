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
        function createImages(images, product_id, variant_id) {
            var hasImage = false;
            var cardBody = $("#card-body");
            var image_for_variant = [];
            images.forEach(image => {
                if (image.variant.id == variant_id) image_for_variant.push(image);
            });
            if(image_for_variant.length == 0) {
                var p = $('<p>').text('Biến thể chưa có hình ảnh').addClass('font-weight-bold text-warning')
                cardBody.append(p);
                console.warn(`Image skipped: variant ID ${variant_id} not found in image object.`);
            } else {
                image_for_variant.forEach(image => {
                    var img = $('<img>')
                        .attr('src', '/' + image.imgurl)
                        .attr('data-product-id', product_id)
                        .addClass('product-image')
                        .css({
                            maxWidth: '20%' // Giới hạn chiều rộng tối đa của ảnh là 20% chiều rộng của phần tử cha
                        }); // Use 'url' property for clarity
                    cardBody.append(img);
                })

            }
        }

        // Sự kiện click vào hình ảnh
        $(document).on('click', '.product-image', function() {
            var clickedImage = $(this);
            var imgSrc = clickedImage.attr('src');
            var product_id = clickedImage.attr('data-product-id');
            // Tạo một lớp phủ (overlay) để chứa ảnh phóng to
            var overlay = $('<div>').addClass('image-overlay').css({}).appendTo('body');
            var imgOverlay = $('<img>').attr('src', imgSrc).addClass('enlarged-image bg-dark-hover').appendTo(overlay);

            // Hiển thị overlay và ảnh phóng to
            overlay.fadeIn();
            imgOverlay.css({
                zIndex: 9999,
                position: 'fixed',
                top: '50%',
                left: '60%',
                width: '70%',
                height: '70%',
                transform: 'translate(-50%, -50%)',
                objectFit: 'contain' // Giữ tỷ lệ ảnh và căn giữa
            });

            // Tạo nút đóng
            var closeBtn = $('<button>').addClass('close-btn font-weight-bold font-size-xlg text-danger p-4').text('X')
                .css({
                    zIndex: 9999,
                    position: 'fixed',
                    top: '90%',
                    left: '55%',
                }).appendTo(overlay);

            var delImageBtn = $('<a>')
                .addClass('btn btn-danger font-weight-bold font-size-xlg p-4')
                .text('Xóa')
                .css({
                    zIndex: 9999,
                    position: 'fixed',
                    top: '90%',
                    left: '65%',
                })
                .attr('href', '/Lengkeng/admin/products/detail/delImage?url=' + encodePath(imgSrc.substring(1))+'&product_id='+product_id) // Thêm thuộc tính href
                .appendTo(overlay);

            delImageBtn.on('click', function(event) {
                // Ngăn chặn hành vi mặc định của link (nếu cần)
                event.preventDefault();

                // Thực hiện các hành động khác khi nút được click (ví dụ: hiển thị confirm box)
                if (confirm('Bạn có chắc chắn muốn xóa ảnh này không?')) {
                    // Chuyển hướng đến link xóa ảnh
                    window.location.href = $(this).attr('href');
                }
            });

            closeBtn.click(function() {
                overlay.slideUp(300);
            });
            console.log('clicked')
        });


        $(".image_variant").click(function() {
            var variant_id = $(this).attr('data-id');
            var product_id = $(this).attr('data-product-id');
            $("#card-body").empty();
            createImages(images,product_id, variant_id);
            $(".popup-image").slideDown(300);
        });
        $(window).click(function(event) {
            if ($(event.target).hasClass("popup-image")) {
                $(".popup-image").slideUp(300);
                $(".image-overlay").slideUp(300);
            }
        });
        $(".close-btn").click(function() {
            $(".popup-image").slideUp(300);
            $(".image-overlay").slideUp(300);
        });
    }

    /** Mã hóa đường dẫn ảnh */

    function encodePath(path) {
        var base64String = btoa(path);
        return base64String;
    }

    function decodePath(base64String) {
        // Giải mã chuỗi Base64 thành mảng byte
        var byteArray = atob(base64String);
        // Chuyển đổi mảng byte thành chuỗi
        var path = new TextDecoder().decode(byteArray);
        return path;
    }
});