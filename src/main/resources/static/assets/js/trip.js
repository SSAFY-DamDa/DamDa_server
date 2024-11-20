
/**
 * 
 */
let map = null;

const script = document.createElement("script");
script.src = "http://dapi.kakao.com/v2/maps/sdk.js?appkey=df98b282fbb023c22d16c21903c667d9&autoload=false&libraries=clusterer,services&";
document.head.appendChild(script);
script.onload = () => {
    kakao.maps.load(() => {
        const container = document.getElementById('map'); // 지도를 표시할 div
		const options = {
			center: new kakao.maps.LatLng(33.450701, 126.570667),
			level: 3,
		};
        map = new kakao.maps.Map(container, options);
    });
};


let currentPage = 1;  // 현재 페이지를 추적

window.onload = () => {
	console.log("onload!");
	fetchPage(currentPage);
};


//페이지네이션과 함께 페이지를 가져오는 함수
function fetchPage(page) {
	fetch(`${root}/trip/list?pgno=${page}`	, {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json'
			}
		})
		.then(response => {
					if (response.ok) {
						return response.json();
					} else {
						throw new Error('로딩 실패');
					}
				})
		.then(data => {
			makeList(data.tripList);// 기존 리스트에 새로운 데이터를 추가
			makeOption(data.siList);
			setupPagination(data.pageNavigation);
		})
		.catch(error => console.error('Error:', error));
}

// 페이지네이션 HTML을 설정하고 이벤트 리스너를 추가하는 함수
function setupPagination(navigatorHtml) {
	document.getElementById("pagination").innerHTML = navigatorHtml;
	// 새로 추가된 페이지네이션 링크에 이벤트 리스너 추가
	document.querySelectorAll(".pagination .page-link").forEach(link => {
		link.addEventListener("click", function(e) {
			e.preventDefault();
			const pgNo = e.target.getAttribute("data-pg");
			currentPage = parseInt(pgNo);
			fetchPage(currentPage);
		});
	});
}

function loadMoreData(page) {
	fetch(`${root}/trip/scrolllist&pgno=${page}`)
		.then(response => response.json())
		.then(data => {
			appendList(data);  // 기존 리스트에 새로운 데이터를 추가
		})
		.catch(error => console.error('Error:', error));
}


function appendList(data) {
	let tripList = '';  // 리스트로 출력할 HTML
	positions = [];  // 마커를 표시할 좌표 리스트
	data.forEach(area => {
		const imgSrc = area.img1 ? area.img1 : area.img2 ? area.img2 : `${root}/assets/img/default__background.svg`;

		tripList += `
            <li class="result__item">
                <div class="item__content">
                    <img src="${imgSrc}" alt="" class="item__img" />
                    <div class="content">
                        <span class="item__title">${area.title}</span>
                        <span class="item__address content">${area.addr1} ${area.addr2}</span>
                    </div>
                </div>
            </li>
        `;

		// 마커 정보 추가
		let markerInfo = {
			title: area.title,
			latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
			area: area
		};
		positions.push(markerInfo);
	});


	// 리스트를 HTML에 업데이트
	document.querySelector(".result_list").innerHTML += tripList;

	// 지도에 마커 표시
	displayMarker();
}

//페이지네이션 결정
document.querySelectorAll(".pagination .page-link").forEach(link => {
	link.addEventListener("click", function(e) {
		e.preventDefault();
		const pgNo = e.target.parentNode.getAttribute("data-pg");

		fetch(`${root}/trip/list&pgno=${pgNo}`)
			.then(response => response.json())
			.then(data => {
				makeList(data);  // 결과 리스트를 업데이트하는 함수
			})
			.catch(error => console.error('Error:', error));
	});
});

function makeOption(data) {
	//const areas = data.response.body.items.item;
	// console.log(areas);
	const sel = document.getElementById("search-area");
	data.forEach((area) => {
		const opt = document.createElement("option");
		//opt.setAttribute("value", area.code);
		//opt.appendChild(document.createTextNode(area.name));
		opt.value = area.sido_code;
		opt.textContent = area.sido_name;
		sel.appendChild(opt);
	});
}

document.getElementById("btn-search").addEventListener("click", () => {

	let search_area = document.querySelector("#search-area");
	let selectedArea = search_area.value;


	let select_content = document.querySelector("#search-content-id");
	let selectedContent = select_content.value;

	let search_keyword = document.querySelector("#search-keyword");
	let selectedKeyword = search_keyword.value;


	fetch(`${root}/trip/search?areaCode=${selectedArea}&contentTypeId=${selectedContent}&title=${selectedKeyword}&pgno=${currentPage}`)
		.then(response => response.json())
		.then(data => {
			makeList(data.tripList);
			setupPagination(data.pageNavigation);
		})
		.catch(error => console.error('Error:', error));


});

document.getElementById("search-keyword").addEventListener("keydown", (event) => {
  if (event.keyCode === 13) {
    event.preventDefault(); // 엔터키에 대해 기본 동작(폼 제출)을 막습니다.
    
    console.log("enter");

    // 검색 기능 수행
    let search_area = document.querySelector("#search-area");
    let selectedArea = search_area.value;

    let select_content = document.querySelector("#search-content-id");
    let selectedContent = select_content.value;

    let search_keyword = document.querySelector("#search-keyword");
    let selectedKeyword = search_keyword.value;

    fetch(`${root}/trip/search?areaCode=${selectedArea}&contentTypeId=${selectedContent}&title=${selectedKeyword}&pgno=${currentPage}`)
      .then(response => response.json())
      .then(data => {
        makeList(data.tripList);
        setupPagination(data.pageNavigation);
      })
      .catch(error => console.error('Error:', error));
  }
});

var positions; // marker 배열.
function makeList(data) {
	let tripList = '';  // 리스트로 출력할 HTML
	positions = [];  // 마커를 표시할 좌표 리스트

	data.forEach(area => {
		// 리스트에 HTML 추가
		const imgSrc = area.img1
			? area.img1
			: area.img2
				? area.img2
				: `${root}/assets/img/default__background.svg`;

		tripList += `
            <li class="result__item" onclick="moveCenter(${area.latitude}, ${area.longitude});">
                <div class="item__content">
                    <img src="${imgSrc}" alt="" class="item__img" />
                    <div class="content">
                        <span class="item__title">${area.title}</span>
                    </div>
                    <span class="item__address content">${area.addr1} ${area.addr2}</span>
                </div>
            </li>
        `;

		// 마커 정보 추가
		let markerInfo = {
			title: area.title,
			latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
			area: area
		};
		positions.push(markerInfo);
	});

	// 리스트를 HTML에 업데이트
	document.querySelector(".result_list").innerHTML = tripList;

	// 지도에 마커 표시
	displayMarker();
}

function displayMarker() {
	//clearMarkers(); // 기존 마커 제거

	var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

	positions.forEach((positionInfo, index) => {
		var imageSize = new kakao.maps.Size(24, 35);  // 마커 이미지 크기
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);  // 마커 이미지 생성

		var marker = new kakao.maps.Marker({
			map: map,
			position: positionInfo.latlng,
			title: positionInfo.title,
			image: markerImage
		});

		//markers.push(marker); // 마커를 배열에 추가 마커 제거 위함

		// 마커 클릭 시 커스텀 오버레이 표시
		(function(marker, positionInfo) {
			var overlay = new kakao.maps.CustomOverlay({
				map: map,
				position: marker.getPosition(),
			});

			// 오버레이 내용 설정
			const wrap = document.createElement("div");
			wrap.setAttribute("class", "wrap");

			const info = document.createElement("div");
			info.setAttribute("class", "info");

			const titleDiv = document.createElement("div");
			titleDiv.setAttribute("class", "title");
			titleDiv.textContent = positionInfo.title;

			const closeDiv = document.createElement("div");
			closeDiv.setAttribute("class", "close");
			closeDiv.addEventListener('click', function() {
				overlay.setMap(null);  // 오버레이 닫기
			});

			titleDiv.appendChild(closeDiv);
			info.appendChild(titleDiv);

			const bodyDiv = document.createElement("div");
			bodyDiv.setAttribute("class", "body");


			const img = document.createElement("img");
			img.setAttribute("class", "img");

			const imgSrc = positionInfo.area.img1
				? positionInfo.area.img1
				: positionInfo.area.img2
					? positionInfo.area.img2
					: `${root}/assets/img/default__background.svg`;

			img.setAttribute("src", imgSrc);
			bodyDiv.appendChild(img);

			const desc = document.createElement("div");
			desc.setAttribute("class", "desc");

			const ellipsis = document.createElement("div");
			ellipsis.setAttribute("class", "ellipsis");
			ellipsis.textContent = positionInfo.area.addr1;

			const jibun = document.createElement("div");
			jibun.setAttribute("class", "jibun ellipsis");
			jibun.textContent = `(우) ${positionInfo.area.addr2}`;

			desc.appendChild(ellipsis);
			desc.appendChild(jibun);
			bodyDiv.appendChild(desc);
			info.appendChild(bodyDiv);
			wrap.appendChild(info);

			overlay.setContent(wrap);

			// 마커 클릭 시 오버레이를 표시
			kakao.maps.event.addListener(marker, "click", function() {
				overlay.setMap(map);  // 오버레이를 지도에 표시
				overlays.push(overlay);
			});
		})(marker, positionInfo);  // 클로저를 사용하여 각각의 마커에 대해 독립적으로 이벤트 처리
	});

	// 첫 번째 좌표로 지도 이동
	if (positions.length > 0) {
		map.setCenter(positions[0].latlng);
	}
}

//마커 제거하기
const markers = []; // 현재 지도에 표시된 마커들을 저장하는 배열
const overlays = []; // 현재 지도에 표시된 오버레이들을 저장하는 배열
function clearMarkers() {
	markers.forEach(marker => marker.setMap(null)); // 모든 마커를 지도에서 제거
	//overlays.forEach(overlay => overlay.setMap(null)); //모든 오버레이 지도에서 제거
	markers = []; // 배열 초기화
	overlays = [];
}


// 오버레이를 닫는 함수
function closeOverlay(overlay) {
	overlay.setMap(null);  // 오버레이를 지도에서 제거
}

function moveCenter(lat, lng) {
	map.setCenter(new kakao.maps.LatLng(lat, lng));
}