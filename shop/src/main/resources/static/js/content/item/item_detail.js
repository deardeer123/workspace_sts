document.querySelector("#count").addEventListener('input', function(){
    let count = document.querySelector("#count").value;
    let total = document.querySelector("#total").value;
    let price = document.querySelector("#price").value;
    console.log(price)
    console.log(count)
    if(count == '' || count <= 1){
        document.querySelector("#count").value = 1;
        count = 1;
    }
    total = parseInt(price) * parseInt(count);
    document.querySelector("#total").textContent = `￦${total.toLocaleString()}`;
    console.log(total)
});

function setTotalPrice(inputTag){
    const cnt = inputTag.value;
}


function goCart1(itemCode,memberInfo){
    const cartCnt = document.querySelector('input[type="number"]').value;
    if(memberInfo == null){
        alert("로그인 하십쇼")
        return ;
    }

    fetch('/cart/insertCart', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           'itemCode' : itemCode ,
           'cartCnt' : cartCnt 
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
        console.log(data);
        if(confirm(`장바구니에 상품을 담았습니다.\n장바구니 페이지로 이동할까요?`)){
            location.href="/cart/list";
        }else{
            return ;
        }

    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}


//바로 구매 버튼 선택시 실행!!
function insertBuy(){
    //총 가격 정보를 input value로 세팅!!1
    const totalPriceStr = document.querySelector('.total-price-span')

    const regex = /[^0-9]/g;
        const totalPrice = parseInt(totalPriceStr.replace(regex,'')); // ￦20,000 -> 20000
        document.querySelector('input[name="totalPrice"').value = totalPrice;

    //submit
    document.querySelector('#insert-buy-form').submit();
}

