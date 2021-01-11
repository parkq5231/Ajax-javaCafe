$(function () {
    $.ajax({
        url: '../data/MOCK_DATA.json',
        dataType: 'json',
        success: showContent,
        error: function (result) {
            console.log('에러: ' + result.statusText);
        }
    });
    //button
    $('#btn').click();
    //find
    $('#find').on('click', function () {
        let findId = $('#find_id').val();
        let findRow = $('#' + findId + '').css('background-color', 'gray');
        //tr
        let tr = makeNewTr();
        findRow.before(tr);
    });
})

function makeNewTr() {
    let inputs = $('.input.val');
    let tr = $('<tr/>');
    $(tr).click(trToInputFunc);
    $(tr).hover(function () {
        $(this).css('background-color', 'yellow');
    }),
    function () {
        $(this).css('background-color', '');
    }
    for (let i = 0; i < inputs.length; i++) {
        let td = $('<td/>').html(inputs[i].value);
        tr.append(td);
    }
    return tr;
}

function addRow() {
    let tr = makeNewTr();
    $('#tbl').append(tr);
}

function btnFunc() {
    console.log($(this));
    let tr = $('<tr/>');
    let tdId = $('<td/>').html($('#id').val());
    let tdFirst = $('<td/>').html($('#first_name').val());
    let tdLast = $('<td/>').html($('#last_name').val());
    let email = $('<td/>').html($('#email').val());
    let trVal = $(tr).append(tdId, tdFirst, tdLast, email);
    $('#tbl').append(trVal);
}
function showContent(result) {
    let headers = ['id', 'first_name', 'last_name', 'email'];
    let data = result;
    let table = $('<table border ="1"/>').attr('id', 'tbl');
    let titles = $('<tr/>');
    for (field of headers) {
        let td = $('<td/>').html(field.replace('_', '').toUpperCase());
        titles.append(td);
    }
    table.append(titles);
    $(data).each(function (idx, obj) {
        if (idx < 5) {
            let tr = $('<tr/>');
            $(tr).attr('id', 'obj_id');
            $(tr).on({
                'click': trToInputFunc,
                'mouseover': function () {
                    $(this).css('background-color', 'yellow')
                },
                'mouseout': function () {
                    $(this).css('background-color', '')
                }
            });
            for (field of headers) {
                let td = $('<td/>');
                td.html(obj[field]);
                tr.append(td);
            }
            table.append(tr);
        }
    })
    $('#show').append(table);
}
function trToInputFunc() {
    console.log($(this).children().eq(0).html())
    $('#id').val($(this).children().eq(0).html());
    $('#first_name').val($(this).children().eq(1).html());
    $('#last_name').val($(this).children().eq(2).html());
    $('#email').val($(this).children().eq(3).html());
}