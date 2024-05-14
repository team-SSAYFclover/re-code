const currentUrl = window.location.href;
const regex = /^https:\/\/www\.acmicpc\.net\/status.{0,}/i;

function findUsername() {
  if (USER_NAME != null) return USER_NAME;
  if (currentUrl == 'https://www.acmicpc.net/') return null;

  const el = document.querySelector('a.username');
  // console.log("username = " + el);

  if (el == null) return null;

  const username = el?.innerText?.trim();
  if (username == null) return null;

  return username;
}

/**
 * 현재 페이지가 채점 결과 페이지라면
 * 업로드 버튼 추가 함수 실행
 */
if (regex.test(currentUrl)) {
  chrome.storage.local.get(['id']).then((res) => {
    if (res.id) {
      addModal();
      addUploadBtnToResult();
    }
  });
}

/**
 * 채점 결과에 업로드 버튼 추가하는 함수
 * 맞춘 문제에 대해서만 적용
 */
function addUploadBtnToResult() {
  var resultTable = document.querySelector('.table-responsive');
  var results = resultTable.querySelectorAll('tbody > tr');

  for (const rows of results) {
    var td = rows.querySelectorAll('td');
    USER_NAME = findUsername();

    if (
      td[1].textContent == USER_NAME &&
      (td[3].textContent == '맞았습니다!!' || td[3].textContent == '100점')
    ) {
      //alert(submitNo + ' : ' + user + ' ' + problemNo + ' ' + result + ' ' + memory + ' ' + time + ' ' + lang + ' ' + byte + ' ' + submitTime);
      td[3].style = 'justify-content:center';
      td[3].innerHTML =
        td[3].innerHTML +
        '<div style="float:right;"><a href="javascript:void(0);" class="uploadBtn fa fa-upload" >RE:CODE</a></div>';
    }
  }

  // a 태그에 클릭 이벤트 등록(이렇게해야 해당 a 태그에 해당하는 문제 정보를 가져올 수 있음)
  var uploadbtn = document.querySelectorAll('.uploadBtn');
  uploadbtn.forEach((it) =>
    it.addEventListener('click', async function () {
      const td = it.parentNode.parentNode.parentNode.querySelectorAll('td');
      // 채점 결과 테이블의 데이터를 가져옴
      const table_data = getProblemInfo(td);

      // problem_info로 데이터 병합
      const problem_info = Object.assign(
        {},
        table_data,
        await chrome.storage.local.get(['id']),
        await fetchProblemDescriptionById(table_data.problemNo),
        await findSubmissionCode(table_data.submitNo)
      );

      const bojData = makeDetailMessageAndReadme(problem_info);
      popOpen(bojData);
    })
  );
}
