/**
 * showPage.js
 */

function showPage() {
	let doc = xhttp.responseXML;
	console.log(doc);

	let data = doc.getElementsByTagName('record');
	console.log(data);

	let tableTag = document.createElement('table');
	tableTag.setAttribute('border', '1');

	//title tr
	// data tr =>[배열]
	let headerTr = titleRow(data);
	let dataTr = contentRow(data);

	tableTag.appendChild(headerTr);
	for (let i = 0; i < dataTr.length; i++) {
		tableTag.appendChild(dataTr[i]);
	}
	document.getElementById('show').appendChild(tableTag);
}//end of showPage

//title
function titleRow(result) {//result=매개변수
	console.log(result[0].childNodes[3].nodeName);
	let trTag = document.createElement('tr');
	for (let i = 0; i < result[0].childNodes.length; i++) {
		let tdTag = document.createElement('th');
		let textNode = document.createTextNode(result[0].childNodes[i].nodeName.toUpperCase());
		tdTag.append(textNode);
		trTag.append(tdTag);
	}
	let tdTag = document.createElement('th');
	let textNode = document.createTextNode("처리");
	tdTag.append(textNode);
	trTag.append(tdTag);
	return trTag;
}

//content
function contentRow(result) {
	let trTags = [];
	for (let j = 0; j < result.length; j++) {
		let trTag = document.createElement('tr');
		for (let i = 0; i < result[0].childNodes.length; i++) {
			let tdTag = document.createElement('td');
			let textNode = document.createTextNode(result[j].childNodes[i].firstChild.nodeValue);
			tdTag.appendChild(textNode);
			trTag.appendChild(tdTag);
		}
		//td 배경색
		//onmouseover
		trTag.onmouseover = function() {
			trTag.style.background = 'yellow';
		}//onmouseout
		trTag.onmouseout = function() {
			trTag.style.background = '';
		}
		//임의로 버튼 추가
		let button = document.createElement('button');
		button.innerHTML = '삭제';
		button.onclick = function() {
			console.log(this.parentNode.parentNode.remove());//this 버튼 //parent tr 2번짼 td
			let id = this.parentNode.parentNode.childNodes[0].firstChild.nodeValue;
			let req = new XMLHttpRequest();
			req.open('get', '../deleteEmp?empId=' + id);
			req.send();
			req.onload = function() {
				console.log(req.responseText);
			}
		}
		let tdTag = document.createElement('td');
		tdTag.appendChild(button);
		trTag.appendChild(tdTag);

		trTags.push(trTag);
	}// for의 끝(tr)
	return trTags;
}//end of contentRow
