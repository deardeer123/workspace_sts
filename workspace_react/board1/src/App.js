import logo from './logo.svg';
import './App.css';
import { useState } from 'react';

function App() {

  let board_list = [
    {
      'boardNo': 1,
      'title': '1번 글',
      'content': '1번글 내용',
      'writer': '김자바',
      'regDate': '2024-02-01'
    },
    {
      'boardNo': 2,
      'title': '2번 글',
      'content': '2번글 내용',
      'writer': '홍길동',
      'regDate': '2024-02-02'
    },
    {
      'boardNo': 3,
      'title': '집에가고싶다',
      'content': '제목이 곧 내용',
      'writer': '박자바',
      'regDate': '2024-02-03'
    },
    {
      'boardNo': 4,
      'title': '진자',
      'content': '진자 집에가고싶다',
      'writer': '김진짜',
      'regDate': '2024-02-07'
    },
    {
      'boardNo': 5,
      'title': '리랙트',
      'content': 'react',
      'writer': '김리액트',
      'regDate': '2024-02-11'
    }
  ];

  let [boardList, setBoardList] = useState(board_list)
  let [board, setBoard] = useState(boardList[0]);
  let [isLook, setIsLook] = useState(false)
  console.log(selectBoard)
  console.log(board)



  return (
    <div className="App">
      <div>
        <div className='header-div'>
          {/* 헤더 */}
          <h1>게시글</h1>
        </div>

        <div className='content-div'>
          {/* 게시물 */}
          <table className='table-1'>
            <colgroup>
              <col width='15%'></col>
              <col width='*'></col>
              <col width='20%'></col>
              <col width='20%'></col>
            </colgroup>

            <thead className='thead-1'>
              <tr>
                <td>글번호</td>
                <td>제목</td>
                <td>작성자</td>
                <td>작성일</td>
              </tr>
            </thead>

            <tbody>
              {
                boardList.map((board, index) => {
                  return (
                    <>
                      <LookBoard board={board} setIsLook={setIsLook} setBoard ={setBoard}/>

                    </>
                  );
                })
              }
            </tbody>

          </table>
        </div>

        <div>
          {/* 누르면 보이는거 */}
          {
            isLook ? <DetailBoard board={board}/> : ''

          }

        </div>

      </div>
    </div>
  );
}
function DetailBoard(props) {
  return (
    <>
      <div className='detail'>
        <table className='table-2'>
          <colgroup>
            <col width='30%'></col>
            <col width='*'></col>
          </colgroup>
          <tr>
            <td>글번호</td>
            <td>{props.board.boardNo}</td>
          </tr>
          <tr>
            <td>제목</td>
            <td>{props.board.title}</td>
          </tr>
          <tr>
            <td>내용</td>
            <td>{props.board.content}</td>
          </tr>
          <tr>
            <td>작성자</td>
            <td>{props.board.writer}</td>
          </tr>
          <tr>
            <td>작성일</td>
            <td>{props.board.regDate}</td>
          </tr>
        </table>

        {/* {console.log(props.index)} */}
      </div>
    </>

  );
}

function LookBoard(props) {
  // 'boardNo' : 1 ,
  //     'title' : '1번 글',
  //     'content' : '1번글 내용',
  //     'writer' : '김자바',
  //     'regDate' : '2024-02-01'
  
  return (

    <>

      <tr onClick={
        () => {
          props.setIsLook(true);
          props.setBoard(props.board);
        
        }
      }>
        <td>
          {/* 글번호 */}
          {props.board.boardNo}
        </td>
        <td>
          {/* 제목 */}
          {props.board.title}
        </td>
        <td>
          {/* 작성자*/}
          {props.board.writer}
        </td>
        <td>
          {/* 작성일 */}
          {props.board.regDate}
        </td>
      </tr>

    </>

  );
}

export default App;
