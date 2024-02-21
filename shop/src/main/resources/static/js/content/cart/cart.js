setFinalPrice();

//총 가격 계산 함수
function setFinalPrice(){
    const chks = document.querySelectorAll('.chk:checked');
    //클래스가 chk인 태그 중에서 체크가 된 태그만 선택!!
    let sum = 0;

    chks.forEach((chk, index)=>{
        //chk 각각의 같은 행에 있는 총 가격 데이터 찾아가기
        const strPrice = chk.closest('tr').children[5].textContent;
        
        //정규식을 사용해서 쉼표와 원화표시를 제거.
        const regex = /[^0-9]/g;
        const price = parseInt(strPrice.replace(regex,'')); // ￦20,000 -> 20000
        sum = sum + price


    })
    let str = `총가격 : ￦${sum.toLocaleString()}원`;
    document.querySelector('.h3').textContent = str;

}

//제목줄 체크 박스 체크 및 해체 시 모든 체크박스 체크 및 해제
function checkAll(){
    const chkAll = document.querySelector('#chkAll');
    const shks = document.querySelector('.chk');

    if(chkAll.checked){
        for(const chk of chks){
            chk.checked = true;
        }
    }else{
        for(const chk of chks){
            chk.checked = false;
        }
    }
}

function goDelete(cartCode){
    if(confirm("진짜 삭제할거에요?")){
        location.href=`/cart/delete?cartCode=${cartCode}`;
    }

}


function changeCartCnt(selectTag,cartCode,price){
    let cartCnt = selectTag.parentNode.previousElementSibling.children[0].value;
    // selectTag.closest('div').children[0].children[0].value
    // selectTag.colsest('.row').querySelector('input[type="number"]).value

    fetch('/cart/changeCartCnt', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           cartCnt : cartCnt,
           cartCode : cartCode
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        //alert(`변경 ${data}`)
        let strPrice = selectTag.closest('tr').children[5].textContent;
        console.log(strPrice);

        let str = `￦${data.toLocaleString()}`;

        selectTag.closest('tr').children[5].textContent = str;
        //이게 더 나은거 같음

        const changeTotalPrice = parseInt(cartCnt) * parseInt(price);
        console.log(changeTotalPrice);
        //td안에 있는 총가격에 <span>으로 감싸줌
        //selectTag.closest('tr').querySelector('. totalPrice-span').textContent = '￦' + changeTotalPrice.toLocaleString();


        setFinalPrice();
        alert('상품의 수량이 변경되었어요')
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function deleteCarts(){
    //선택 삭제
    const chks = document.querySelectorAll('.chk:checked');
    if(chks.length == 0){
        alert('삭제할 상품을 선택하세요');
        return;
    }
    //컨트롤러로 넘겨줄 cartCode들
    let cartCodes = [];
    for(const chk of chks){
        console.log(chk.value);
        cartCodes.push(chk.value);
    }
    location.href=`/cart/deleteCarts?cartCodeList=${cartCodes}`

}
//예전에 만들어 놓은거
function choiceDelete(){
    const chks = document.querySelectorAll('.chk:checked');
    //let cartCodeList = [];
    let cartCodes = '';
    for(e of chks){
        //cartCodeList.push(e.value);
        cartCodes = cartCodes +","+ e.nextElementSibling.value
    }
    console.log(cartCodes);
    if(cartCodes == ""){
        alert("삭제할 상품을 선택하세요!!!!!")
        return;
    }else{
        location.href=`/cart/choiceDelete?cartCodes=${cartCodes}`
    }
}

function goBuy(){
    const chks = document.querySelectorAll('.chk:checked');
    if(chks.length ==0){
        alert('구매할 상품을 선택하세요')
        return;
    }
    let cartCodeList =[] //여기다가 담자
    for(const chk of chks){
        cartCodeList.push(chk.value);
    }


    location.href=`/buy/buyCarts?cartCodeList=${cartCodeList}`
}

function goBuy1(){
    const chks = document.querySelectorAll('.chk:checked');

    if(chks.length ==0){
        alert('구매할 상품을 선택하세요')
        return;
    }
    let testList = new Object(); //여기다가 담자

    for(const chk of chks){
        function cart(itemCode, totalPrice, buyCnt){
            this.itemCode = itemCode;
            this.totalPrice = totalPrice;
            this.buyCnt = buyCnt;
        }

        console.log(chk.closest('tr').children[1].children[1].value)
        console.log(`itemCode ${chk.closest('tr').children[1].children[2].value}`)
        console.log(`totalPrice ${chk.closest('tr').children[1].children[3].value}`)
        console.log(`cartCnt ${chk.closest('tr').children[1].children[4].value}`)

        // cart1 = [];
        // cart1.push(chk.closest('tr').children[1].children[2].value)
        // cart1.push(chk.closest('tr').children[1].children[3].value)
        // cart1.push(chk.closest('tr').children[1].children[4].value)


        let cart1 = new cart(chk.closest('tr').children[1].children[2].value,
            chk.closest('tr').children[1].children[3].value ,
            chk.closest('tr').children[1].children[4].value
        );
        
        testList.push(cart1)
    }
    console.log(testList)
    console.log(typeof(testList))
    console.log(testList[0])
    console.log(testList[1])
    console.log(testList[2])

    //location.href=`/buy/buyCarts1?cartList=${cartList}`
    // ------------------- 첫번째 방식 ---------------//
    // ------------------- 첫번째 방식 ---------------//
    // fetch('/buy/buyCarts1', { //요청경로
    //     method: 'POST',
    //     cache: 'no-cache',
    //     headers: {
    //         'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    //     },
    //     //컨트롤러로 전달할 데이터
    //     body: new URLSearchParams({
    //     // 데이터명 : 데이터값
    //         testList : testList
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


    
    // ------------------- 두번째 방식(가장 많이 쓰는 방식) ---------------//
    fetch('/buy/buyCarts1', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
           // 데이터명 : 데이터값
           testList : testList
        })
    })
    .then((response) => {
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    
}