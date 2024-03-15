
const updateItemCode = document.querySelector('#updateItemCode').value;

if(updateItemCode != 0){
    getDetail(updateItemCode);
}

//상품 목록 테이블의 행 클릭 시 상품의 상세 정보 조회
function getDetail(itemCode){
    fetch('/admin/selectItemDetail', { //요청경로
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
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }
    
        //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        console.log(data);
        console.log(data.itemDetail);
        console.log(data.cateList);


       const detail_div = document.querySelector('.detail-div');
       detail_div.innerHTML = '';

       let str = '';
       str += `
       <div class="row">
            <div class="col h3">
                상품 기본 정보
            </div>
        </div>
        <form action="/admin/updateItem" method="post">
        <input type="hidden" name="itemCode" value="${data.itemDetail.itemCode}">
        <div class="row mb-3">
            <div class="col col-bg">
                <div class="row mb-3">
                    <label class="col-3 col-form-label">카테고리</label>
                    <div class="col-9">
                        <select class="form-select" name="cateCode">`;

                        for(const category of data.cateList){
                            if(category.cateCode == data.itemDetail.cateCode){
                                str += `<option value="${category.cateCode}" selected>${category.cateName}</option>`;
                            }
                            else{
                                str += `<option value="${category.cateCode}">${category.cateName}</option>`;
                            }
                        }

str+=                   `</select>
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-3 col-form-label">상품명</label>
                    <div class="col-9">
                        <input name="itemName" type="text" class="form-control" value="${data.itemDetail.itemName}">
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-3 col-form-label">상품수량</label>
                    <div class="col-9">
                        <input name="itemStock" type="number" class="form-control" value="${data.itemDetail.itemStock}">
                    </div>
                </div>
                <div class="row mb-3 align-items-center">
                    <label class="col-3 col-form-label">상품상태</label>
                    <div class="col-9">
                        <div class="form-check form-check-inline">`;
                            if(data.itemDetail.itemStatus == 1){
                                str += `<input checked class="form-check-input" type="radio" name="itemStatus" value="1">`;
                            }
                            else{
                                str += `<input class="form-check-input" type="radio" name="itemStatus" value="1">`;
                            }
                        
                        str+=    `<label class="form-check-label">준비 중</label>
                        </div>
                        <div class="form-check form-check-inline">`;
                        if(data.itemDetail.itemStatus == 2){
                            str += `<input checked class="form-check-input" type="radio" name="itemStatus" value="2">`;
                        }
                        else{
                            str += `<input class="form-check-input" type="radio" name="itemStatus" value="2">`;
                        }
                        str += `<label class="form-check-label">판매 중</label>
                        </div>
                        <div class="form-check form-check-inline">`;
                        if(data.itemDetail.itemStatus == 3){
                            str += `<input checked class="form-check-input" type="radio" name="itemStatus" value="3">`;
                        }
                        else{
                            str += `<input class="form-check-input" type="radio" name="itemStatus" value="3">`;
                        }

                            
                        str +=    `<label class="form-check-label">매진</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col h3">
                상품 이미지 정보
            </div>
        </div>
        <div class="row">
            <div class="col col-bg">
                <div class="row mb-3">
                    <div class="col-3">
                        메인 이미지
                    </div>
                    <div class="col-9">`;
                    
                    for(const img of data.itemDetail.imgList){
                        if(img.isMain == 'Y'){
                            str += `<span onclick="showModal('${img.attachedFileName}')">${img.originFileName}</span>`;
                        }
                    }


        str +=     `</div>
                </div>
                <div class="row mb-3">
                    <div class="col-3">
                        상세 이미지
                    </div>`;

                    let cnt = 0;
                    data.itemDetail.imgList.forEach(function(img, idx){
                        if(img.isMain == 'N'){
                            if(cnt == 0){
                                str += `
                                    <div class="col-9">
                                        <span onclick="showModal('${img.attachedFileName}')">${img.originFileName}</span>
                                    </div>
                                `;

                                cnt++;
                            }
                            else{
                                str += `
                                    <div class="offset-3 col-9">
                                        <span onclick="showModal('${img.attachedFileName}')">${img.originFileName}</span>
                                    </div>
                                `;
                            }
                        }    
                    });

    str +=         `</div>
            </div>
        </div>
        <div class="row">
            <div class="col-4 d-grid">
                <input type="submit" value="변 경" class="btn btn-primary">
            </div>
        </div>
        </form>
       `;
       
       detail_div.insertAdjacentHTML('afterbegin', str);
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

//이미지 모달창 띄우기
function showModal(attachedFileName){
    const img_modal = new bootstrap.Modal('#img-modal');

    const img_tag = document.querySelector('#img-modal img');
    img_tag.src= `/upload/${attachedFileName}`;

    img_modal.show();
}


