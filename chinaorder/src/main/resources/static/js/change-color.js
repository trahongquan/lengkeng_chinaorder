
/******************* click btn-edit-variant sửa *********************/

$(document).ready(function () {
    var inputs = $('.variant');
    $('#warning').hide();
    $('.btn-edit-variant').click(function() {
        var variant_id = $(this).attr('data-id');
        console.log('variant_id = ' + variant_id)
        console.log('variants = ' + variants)
        var variant = $.grep(variants, function(variant1) {
            return variant1.id == variant_id;
        });
        console.log(variant[0]);


        if($('#id').val()==variant_id){
            $('#id').val(0);
            $('#variant_id').val(0);
            $('#category').prop('selectedIndex', 0);
            $('#product').prop('selectedIndex', 0);
            $('#sku').val("");
            $('#checkboxSize').prop('checked', false);
            $('#size').prop('selectedIndex', 0);
            $('#color').prop('selectedIndex', 0);
            $('#costPrice').val("");
            $('#sellingPrice').val("");
            $('#stockQuantity').val("");
            $('#btnSubmit').text('Thêm mới');
            $('#color').css('background-color', 'white');
            // $('#color').trigger('change')
            inputs.each(function() {
                $(this).removeClass('font-weight-bold')
            })
            $('#warning').hide()
            $('#add_Image').hide()
            if($('#addImage').show()) $('#addImage').hide()

        }
        else {
            // Populate the input and select fields with lesson information
            $('#id').val(variant[0].id);
            $('#variant_id').val(variant[0].id);
            $('#category').val(variant[0].product.category.id);
            $('#product').val(variant[0].product.id);
            $('#sku').val(variant[0].sku);
            if (variant[0].size != null) {
                $('#checkboxSize').prop('checked', true); // Tích vào checkbox
                $('#size').val(variant[0].size.id);       // Đặt giá trị cho select
                $('#checkboxSize').trigger('change')
            } else {
                $('#size').val('');                        // Xóa giá trị của select
                $('#checkboxSize').prop('checked', false); // Bỏ tích checkbox
            }
            $('#color').val(variant[0].color.id);
            $('#color').css('background-color', variant[0].color.hexCode);
            $('#costPrice').val(variant[0].costPrice);
            $('#sellingPrice').val(variant[0].sellingPrice);
            $('#stockQuantity').val(variant[0].stockQuantity);
            $('#btnSubmit').text('Sửa');
            inputs.each(function() {
                $(this).addClass('font-weight-bold')
            })
            $('#warning').show();
            $('#add_Image').show()
        }
    })
})

/******************* Popup Attribute *********************/

$('#add_Attribute').click(function() {
    var form = $('#addAttributes');
    if (form.is(':hidden')) {
        form.show();
        $(this).text('Ẩn thêm thuộc tính');
    } else {
        form.hide();
        $(this).text('Thêm thuộc tính mới');
    }
});

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

/*******************  Disable size *********************/


// Khởi tạo: Đặt giá trị select là rỗng và vô hiệu hóa nó
$('#size').val('');
$('#size').prop('disabled', true);
// Kiểm soát trạng thái của checkbox
$('#checkboxSize').on('change', function() {
    console.log('xxx')
    if ($(this).is(':checked')) {
        $('#size').prop('disabled', false); // Bật select
    } else {
        $('#size').prop('disabled', true); // Tắt select
        $('#size').val(''); // Xóa giá trị của select
    }
});
/*******************  Thay đổi màu slect color *********************/


$('#color').change(function() {
    var selectedColor = $(this).find('option:selected').attr('data-hexcode');
    $(this).css('background-color', selectedColor);
});
$('#color').trigger('change')