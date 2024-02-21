//search 버튼 클릭 시 주소록 검색 팝업창 띄우기
function searchAddress(){
    new daum.Postcode({
    oncomplete: function(data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
      // 예제를 참고하여 다양한 활용법을 확인해 보세요.

      //alert(data.zonecode);
      //let roadAddr = data.roadAddress; // 도로명 주소 변수
      document.querySelector('#postCode').value = data.zonecode;
      document.querySelector('#roadAddress').value = data.roadAddress;
    }
  }).open();
}

function goJoin(){
  //버튼 클릭시 from태그를 submit 시킴
  //0.submit 전에 유효성 검사를 해야됨!!!!!!!!!!!!!!!!!!!!!!!!!
  //일단 ID를 입력했는지 확인
  const idInput = document.querySelector('#memberId');
  const pwInput1 = document.querySelectorAll('input[type="password"]');
  //const pwInput2 = document.querySelector('#memberPw2');

  //문자 + 숫자 + 특수 문자가 포함된 4~12자리의 글자
  const regExp= /^.*(?=^.{4,12}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
  //test 함수의 매개변수로 들어온 문자가 정규식 조건에 부합하면 return true 나옴!!!
  const expResult = regExp.test(idInput[0]);

  // if(!expResult){
  //   alert('비밀번호 형식에 일치하지 않습니다')
  //   return ;
  // }
  
  if(idInput.value == ''){
    alert('아이디는 필수 입력사항 입니다.')
    return ;
  }
  if(idInput.value.length > 20){
    alert('아이디는 20자를 초과할 수 없습니다. ')
    return;
  }
  
  if(pwInput1[0].value != pwInput1[1].value){
    alert('입력하는 두 비밀번호가 다릅니다.\n비밀번호를 다시 입력해주세요.')
    return;
  }

  // 1 submit 시킬 form 태그를 선택후 submit함수 실행

  document.querySelector('#join-form').submit();
}

//모달 창이 닫히면 다 리셋시켜버리기
const joinModal = document.querySelector('#join-modal');
//선택한 태그에 이벤트 달아주기
// joinModal.addEventListener('hidden.bs.modal', function(){

// })
//hidden.bs.model하면 모달창 닫힐때 생길 이벤트 설정가능!!
joinModal.addEventListener('hidden.bs.modal',() =>{
  document.querySelector('#join-form').reset();
});

/////////////////로그인////////////////////////////
function goLoginForm(){
  alert('이동하자')

  location.href='/member/login'
}
/////////////////////로그아웃/////////////
function goLogout(){
  if(confirm('로그아웃 하시겠습니까?')){
    location.href='/member/logout'
  }
}
///장바구니 목록으로 가기/////////
function goCartList(){
  location.href='/cart/list';
}

function goMyInfo(){
  location.href="/buy/history";
}
