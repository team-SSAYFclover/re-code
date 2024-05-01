/**
 * 채점 결과 테이블 td로 문제 정보를 가져오는 함수
 */
function getProblemInfo(td) {
	var problemInfo = {
		submitNo: td[0].textContent,
		problemNo: td[2].textContent,
		level: td[2].querySelector("img")?.src.split("r/")[1].replace(".svg", ""),
		submitTime: td[8].textContent,
	};

	return problemInfo;
}

/**
 * 문제 정보(문제 번호, 설명, 입출력, 티어 이미지)을 가져오는 함수
 */
function parseProblemDescription(doc = document) {
	//이미지에 상대 경로가 있을 수 있으므로 이미지 경로를 절대 경로로 전환
	convertImageTagAbsoluteURL(doc.getElementById("problem_description"));

	// let problem_tier_img = doc.querySelector('.page-header > blockquote');
	// problem_tier_img = problem_tier_img.innerHTML.split('&nbsp')[0].replace('<img', '<img width="20px" ');

	const problemId = doc
		.getElementsByTagName("title")[0]
		.textContent.split(":")[0]
		.replace(/[^0-9]/, "");
	const problem_title = doc.getElementById("problem_title").innerHTML.trim();
	const problem_description = unescapeHtml(
		doc.getElementById("problem_description").innerHTML.trim()
	);
	const problem_input = unescapeHtml(doc.getElementById("problem_input")?.innerHTML.trim?.()); // eslint-disable-line
	const problem_output = unescapeHtml(doc.getElementById("problem_output")?.innerHTML.trim?.()); // eslint-disable-line

	const tags = doc.getElementsByClassName("spoiler-link");

	let problem_tags = [];
	for (let i = 0; i < tags.length; i++) {
		problem_tags.push(tags[i].innerHTML.trim());
	}

	if (problemId && problem_description) {
		// if (debug) console.log(`문제번호 ${problemId}의 내용을 저장합니다.`);
		return {
			problemId,
			problem_title,
			problem_description,
			problem_input,
			problem_output,
			problem_tags,
		};
	}
	return {};
}

/**
 * Readme를 생성하는 함수
 */
function makeDetailMessageAndReadme(problem_info) {
	const {
		submitNo /* 제출 번호 */,
		code /* 소스 코드 */,
		level /* 문제 레벨(티어) */,
		problemId /* 문제 번호 */,
		problem_description /* 문제 설명 */,
		problem_title /* 문제 명 */,
		problem_input /* 문제 입력 */,
		problem_output /* 문제 출력 */,
		problem_tags /*문제 분류*/,
		id,
	} = problem_info;

	const readme =
		`## 문제\n` +
		`${problem_description}\n\n` +
		`## 입력\n` +
		`${problem_input}\n\n` +
		`## 출력\n` +
		`${problem_output}\n\n`;

	return {
		id,
		problem: {
			problemNo: problemId,
			title: problem_title,
			level: level,
			content: readme,
			tags: problem_tags,
		},
		code: { codeNo: submitNo, content: code },
	};
}
