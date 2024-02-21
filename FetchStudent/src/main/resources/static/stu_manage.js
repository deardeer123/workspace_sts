function goSelect(){
    let classCode = document.querySelector("#class-selector").value;
    alert(classCode);
    location.href =`/?classCode=${classCode}`;
}
function fetchSelect(){
    let classCode = document.querySelector("#class-selector").value;
    //선택한 학급 데이터 가지고옴

    fetch('/fetchSelect', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           //위에 선언한 데이터 가져감
           classCode : classCode
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

        //tbody 태그를 선택!!!! css 자식 구하는법 사용가능!!!!!!!
        const tbodyTag = document.querySelector('#stu-list-table > tbody')
        let num = 20;
        //tbodyTag.innerHTML=`<tr><td>1</td><td>자바반</td><td>${num}</td><td>홍길동</td> </tr>`; //이거쓰면 tbody안에 있는 내용들 다 없애버림
        //새로 조회한 데이터로 안의 내용을 채워줌!!!!!!!
        tbodyTag.innerHTML=``;
        let str=``;
        // for(let i=0; i<data.length;i++){
        //     str=`<tr>
        //         <td>${i+1}</td>
        //         <td>${data[i].className}</td>
        //         <td>${data[i].stuNum}</td>
        //         <td>${data[i].stuName}</td>
        //         </tr>`;
        //     tbodyTag.insertAdjacentHTML('afterbegin',str);
        // }


        // for(const stu of data){
        //     str+= `<tr>
        //             <td></td>
        //             <td>${stu.className}</td>
        //             <td>${stu.stuNum}</td>
        //             <td>${stu.stuName}</td>
        //             </tr>`;
        // }

        data.forEach(function(stu, i){
            str+= `<tr>
                    <td ">${data.length - i}</td>
                    <td ">${stu.className}</td>
                    <td ">${stu.stuNum}</td>
                    <td>
                    <span onclick="stuSelect([[${stu.stuNum}]])"
                    id="name-span">${stu.stuName}</span></td>
                    </tr>`;
        });




        tbodyTag.insertAdjacentHTML('afterbegin',str);
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}

function stuSelect(stuNum){
    //stuNum값을 받아옴
    
    fetch('/stuSelect', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           //위에 선언한 데이터 가져감
           stuNum : stuNum
           //이걸 stuVO로 보내줄것
           
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

        //tbody 태그를 선택!!!! css 자식 구하는법 사용가능!!!!!!!
        const detail_div = document.querySelector('.stu-detail-div')
        detail_div.innerHTML=''
        let avgScore = 0.0;

        if((data.stuScoreVO.korScore+
            data.stuScoreVO.engScore+
            data.stuScoreVO.mathScore)/3.0 == 0){
                avgScore = '0.0';
            }else{
                avgScore = (data.stuScoreVO.korScore+
                    data.stuScoreVO.engScore+
                    data.stuScoreVO.mathScore)/3.0
                avgScore=avgScore.toFixed(1)
            }
        let str =`
                        <div>
                            <div>
                                <h1>학생 기본 정보</h1>
                            </div>
                            <div>
                                <table id="stu-list-table2">
                                    <thead>
                                        <tr>
                                            <td>학번</td>
                                            <td>소속반</td>
                                            <td>학생명</td>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <tr>
                                            <td class="stuNumTd" >${data.stuVO.stuNum}</td>
                                            <td>${data.stuVO.className}</td>
                                            <td>${data.stuVO.stuName}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div>
                            <div>
                                <h1>학생 점수 정보</h1>
                            </div>
                        
                            <div>
                                <table id="stu-list-table3"> 
                                    <thead>
                                        <tr>
                                            <td>국어점수</td>
                                            <td>영어점수</td>
                                            <td>수학점수</td>
                                            <td>평균</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td class="scoreTd">${data.stuScoreVO == null ? 0 : data.stuScoreVO.korScore}</td>
                                            <td class="scoreTd">${data.stuScoreVO == null ? 0 : data.stuScoreVO.engScore}</td>
                                            <td class="scoreTd">${data.stuScoreVO == null ? 0 : data.stuScoreVO.mathScore}</td>
                                            <td>${data.stuScoreVO == null ? '0.0' : avgScore}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div> <input type="button" id="btn" onclick ="goSave(${data.stuVO.stuNum})" value="점수입력"> </div>
                        </div>
        `;

        detail_div.insertAdjacentHTML('afterbegin',str);
        //새로 조회한 데이터로 안의 내용을 채워줌!!!!!!!
        console.log(data);
        console.log(data.stuVO);
        console.log(data.stuScoreVO);
        
        // tbodyTag1.innerHTML=`
        //                 <div>
        //                     <div>
        //                         <h1>학생 기본 정보</h1>
        //                     </div>
        //                     <div>
        //                         <table id="stu-list-table2">
        //                             <thead>
        //                                 <tr>
        //                                     <td>학번</td>
        //                                     <td>소속반</td>
        //                                     <td>학생명</td>
        //                                 </tr>
        //                             </thead>

        //                             <tbody>
        //                                 <tr>
        //                                     <td>${data.stuVO.stuNum}</td>
        //                                     <td>${data.stuVO.className}</td>
        //                                     <td>${data.stuVO.stuName}</td>
        //                                 </tr>
        //                             </tbody>
        //                         </table>
        //                     </div>
        //                 <div>`;
        // let str=``;
        
        // const tbodyTag2 = document.querySelector('.stu-detail-div')
        // tbodyTag2.innerHTML=``
        if(data.stuScoreVO == null){
            console.log("고객님 콘솔창 한 번 확인하시라니깐요?")
            // tbodyTag2.innerHTML=`<tr>
            //                         <td>0</td>
            //                         <td>0</td>
            //                         <td>0</td>
            //                         <td>0.0</td>
            //                     </tr>`
        }
        else{
            // tbodyTag2.innerHTML=`<tr>
            //                      <td>${data.stuScoreVO.korScore}</td>
            //                      <td>${data.stuScoreVO.engScore}</td>
            //                      <td>${data.stuScoreVO.mathScore}</td>
            //                      <td>${(data.stuScoreVO.korScore+
            //                         data.stuScoreVO.engScore+
            //                         data.stuScoreVO.mathScore)/3 }</td>
            //                  </tr>`;
        }
        
       




        //tbodyTag.insertAdjacentHTML('afterbegin',str);
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function goSave(stuNum){

    if(btn.value == '점수입력'){
        let btn =document.querySelector('#btn').value = '저장';
      const tds = document.querySelectorAll('.scoreTd');
        //textContent 내부 글자를 변환시켜줌!!!
        //innerHTML 는 html로 변환해서 보여줌!!
        // for(let i = 0; i< tds.length ; i++){
        //     tds[i].innerHTML = '<input type="text" name="korScore" id="korScore">';
        // }
        // for(const e of tds){
        //     e.innerHTML = '<input type="text" name="engScore" id="engScore">';
        // }
        tds.forEach(function(e,i){
            e.innerHTML='<input type="text" class="scoreInput">';
        });  
    }else if(btn.value == '저장'){
        //확인누르면 true 아니면 false
        const result = confirm("입력하신 정보로 점수를 등록할까요???????");
        if(result){
           insertScore(); 
        }
        
    }

    //아 다시 만들어야해!!!!!!!!!!!!!!!

    //넘어옴미까?
    // alert(stuNum);
    // const save_div = document.querySelector('#stu-list-table3')
    // const button_div = document.querySelector('.stu-detail-div>div:nth-child(2)>div:nth-child(3)')
    // save_div.innerHTML='';
    // button_div.innerHTML='';
    // let str =`
    //     <thead>
    //     <tr>
    //         <td>국어점수</td>
    //         <td>영어점수</td>
    //         <td>수학점수</td>
    //     </tr>
    //     </thead>
    //     <tbody>
    //     <tr>
    //         <td> <input type="text" class="btnwidth" name="korScore" id="korScore"> </td>
    //         <td> <input type="text" class="btnwidth" name="engScore" id="engScore"> </td>
    //         <td> <input type="text" class="btnwidth" name="mathScore" id="mathScore"> </td>
    //     </tr>
    //     </tbody>    
    // `

    // let str2 =`
    //     <div>
    //         <input type="button" onclick="save()" value ="저장!!!">
    //         <input type="hidden" value="${stuNum}" id="stuNum">
    //     </div>
    // `
    // save_div.insertAdjacentHTML('afterbegin',str);
    // button_div.insertAdjacentHTML('afterbegin',str2);   
}

function save(){

    let korScore = document.querySelector("#korScore").value;
    let engScore = document.querySelector("#engScore").value;
    let mathScore = document.querySelector("#mathScore").value;
    let stuNum = document.querySelector("#stuNum").value;
    console.log(stuNum)
    console.log(korScore)
    console.log(engScore)
    console.log(mathScore)
    alert("눌림?");

    fetch('/saveScore', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           stuNum : stuNum,
           korScore : korScore,
           engScore : engScore,
           mathScore : mathScore
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
        let avgScore = 0;
        
        console.log(data.korScore);
        console.log(data.engScore);
        console.log(data.mathScore);
        avgScore = (data.korScore + data.engScore + data.mathScore)/3;
        if(avgScore == 0){avgScore = avgScore.toFixed(1)}
        const save_div = document.querySelector('#stu-list-table3')
        const button_div = document.querySelector('.stu-detail-div>div:nth-child(2)>div:nth-child(3)')

        save_div.innerHTML = `
        <thead>
            <tr>
                <td>국어점수</td>
                <td>영어점수</td>
                <td>수학점수</td>
                <td>평균</td>
            </tr>
        </thead>

        <tbody>
            <tr>
                <td>${data.korScore}</td>
                <td>${data.engScore}</td>
                <td>${data.mathScore}</td>
                <td>${avgScore.toFixed(1)}</td>
        </tbody>

        `
        button_div.innerHTML=`
        <div>
            <input type="button" onclick="goSave(${data.stuNum})" id="btn" value ="저장!!!">
            <input type="hidden" value="${data.stuNum}" id="stuNum">
        </div>
        `


 

    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });

}

function insertScore(){
    const inputs = document.querySelectorAll('.scoreInput');
    const stuNum = document.querySelector('.stuNumTd').textContent;
    console.log(stuNum);

    fetch('/insertScore', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
           // 데이터명 : 데이터값
           'korScore' : inputs[0].value,
           'engScore' : inputs[1].value,
           'mathScore' : inputs[2].value,
           'stuNum' : stuNum
        //    글자 훔쳐올때는 textContent
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
        alert("점수가 등록되었슴미다")
        stuSelect(stuNum);
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}