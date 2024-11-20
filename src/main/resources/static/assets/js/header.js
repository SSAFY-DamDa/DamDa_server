let ID = "";
let PWD = "";
let PWDConfirm = "";
let NAME = "";
let EMAIL = "";

//아이디 입력값 핸들러
function handleIdChange(e) {
	ID = e.target.value;
}

//비밀번호 입력값 핸들러
function handlePWDChange(e) {
	PWD = e.target.value;
}

//비밀번호 확인 핸들러
function handlePWDConfirmChange(e) {
	PWDConfirm = e.target.value;
}

//닉네임 입력값 핸들러
function handleNameChange(e) {
	NAME = e.target.value;
}

//이메일 입력값 핸들러
function handleEmailChange(e) {
	EMAIL = e.target.value;
}

// 로그인 '확인' 클릭 핸들러
function handleClickConfirm(e) {
	e.preventDefault(); // 폼 기본 제출 막기
	const formData = {
		userId: document.getElementById('loginUserid').value,
		userPwd: document.getElementById('loginUserpwd').value,
		saveid: document.getElementById('saveid').checked ? "ok" : null
	};

	fetch(`${root}/user/login`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(formData)
	})
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw new Error('로그인 실패');
			}
		})
		.then(data => {
			//Token : localStorage에 저장
			localStorage.setItem("token", JSON.stringify(data));
		
			// 로그인 성공 시 처리 로직
			window.location.href = `${root}/`;
		})
		.catch(error => {
			console.error('Error:', error);
			alert('로그인 중 오류가 발생했습니다.');
		});

}

// 회원가입 확인 클릭 핸들러
function handleClickSignupConfirm(e) {
	e.preventDefault();

	const formData = {
		userId: document.getElementById('signUserid').value,
		userPwd: document.getElementById('signUserpwd').value,
		userName: document.getElementById('signUsername').value,
		emailId: document.getElementById('signEmailid').value,
		emailDomain: document.getElementById('signEmaildomain').value
	};

	fetch(`${root}/user/join`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(formData)
	})
		.then(response => {
			if (response.ok) {
				alert('회원가입 성공');
				closeSignupModal(e);
			} else {
				throw new Error('회원가입 실패');
			}
		})
		.catch(error => {
			console.error('Error:', error);
			alert('회원가입 중 오류가 발생했습니다.');
		});
}

// 로그아웃 함수 정의
function handleLogout(event) {
	event.preventDefault(); // 기본 동작 방지

	fetch(`${root}/user/logout`, {
		method: 'GET', // 로그아웃은 간단한 GET 요청으로 처리할 수 있습니다.
	})
		.then(response => {
			if (response.ok) {
				// 로그아웃 성공 시 로직
				alert('로그아웃 성공');
				window.location.href = `${root}/`; // 메인 페이지로 리디렉션
			} else {
				throw new Error('로그아웃 실패');
			}
		})
		.catch(error => {
			console.error('Error:', error);
			alert('로그아웃 중 오류가 발생했습니다.');
		});
}

//로그인 성공 시 헤더 변경
function showLogout() {
	const logoutSection = document.getElementById('header_logout');
	logoutSection.style.display = 'flex'; // display를 flex로 변경

	const loginSection = document.getElementById('header_login');
	loginSection.style.display = 'none'; // 로그인 섹션은 숨김
}

// 모달창 열기
const loginmodal = document.getElementById("loginModalContainer");
const signupmodal = document.getElementById("signupModalContainer");

function openLoginModal(e) {
	e.preventDefault();
	loginmodal.style.display = "flex";
}

function openSignupModal(e) {
	e.preventDefault();
	signupmodal.style.display = "flex";
}

//모달창 닫기
function closeLoginModal(e) {
	e.preventDefault();
	document.querySelector("#loginUserpwd").value = null;
	loginmodal.style.display = "none";
}

function closeSignupModal(e) {
	e.preventDefault();
	document.querySelector("#signUserid").value = null;
	document.querySelector("#signUserpwd").value = null;
	document.querySelector("#pwdcheck").value = null;
	document.querySelector("#signUsername").value = null;
	document.querySelector("#signEmailid").value = null;
	document.querySelector("#signEmaildomain").value = null;
	signupmodal.style.display = "none";
}