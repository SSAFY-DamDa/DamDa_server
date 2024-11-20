// 비밀번호 찾기 클릭 핸들러
function handleFindBtn(e) {
  e.preventDefault();
  
  const userId = document.getElementById("userid").value;

  fetch(`${root}/user/findpwd/${userId}`, {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json'
		},
	})
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw new Error('탈퇴 실패');
			}
		})
		.then(data => {
			console.log("data:",data);
			document.getElementById("userpwd").value = data.password;
		})
		.catch(error => {
			console.error('Error:', error);
			alert('비밀번호 찾기 중 오류가 발생했습니다.');
		});	
}