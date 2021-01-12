/**
 * cafeList.js
 */
$(function() {//js전부 확인 후라 js확인가능
	$.ajax({
		url: '../getProdList',
		dataType: 'json',
		success: showContents,
		error: showErrors

	});

});

//showContents(); // 그냥 호출
function showErrors(result) {
	console.log(result);
}
function showContents(result)  {//success의 성공한 결과값을 매개값으로 받아옴
	console.log(result);
	let data = result;//result에 들어온 매개값을 data라는 변수에 담아줌
	for (val of data){
	// console.log(val.item_no,val.item,val.price);
	let elem_1, elem_2, elem_3;
	//첫번째 자식요소
	elem_1 = $("<a/>").attr('href', val.link);
	let e1_img = $('<img/>').attr('src', '../images/'+val.image).attr('alt', val.alt);

	e1_img.addClass("card-img-top");
	elem_1.append(e1_img);
	//두번째 자식요소
	elem_2 = $("<div/>").addClass("card-body");
	let e2_h4 =  $('<h4/>').addClass('card-title');
	let e2_a = $('<a/>').attr('href', val.link).html(val.item);	//DB의 Item가져옴
	//원화
	let krw_price=new Intl.NumberFormat('ko-KR',{style:'currency',currency:'KRW'}).format(val.price);
	
	let e2_h5 = $('<h5/>').html(krw_price);							//DB의 price가져옴
	let e2_p =  $('<p/>').addClass ('card-text').html(val.content);//DB의 content가져옴

	e2_h4.append(e2_a);
	elem_2.append(e2_h4, e2_h5, e2_p);
	
	//세번째 자식요소
	elem_3 = $('<div/>').addClass('card-footer');
	let star='';
	for (let i = 0; i<val.likeIt;i++){
		star += '&#9733;';
		console.log(val.likeIt);
	}	
	let e3_small = $('<small/>').addClass("text-muted").html(star);	
	elem_3.append(e3_small);
 	//total  
	let div1 = $('<div/>').addClass('col-lg-4 col-md-6 mb-4');
	let div2 = $('<div/>').addClass('card h-100');

	div1.append(div2);
	div2.append(elem_1, elem_2, elem_3);

	$('.col-lg-9 .row').append(div1);
		}
		//let db_value="db_value";
}//end of showContents