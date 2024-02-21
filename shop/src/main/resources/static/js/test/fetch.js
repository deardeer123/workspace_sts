// // ------------------- 첫번째 방식 ---------------//
// fetch('/', { //요청경로
//     method: 'POST',
//     cache: 'no-cache',
//     headers: {
//         'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
//     },
//     //컨트롤러로 전달할 데이터
//     body: new URLSearchParams({
//        // 데이터명 : 데이터값
//     })
// })
// .then((response) => {
//     if(!response.ok){
//         alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
//         return ;
//     }

//     return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
//     //return response.json(); //나머지 경우에 사용
// })
// //fetch 통신 후 실행 영역
// .then((data) => {//data -> controller에서 리턴되는 데이터!

// })
// //fetch 통신 실패 시 실행 영역
// .catch(err=>{
//     alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
//     console.log(err);
// });


function fetch1() {
    // ------------------- 두번째 방식(가장 많이 쓰는 방식) ---------------//
    fetch('/fetch/fetch1', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
            // 데이터명 : 데이터값
            'id':'java',
            'name':'홍',
            'age':20
        })
    })
        .then((response) => {
            //return response.text();
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            console.log(data)
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}

function fetch2() {
    // ------------------- 두번째 방식(가장 많이 쓰는 방식) ---------------//
    fetch('/fetch/fetch2', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
            // 데이터명 : 데이터값
            'id':'java',
            'name' : '홍',
            'age' : 20
        })
    })
        .then((response) => {
            return response.text();
            //return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            console.log(data)
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}

function fetch3() {
    // ------------------- 두번째 방식(가장 많이 쓰는 방식) ---------------//
    const arr = [];
    for(let i = 1; i<=5; i++){
        const member = {
            'name' : `홍_${i}`,
            'age' : 20+i ,
            'id' : `java_${i}`
        };
        arr.push(member);
    }


    fetch('/fetch/fetch3', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify(arr)
    })
        .then((response) => {
            return response.text();
            //return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            console.log(data)
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}


function fetch4() {
    // ------------------- 두번째 방식(가장 많이 쓰는 방식) ---------------//
    const imgList = [
        {
            'imgName' : 'aaa.jpg',
            'imgCode' : 3
        },
        {
            'imgName' : 'bbb.jpg',
            'imgCode' : 4
        }
    ];


    const cartInfo = {
        'cartCode' : 1,
        'memberInfo' : {
            'memberId' : 'java' ,
            'memberName' : '홍'
        },
        'itemInfo' : {
            'itemCode' : 5 ,
            'itemName' : '상품1' ,
            'itemPrice' : 5000 ,
            'imgList' : imgList
        }

        };

    console.log(cartInfo.itemInfo.imgList[0].imgName)


    fetch('/fetch/fetch4', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify(cartInfo)
        
    })
        .then((response) => {
            return response.text();
            //return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            console.log(data)
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}




