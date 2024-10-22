function remove(button) {
    var grandParent = button.parentNode.parentNode;
    grandParent.remove();
}


function addInput(button) {

    console.log(attributes);
    var targetDiv = $(button).closest('.d-flex.row.flex-wrap.justify-content-start');
    var newDiv = $('<div>', {
        'class': 'd-flex flex-column justify-content-start align-content-center m-2 col-3'
    });
    var innerDiv = $('<div>', {
        'class': 'd-flex row justify-content-center align-content-center'
    });
    var select = $('<select>', {
        'class': 'form-control col-6',
        'name': 'attribute',
        'required': true
    });
    $.each(attributes, function(index, attribute) {
        $('<option>', {
            'value': attribute.id,
            'text': attribute.name
        }).appendTo(select);
    });
    var btnRemove = $('<button>', {
        'type': 'button',
        'class': 'btnremove btn btn-primary col-3 ml-2',
        'onclick': 'remove(this)'
    });
    $('<i>', { 'class': 'fa fa-times-circle' }).appendTo(btnRemove);
    innerDiv.append(select);
    innerDiv.append(btnRemove);
    var inputSearch = $('<input>', {
        'type': 'search',
        'class': 'form-control m-3',
        'id': 'value',
        'name': 'value',
        'required': true,
        'placeholder': 'Giá trị thuộc tính'
    });
    newDiv.append(innerDiv);
    newDiv.append(inputSearch);
    targetDiv.append(newDiv);
    $(button).before(newDiv);
}

