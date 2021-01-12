console.log('first');
$(document).ready(function () { //document 다 로딩된 후 함수 실행하겠다는 의미
    console.log('second');
    console.log(document.getElementById('food_1').childNodes[1].childNodes[0].nodeValue);
    console.log($('#food_1').children().eq(0).html());
    //eq 몇번째 메소드인지(firstChild와 같음) html()은 innerHTML과 같음 인덱스값도 함수의 매개값과 같이 사용
    $('#food_1 > ul > li').eq(0).css('background', 'red');
    $('#food_1 > ul > li').eq(1).html('bulgogi');
});
console.log('third');