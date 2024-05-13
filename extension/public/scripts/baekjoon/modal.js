let bojData = null;

/**
 * 백준 페이지에 디렉토리 선택 Modal을 추가하는 함수
 */
function addModal() {
  let body = document.querySelector('body');

  body.innerHTML =
    body.innerHTML +
    `
    <div class="recode-modal-bg"></div>
    <div class="recode-modal-wrap">

			<div style="display: flex; flex-direction: column; width : 100%; height : 100%; position:relative;">
			<div id="recodebackdrop" class="" style="position : absolute;"></div>
			<p class="recodeheader">RE:CODE</p>
			<div class="recodediv">
				<div id="Recode_progress_elem" class="" style="position : absolute;"></div>
				<div style="width : 100%; display : flex;">
				<div>코드 제목 입력<span style="font-size:10px"> (최대 20자)</span></div>
				</div>
				<input type="text" id="nameinput" maxlength="20" placeholder="코드를 요약해주세요."
				style="padding : 4px; background-color: rgb(226 232 240 / var(--tw-bg-opacity));
				border-radius: 6px !important; width : 100%; height : 32px; --tw-bg-opacity: 1;"/>
				<div id="resultdiv"> </div>
				<div style="width : 100%; display : flex; justify-content: space-between;">
					<button  class="modal-close btn recodebtn">닫기</button>
					<button id="upload" class="btn recodebtn">업로드</button>
				</div>
			</div>
			</div>
        
    </div>`;

  let selectBtn = document.querySelector('#upload');
  selectBtn.addEventListener('click', function () {
    if (!bojData) {
      alert('오류가 발생하였습니다. 새로고침 후 다시 시도해주세요.');
      return;
    }

    // 로딩 CSS 표시
    const elem = document.getElementById('Recode_progress_elem');
    elem.className = 'Recode_progress';
    const backdrop = document.getElementById('recodebackdrop');
    backdrop.className = 'recodebackdrop';

    const nameInput = document.getElementById('nameinput');
    bojData.code.name = nameInput.value || '빈 제목 입니다.';

    fetch('https://www.recode-d210.com/api/problems/regist', {
      method: 'POST',
      body: JSON.stringify(bojData),
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then((res) => {
        if (res.status == 409) {
          alert('이미 동일한 코드를 등록한 적이 있어요!');
        }
      })
      .finally(() => {
        popClose();
        elem.className = '';
        backdrop.className = '';
        nameInput.value = '';
      });
  });

  let modalclose = document.querySelector('.modal-close');
  modalclose.addEventListener('click', popClose);
}

async function popOpen(data) {
  bojData = data;
  const elem = document.getElementById('Recode_progress_elem');
  elem.className = ''; // 기존 완료 아이콘 CSS 없애기

  let modalPop = document.querySelector('.recode-modal-wrap'); // $('.modal-wrap');
  let modalBg = document.querySelector('.recode-modal-bg'); // $('.modal-bg');

  modalBg.style = 'display: block';
  modalPop.style = 'display: block';
}

function popClose() {
  let modalPop = document.querySelector('.recode-modal-wrap'); // $('.modal-wrap');
  let modalBg = document.querySelector('.recode-modal-bg'); // $('.modal-bg');
  modalBg.style = 'display: none';
  modalPop.style = 'display: none';
}
