function unescapeHtml(text) {
	const unescaped = {
		"&amp;": "&",
		"&#38;": "&",
		"&lt;": "<",
		"&#60;": "<",
		"&gt;": ">",
		"&#62;": ">",
		"&apos;": "'",
		"&#39;": "'",
		"&quot;": '"',
		"&#34;": '"',
		"&nbsp;": " ",
		"&#160;": " ",
	};
	return text.replace(/&(?:amp|#38|lt|#60|gt|#62|apos|#39|quot|#34|nbsp|#160);/g, function (m) {
		return unescaped[m];
	});
}

/**
 * 상대 경로 이미지를 절대 경로로 변경하는 함수
 */
function convertImageTagAbsoluteURL(doc = document) {
	if (isNull(doc)) return;
	// img tag replace Relative URL to Absolute URL.
	Array.from(doc.getElementsByTagName("img"), (x) => {
		// console.log(x);
		x.src = x.src; // 밑의 코드 대신 해당 코드를 써야 경로가 바꿔짐 (다른 환경에서 테스트 해보기)
		//x.setAttribute('src', x.currentSrc);
		return x;
	});
}

/** 일반 특수문자를 전각문자로 변환하는 함수
 * @param {string} text - 변환할 문자열
 * @returns {string} - 전각문자로 변환된 문자열
 */
function convertSingleCharToDoubleChar(text) {
	// singleChar to doubleChar mapping
	const map = {
		"!": "！",
		"%": "％",
		"&": "＆",
		"(": "（",
		")": "）",
		"*": "＊",
		"+": "＋",
		",": "，",
		"-": "－",
		".": "．",
		"/": "／",
		":": "：",
		";": "；",
		"<": "＜",
		"=": "＝",
		">": "＞",
		"?": "？",
		"@": "＠",
		"[": "［",
		"\\": "＼",
		"]": "］",
		"^": "＾",
		_: "＿",
		"`": "｀",
		"{": "｛",
		"|": "｜",
		"}": "｝",
		"~": "～",
		" ": " ", // 공백만 전각문자가 아닌 FOUR-PER-EM SPACE로 변환
	};
	return text.replace(/[!%&()*+,\-./:;<=>?@\[\\\]^_`{|}~ ]/g, function (m) {
		return map[m];
	});
}

/**
 * 해당 값이 null 또는 undefined인지 체크합니다.
 * @param {any} value - 체크할 값
 * @returns {boolean} - null이면 true, null이 아니면 false
 */
function isNull(value) {
	return value === null || value === undefined;
}

/**
 * base64로 문자열을 base64로 인코딩하여 반환합니다.
 * @param {string} str - base64로 인코딩할 문자열
 * @returns {string} - base64로 인코딩된 문자열
 */
function b64EncodeUnicode(str) {
	return btoa(
		encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function (match, p1) {
			return String.fromCharCode(`0x${p1}`);
		})
	);
}

/**
 * base64로 인코딩된 문자열을 base64로 디코딩하여 반환합니다.
 * @param {string} b64str - base64로 인코딩된 문자열
 * @returns {string} - base64로 디코딩된 문자열
 */
function b64DecodeUnicode(b64str) {
	return decodeURIComponent(
		atob(b64str)
			.split("")
			.map(function (c) {
				return `%${`00${c.charCodeAt(0).toString(16)}`.slice(-2)}`;
			})
			.join("")
	);
}
