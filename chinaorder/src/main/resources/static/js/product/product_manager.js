$(document).ready(function () {
    var inputs = $('.product');
    $('#warning').hide();
    $('.btn-edit').click(function() {
        var product_id = $(this).attr('data-id');
        var product = $.grep(products, function(product1) {
            return product1.id == product_id;
        });
        console.log(product[0]);


        if($('#id').val()==product_id){
            $('#id').val(0);
            $('#product_id').val(0);
            $('.product_id').removeAttr('data-product-id');
            $('#productName').val("");
            $('#category').prop('selectedIndex', 0);
            $('#brand').prop('selectedIndex', 0);
            $('#supplier').prop('selectedIndex', 0);
            $('#unit').prop('selectedIndex', 0);
            $('#productDesc').val("");
            $('#btnSubmit').text('Thêm mới');
            inputs.each(function() {
                $(this).removeClass('font-weight-bold')
            })
            $('#warning').hide()
            $('#add_Image').hide()
            $('#show_Image').hide()
            if($('#addImage').show()) $('#addImage').hide()
        }
        else {
            // Populate the input and select fields with lesson information
            $('#id').val(product[0].id);
            $('#product_id').val(product[0].id);
            $('.product_id').attr('data-product-id', product[0].id);
            $('#productName').val(product[0].productName);
            $('#category').val(product[0].category.id);
            $('#brand').val(product[0].brand.id);
            $('#supplier').val(product[0].supplier.id);
            $('#unit').val(product[0].unit.id);
            $('#productDesc').val(product[0].productDesc);
            $('#btnSubmit').text('Sửa');
            inputs.each(function() {
                $(this).addClass('font-weight-bold')
            })
            $('#warning').show();
            $('#add_Image').show()
            $('#show_Image').show()
        }

    })
})
/*******************  Popup add image & button add image *********************/

$('#add_Image').click(function() {
    var divImage = $('#addImage');
    if (divImage.is(':hidden')) {
        divImage.show();
        $(this).text('Ẩn thêm hình ảnh');
    } else {
        divImage.hide();
        $(this).text('Thêm hình ảnh');
    }
});

/*******************  Popup show image product & button show image product *********************/

$(document).ready(function () {
    if($('#show_Image')) {
        // Hàm tạo các thẻ hình ảnh và thêm vào #card-body
        function createImageProduct(imageProducts, product_id, domain) {
            var hasImage = false;
            var cardBody = $("#card-body");
            var image_for_product = [];
            imageProducts.forEach(image => {
                if (image.product.id == product_id) image_for_product.push(image);
        });
            if(image_for_product.length == 0) {
                var p = $('<p>').text('Sản phẩm chưa có hình ảnh').addClass('font-weight-bold text-warning')
                cardBody.append(p);
                console.warn(`Image skipped: variant ID ${product_id} not found in image object.`);
            } else {
                image_for_product.forEach(image => {
                    var img = $('<img>')
                        .attr('src', domain + image.imgurl)
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
                .attr('href', '/Lengkeng/admin/products/delImage?url=' + encodePath(imgSrc.split("/leng-keng/")[1])+'&product_id='+product_id) // Thêm thuộc tính href
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


        $("#show_Image").click(function() {
            var product_id = $(this).attr('data-product-id');
            $("#card-body").empty();
            createImageProduct(imageProducts,product_id, domain);
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
})
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