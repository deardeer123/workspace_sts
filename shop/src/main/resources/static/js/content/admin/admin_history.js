
function buyListSearch() {


    const searchType = document.querySelector("#searchType").value;
    const searchValue = document.querySelector("#searchValue").value;
    const searchMaxDate = document.querySelector("#searchMaxDate").value;
    const searchMinDate = document.querySelector("#searchMinDate").value;

    console.log(searchType);
    console.log(searchValue);
    console.log(searchMaxDate);
    console.log(searchMinDate);


    fetch('/admin/buyListSearch1', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            // 데이터명 : 데이터값
            'searchType' : searchType ,
            'searchValue' : searchValue ,
            'searchMaxDate' : searchMaxDate ,
            'searchMinDate' : searchMinDate
        })
    })
        .then((response) => {
            if (!response.ok) {
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return;
            }

            //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            console.log(data);
            let tbodyTag = document.querySelector("#buyListBody");

            let str = ``;
            tbodyTag.innerHTML='';
            let count = 0;
            for(const e of data){

                let a =
                `
                <tr data-bs-toggle="modal"
                    data-bs-target="#buy-detail-modal" onclick='showModal(${e.buyCode})'>
                    <td>${data.length - count}</td>
                    <td>${e.buyCode}</td>
                    <td>${e.memberId}</td>
                    <td>￦${e.buyPrice.toLocaleString()}</td>
                    <td>${e.buyDate}</td>
                </tr>
                 `
                 str = str + a;
                 count++;
            }
            tbodyTag.insertAdjacentHTML("afterbegin",str);



        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });

}

//부트스트랩이 제공하는 모달 태그를 선택하는 방법
let buy_detail_modal = new bootstrap.Modal('#buy-detail-modal');

//모달 열기
// buy_detail_modal.show();
// //모달 닫기
// buy_detail_modal.hide();

function showModal(buyCode) {
    //행 클릭시 구매 상세 내역 조회 및 모달창 띄우기

    let buy_detail_modal = new bootstrap.Modal('#buy-detail-modal');
    let buyCode1 = buyCode;
    alert(buyCode1);

    // ------------------- 첫번째 방식 ---------------//
    fetch('/admin/selectBuyDetail', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            // 데이터명 : 데이터값
            'buyCode': buyCode
        })
    })
        .then((response) => {
            if (!response.ok) {
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return;
            }

            //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            console.log(data)

            let modalHeaderTag = document.querySelector(".buy-modal-header")
            let modalBodyTag = document.querySelector(".buy-modal-body")
            modalHeaderTag.innerHTML='';
            modalBodyTag.innerHTML='';
           
            let str1 = `
            <div class="row text-center">
                <div class="col" style="font-size: 0.7em;">
                    구매 상세 내역
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <table class="table text-center table-borderless align-middle"
                        style="font-size: 0.8em;">
                        <colgroup>
                            <col width="20%">
                            <col width="20%">
                            <col width="20%">
                            <col width="*%">
                        </colgroup>

                        <tbody>
                            <tr>
                                <td>구매번호</td>
                                <td>${data.buyCode}</td>
                                <td>구매자ID</td>
                                <td>${data.memberId}</td>
                            </tr>

                            <tr>
                                <td>구매금액</td>
                                <td>￦${data.buyPrice.toLocaleString()}</td>
                                <td>구매일시</td>
                                <td>${data.buyDate}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            `
            modalHeaderTag.insertAdjacentHTML("afterbegin",str1);
            str1=`
            <table class="table table-borderless align-middle text-center " style="font-size: 0.7em; margin-top: -0.5em;">
            <colgroup>
                <col width="15%">
                <col width="40%">
                <col width="15%">
                <col width="*">
            </colgroup>

            <thead style="margin-top: 0.5em;">
                <tr>
                    <td>No</td>
                    <td>구매상품</td>
                    <td>수량</td>
                    <td>결제금액</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            `
            data.buyDetailVOList.forEach((e, index) => {
                let str2 = `
                <tr>
                    <td>${data.buyDetailVOList.length-index}</td>
                    <td>
                        <div class="row">
                            <div class="col-5">
                                 <img src="/upload/${e.itemVO.itemImageList[0].attachedFileName}"
                            class="rounded float-end" alt="햄스타" width="50%">
                            </div>
                            <div class="col-7 mt-4">
                             ${e.itemVO.itemName}
                            </div>
                        </div>
                                    
                    </td>
                    <td>${e.buyCnt}</td>
                    <td>￦${e.totalPrice.toLocaleString()}</td>
                </tr>
                `
                str1 = str1 + str2;
            });
            // for(let e of data.buyDetailVOList){ 
            //     let str2 = `
            //     <tr>
            //         <td>${data.buyDetailVOList.length}</td>
            //         <td>
            //             <div class="row">
            //                 <div class="col-5">
            //                      <img src="/upload/${e.itemVO.itemImageList[0].attachedFileName}"
            //                 class="rounded float-end" alt="햄스타" width="50%">
            //                 </div>
            //                 <div class="col-7 mt-4">
            //                  ${e.itemVO.itemName}
            //                 </div>
            //             </div>
                                    
            //         </td>
            //         <td>${e.buyCnt}</td>
            //         <td>￦${e.totalPrice.toLocaleString()}</td>
            //     </tr>
            //     `
            //     str1 = str1 + str2;
            // }
            
            str1 = str1 + `
                </tbody>
            </table>
            `
            modalBodyTag.insertAdjacentHTML("afterbegin",str1);
            buy_detail_modal.show();
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });

}