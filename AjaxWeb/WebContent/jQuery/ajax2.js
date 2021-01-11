$(function (){
    $.ajax({
        url: '../data/MOCK_DATA.json',
        dataType: 'json',
        success: showContent,
        error: function (result) {
            console.log('에러: ' + result.statusText);
        }
    });
    //button
    $('#btn').on('click',function(){
        $('input:checked').parent().parent().remove();
        // $('tr :checked').parent().parent().remove(); 와 동일
        // tr 태그 사용 시 checked 사이 띄울 것(하위요소를 지정한다는 의미를 가지기 때문)
    });
    //all_check
    $('body').on('click','#all_check',function(){
        console.log('checked');
        $('td > input').prop('checked',$('#all_check').is(":checked"));
        //td안의 input태그들의 속성값을 가져온다?
        if($('#all_check').is(":checked"))
            $('td>input').prop('checked',true);
            else{
                $('td>input').prop('checked',false);
            }
    });
})

function showContent(result){
    let headers = ['chkbox','id', 'first_name', 'last_name', 'email'];

    let data = result;
    let table = $('<table id="tbl" />').attr('border', '1');
    let titles = $('<tr />');
    
    for(field of headers){
        let td = $('<td/>');
        if (field == 'chkbox'){
            console.log(field);
            let checkbox = $('<input />').attr('type','checkbox').attr('id','all_check');
            td.append(checkbox);
        } else {
            td = $('<th/>').html(field.replace('_',' ').toUpperCase());
        }
        titles.append(td);
    }
    table.append(titles);
    $(data).each(function (idx,obj){
        if(idx<5){
            let tr = $('<tr/>');
            $(tr).attr('td',obj.id);

            $(tr).on({
                'mouseover':function(){
                    $(this).css('background-color','turquoise')
                },
                'mouseout':function(){
                    $(this).css('background-color','')
                }
            });
            for(field of headers){
                let td = $('<td/>');
                if(field =='chkbox'){
                    let checkbox = $('<input/>').attr('type','checkbox');
                    td.attr('align','center');
                    td.append(checkbox);
                }else{
                    td.html(obj[field]);
                }
                tr.append(td);
            }
            table.append(tr);
        }
    })
    $('#show').append(table);
}