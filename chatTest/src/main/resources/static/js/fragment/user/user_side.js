function goPage(page,index) {
    alert(`${page}로 이동합니다. \n
         선택한 메뉴 -? ${index}    
    `)

    


    // // ------------------- 두번째 방식(가장 많이 쓰는 방식) ---------------//
    // fetch(`/user/${page}`, { //요청경로
    //     method: 'POST',
    //     cache: 'no-cache',
    //     headers: {
    //         'Content-Type': 'application/json; charset=UTF-8'
    //     },
    //     //컨트롤러로 전달할 데이터
    //     body: JSON.stringify({
    //         // 데이터명 : 데이터값
    //     })
    // })
    //     .then((response) => {
    //         return response.json(); //나머지 경우에 사용
    //     })
    //     //fetch 통신 후 실행 영역
    //     .then((data) => {//data -> controller에서 리턴되는 데이터!

    //     })
    //     //fetch 통신 실패 시 실행 영역
    //     .catch(err => {
    //         alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
    //         console.log(err);
    //     });
}