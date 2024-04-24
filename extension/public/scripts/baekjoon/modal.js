let bojData = null;

/**
 * 백준 페이지에 디렉토리 선택 Modal을 추가하는 함수
 */
function addModal() {
	let body = document.querySelector("body");

	body.innerHTML =
		body.innerHTML +
		`
    <div class="modal-bg"></div>
    <div class="modal-wrap">
			<h1>ReCode</h1>
			
        <div id="BaekjoonHub_progress_elem" class=""></div>

        <button  class="modal-close btn">닫기</button>
        <button id="upload" class="btn">업로드</button>
    </div>`;

	let selectBtn = document.querySelector("#upload");
	selectBtn.addEventListener("click", function () {
		if (!bojData) {
			alert("오류가 발생하였습니다. 새로고침 후 다시 시도해주세요.");
			return;
		}

		// 로딩 CSS 표시
		const elem = document.getElementById("BaekjoonHub_progress_elem");
		elem.className = "BaekjoonHub_progress";

		// TODO: 등록 API 호출
	});

	let modalclose = document.querySelector(".modal-close");
	modalclose.addEventListener("click", popClose);
}

async function popOpen(data) {
	bojData = data;
	const elem = document.getElementById("BaekjoonHub_progress_elem");
	elem.className = ""; // 기존 완료 아이콘 CSS 없애기

	let modalPop = document.querySelector(".modal-wrap"); // $('.modal-wrap');
	let modalBg = document.querySelector(".modal-bg"); // $('.modal-bg');
	let json = await getObjectFromLocalStorage("directoryMap");

	// if (isNull(json)) {
	// 	alert("Re:code 계정과 연결되지 않았습니다. 연동해주세요.");
	// 	return;
	// }

	// json = JSON.parse(json);

	// $("#tree").bstreeview({ data: JSON.stringify(json) });

	modalBg.style = "display: block";
	modalPop.style = "display: block";
}

function popClose() {
	let modalPop = document.querySelector(".modal-wrap"); // $('.modal-wrap');
	let modalBg = document.querySelector(".modal-bg"); // $('.modal-bg');
	modalBg.style = "display: none";
	modalPop.style = "display: none";
}
