<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%
	String itemNo=request.getParameter("itemNo");
%>
<script>
//페이지 로딩 후 가장 마지막에 실행하려고 $function을 하는 것
//jsp는 주석이 있어도 실행됨
	$(function (){
		$.ajax({
			url:'../GetProductServlet',
			data:{item_no : <%=itemNo%>},
			dataType:'json',
			success:function(result){
				console.log(result);
				$('#item_no').val(result.item_no);//body의 form태그에 있는 item_no에 값을 result.item_no로 넣어주라는 말
				$('#item_no').val(result.item);//위와 동일
			},
			error:function(reject){
				console.log(reject);
			}
		});
	});
	$('#btnChange').on('click',function(){});
</script>
</head>
<body>
	<form>
		<input type="text" id = 'item_no'>
		<input type="text" id = 'item'>
		<input type="submit" id = 'btnChange' value='수정'>
	</form>
</body>
</html>