

function handleModify(e) {
	e.preventDefault();

	alert("수정하시겠습니까?");

	const formData = {
		userId: document.getElementById('userid').value,
		userPwd: document.getElementById('userpwd').value,
		userName: document.getElementById('username').value,
		emailId: document.getElementById('emailid').value,
		emailDomain: document.getElementById('emaildomain').value,
	};
	
	const accessToken = JSON.parse(localStorage.getItem("token")).accessToken;	

	fetch(`${root}/user/modify`, {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json',
			 'Authorization' : `${accessToken}`
		},
		body: JSON.stringify(formData)
	})
		.then(response => {
			if (response.ok) {
				alert("수정이 완료되었습니다");
				window.location.href = `${root}/`;
			} else {
				throw new Error('수정 실패');
			}
		})
	
		.catch(error => {
			console.error('Error:', error);
			alert('수정 중 오류가 발생했습니다.');
		});


}

function handleExit(e) {
	e.preventDefault();

	const formData = {
		userId: document.getElementById('userid').value,
		userPwd: document.getElementById('userpwd').value,
		userName: document.getElementById('username').value,
		emailId: document.getElementById('emailid').value,
		emailDomain: document.getElementById('emaildomain').value,
	};

	fetch(`${root}/user/delete`, {
		method: 'DELETE',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(formData)
	})
		.then(response => {
			if (response.ok) {
				console.log(response);
				window.location.href = `${root}/`;
			} else {
				throw new Error('탈퇴 실패');
			}
		})
		.catch(error => {
			console.error('Error:', error);
			alert('탈퇴 중 오류가 발생했습니다.');
		});


	alert("성공적으로 탈퇴되었습니다.");
}
