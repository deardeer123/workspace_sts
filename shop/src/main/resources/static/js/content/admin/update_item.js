 const updateItemCode = document.querySelector("#updateItemCode").value;
console.log(updateItemCode);

if(updateItemCode != 0){
    updateItemForm(updateItemCode);
}
//console.log("tlqkf");

function updateItemForm(itemCode) {
    // let selectItemCate = document.querySelector("#select-item-cate");
    // let selectItemName = document.querySelector("#select-item-name");
    // let selectItemStockTag = document.querySelector("#select-item-stock");

    // let selectItemReadyTag = document.querySelector("#select-item-ready");
    // let selectItemSaleTag = document.querySelector("#select-item-sale");
    // let selectItemNotReadyTag = document.querySelector("#select-item-notready");

    // let selectItemMainImgTag = document.querySelector("#select-item-main-img");
    // let selectItemDetailImgTag = document.querySelector("#select-item-detail-img");


    fetch('/admin/updateItem', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            // 데이터명 : 데이터값
            'itemCode': itemCode
        })
    })
        .then((response) => {
            if (!response.ok) {
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return;
            }
        

            //return response.text(); //컨트롤러에서 return하가 없거나 int, String는 데이터 일 때 사용
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            console.log(data);
            console.log(data.itemDetail)
            console.log(data.cateList)

            const detail_div = document.querySelector('.detail-div')
            detail_div.innerHTML = '';
            let str11 = '';
            str11 += `
            
            <form action="/admin/updateItem1" method="post">

            <div class="row">
                <div class="col">

                    <!-- 상품 기본정보 -->
                    <div class="row mb-3">
                        <div class="col">
                            <h1>상품 기본 정보</h1>
                        </div>
                    </div>
                
                
                    <input type="hidden" name="itemCode" value="${data.itemDetail.itemCode}">

                    <div class="row bg-warning me-5">
                        <div class="col ">
        
                            <div class="row mt-1">
                                <div class="col-2">
                                    <!-- 카테고리명 -->
                                    카테고리
                                </div>
                            
                                <div class="col-6">
                                    <select class="form-select" id="select-item-cate" name="cateCode">
                                    `

                                    for (let e of data.cateList) {
                                        if (e.cateCode == data.itemDetail.itemCategoryVO.cateCode) {
                                            str11 += `<option selected value="${e.cateCode}">${e.cateName}</option>`
                                        }
                                        else{str11 += `<option value="${e.cateCode}">${e.cateName}</option>`
                                        }
                                    }

                                    str11 += `
                                    </select>
                                </div>
                            </div>


                        <div class="row mt-2">
                            <div class="col-2">
                                <!-- 상품명 -->
                                상품명
                            </div>

                            <div class="col-6">
                                <input type="text" name="itemName" class="form-control" id="select-item-name" value="${data.itemDetail.itemName}">
                            </div>
                        </div>


                        <div class="row mt-2">
                            <div class="col-2">
                                <!-- 상품수량 -->
                                상품 수량
                            </div>

                            <div class="col-6">
                                <input type="text" name="itemStock" class="form-control" id="select-item-stock" value="${data.itemDetail.itemStock}">
                            </div>
                        </div>


                        <div class="row mt-2">

                            <div class="col-2">
                                <!-- 상품상태 -->
                                상품 상태
                            </div>

                            <div class="col-6">
                                <!-- 라디오 버튼 -->
                                <div class="row">
                                    <div class="col">
                                        <div class="form-check">
                                        `
                                        // if(data.itemDetail.itemStatus == 1){
                                        //     str11+=`<input  checked class="form-check-input" type="radio" name="flexRadioDefault"
                                        //     id="select-item-ready">`
                                        // }


                                        str11 += `
                                            <input class="form-check-input1" type="radio" name="itemStatus" value="1"
                                                id="select-item-ready">
                                            <label class="form-check-label" for="itemStatus">
                                                준비중
                                            </label>
                                        </div>
                                    </div>

                                    <div class="col">
                                        <div class="form-check">
                                    `
                                        // if(data.itemDetail.itemStatus == 2){
                                        //     str11+=`<input  checked class="form-check-input" type="radio" name="flexRadioDefault"
                                        //    id="select-item-sale">`
                                        // }


                                        str11 += `
                                            <input class="form-check-input1" type="radio" name="itemStatus" value="2"
                                                id="select-item-sale" >
                                            <label class="form-check-label" for="itemStatus">
                                                판매중
                                            </label>
                                        </div>
                                    </div>
                                    
                                    <div class="col">
                                        <div class="form-check">
                                        `
                                        // if(data.itemDetail.itemStatus == 3){
                                        //     str11+=`<input  checked class="form-check-input" type="radio" name="flexRadioDefault"
                                        //    id="select-item-notready">`
                                        // }


                                        str11 += `
                                            <input class="form-check-input1" type="radio" name="itemStatus" value="3"
                                                id="select-item-notready">
                                            <label class="form-check-label" for="itemStatus">
                                                매진
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
        

                </div>
            </div>


            <div class="row mt-5">
                <div class="col">
                    <div class="row">
                        <div class="col">
                            <h1>상품 이미지 정보</h1>
                        </div>
                    </div>
                

                    <div class="row bg-warning me-5">
                        <div class="col">

                            <div class="row mt-4 ">
                                <div class="col-3">
                                    <!-- 메인이미지  -->
                                    메인 이미지
                                </div>
                                <div class="col-9" id="select-item-main-img">
                                `
                            

                                data.itemDetail.itemImageList.forEach(function(e , index){
                                    if(e.isMain == 'Y') {
                                        str11 += `<div onclick='imgModal(${e.imageCode})'>${e.origenFileName}</div>`
                                        str11 += `<div onclick="showModal('${e.attachedFileName}')">테스트</div>`
                                    }
                                });

                                str11 += `
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-3">
                                    상세이미지/
                                </div>
                            
                            
                                <div class="col-9" id="select-item-detail-img">
                                    <div class="row">
                                    
                                        `
                                        let cnt = 0;
                                        for (let e of data.itemDetail.itemImageList) {
                                            if (e.isMain == 'N') {
                                                    if(cnt == 0){
                                                        str11 += `<div onclick='imgModal(${e.imageCode})'>${e.origenFileName}</div>`
                                                    }
                                                    else{   
                                                        str11 += `<div onclick='imgModal(${e.imageCode})'>${e.origenFileName}</div>`
                                                    }
                                                    cnt++;
                                                }
                                            }
                                        str11 += `
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>


            <div class="row mt-4">
                <div class="col-4 d-grid mx-auto">
                    <input type="button" class="btn btn-primary" value="비동기변경" onclick='changeDetail(${data.itemDetail.itemCode})'>
                    <input type="submit" class="btn btn-primary" value="동기변경">
                </div>
            </div>
            </form>
            `;


            detail_div.insertAdjacentHTML('afterbegin', str11)

            if (data.itemDetail.itemStatus == 1) {
                document.querySelector("#select-item-ready").checked = true
            }
            else if (data.itemDetail.itemStatus == 2) {
                document.querySelector("#select-item-sale").checked = true

            } else if (data.itemDetail.itemStatus == 3) {
                document.querySelector("#select-item-notready").checked = true
            }





            // console.log(data)
            // //상품 이름
            // selectItemName.value = data.itemName;
            // //상품 수량
            // selectItemStockTag.value = data.itemStock;

            // //카테고리
            // let cateCode = data.itemCategoryVO.cateCode;
            // console.log(cateCode);
            // cateTags = selectItemCate.children;
            // for (let tag of cateTags) {
            //     console.log(tag.value)
            //     if (tag.value == cateCode) {
            //         tag.selected = true;
            //     }
            // }


            // selectItemMainImgTag.innerHTML = '';
            // selectItemDetailImgTag.innerHTML = '';


            // //상태 정보
            // let str = data.itemStatus;
            // console.log(str)
            // if (str == 1) {
            //     str = '준비중'
            //     selectItemReadyTag.checked = true;
            // } else if (str == 2) {
            //     str = '판매중'
            //     selectItemSaleTag.checked = true;
            // } else if (str == 3) {
            //     str = '매진';
            //     selectItemNotReadyTag.checked = true;
            // }


            // //이미지 파트
            // let imgName = ``;
            // let img2Name = ``;

            // for (let e of data.itemImageList) {

            //     if (e.isMain == 'Y') {
            //         let str = e.attachedFileName.replace(".jpg", "")
            //         imgName = `
            //             <p onclick='imgModal(${e.imageCode})'>${e.origenFileName}</p>
            //         `

            //     }
            //     if (e.isMain == 'N') {
            //         let str = e.attachedFileName.replace(".jpg", "")
            //         img2Name = `
            //             <p onclick='imgModal(${e.imageCode})'>${e.origenFileName}</p>
            //         `
            //             ;
            //     }
            // }

            // console.log(imgName);
            // console.log(img2Name);

            // console.log(selectItemMainImgTag);
            // console.log(selectItemDetailImgTag);

            // selectItemMainImgTag.innerHTML = imgName
            // selectItemDetailImgTag.innerHTML = img2Name

            //let img_modal = new bootstrap.Modal('#img-modal');
            //모달 열기
            // buy_detail_modal.show();
            // //모달 닫기
            // buy_detail_modal.hide();


           
    


        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });

}

function changeDetail(itemCode){
    const itemName = document.querySelector("#select-item-name").value;
    const itemStock = document.querySelector("#select-item-stock").value;
    const radioTags = document.querySelectorAll(".form-check-input1")
    let itemStatus;
    let cateCode;
    

    console.log(`itemName : ${itemName}`)
    console.log(`itemStock : ${itemStock}`)
    for(const radioTag of radioTags){
        if(radioTag.checked == true){
            itemStatus = radioTag.value
            console.log(`itemStatus : ${radioTag.value}`)
        }
    }
    

    const cateListTag = document.querySelector("#select-item-cate").children;
    for(const cateTag of cateListTag){
        if(cateTag.selected == true){
            console.log(cateTag);
            cateCode = cateTag.value;
            console.log(`cateCode : ${cateTag.value}`);
        }
    }
    

    fetch('/admin/changeDetail', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            // 데이터명 : 데이터값
            'itemCode' : itemCode ,
            'itemName' : itemName ,
            'itemStock' : itemStock ,
            'itemStatus' : itemStatus ,
            'cateCode' : cateCode
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
            let tbodyTag = document.querySelector("#itemListtBody")
            tbodyTag.innerHTML=''
            let str1 =``;

            data.forEach(function(item,index){
                console.log(1);
                let str = `
                <tr class="table-primary" onclick="updateItemForm(${item.itemCode})">
                    <td>${data.length - index}</td>
                    <td>${item.itemCode}</td>
                    <td>${item.itemName}</td>
                    <td>${item.itemStock}</td>
                    <td>
                       ${item.strStatus}
                    </td>
                </tr>
            `
            str1+=str;
            })

            tbodyTag.insertAdjacentHTML("afterbegin", str1);
           

        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });

}





function imgModal(imgCode) {

    let imageCode = parseInt(imgCode)
    console.log(imageCode);
    console.log(document.querySelector("#img-modal-body2"));
    //const img_tag = document.querySelector("#img-modal img")
    //img_tag.src=''
    document.querySelector("#img-modal-body2").innerHTML = ``;
   
    
    //모달 지정
    let img_modal = new bootstrap.Modal('#img-modal');

    fetch('/admin/modalImg', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            // 데이터명 : 데이터값
            imageCode: imageCode
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
            
            //모달 열기
            let str1 = `
            <img src="/upload/${data.attachedFileName}"
                class="rounded float-center" alt="햄스타" width="100%">
            `
            document.querySelector("#img-modal-body2").insertAdjacentHTML("afterbegin", str1);

            img_modal.show();
            // //모달 닫기
            // buy_detail_modal.hide();



        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });

}

function showModal(attachedFileName){
    console.log(document.querySelector("#img-modal-body2"));
    const img_tag = document.querySelector("#img-modal img")
    img_tag.src=`/upload/${attachedFileName}`;
    //모달 지정
    let img_modal = new bootstrap.Modal('#img-modal');
    //모달보여주기
    img_modal.show();
}