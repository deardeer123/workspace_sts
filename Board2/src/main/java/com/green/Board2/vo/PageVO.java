package com.green.Board2.vo;

public class PageVO {
    private int nowPage; //현재 선택된 페이지 번호
    private int totalDataCnt; //전체 개시물 갯수
    private int displayDataCnt; //한 페이지에 보여지는 데이터 수 무조건 올림해야함!!!1
    private int displayPageCnt; //한 페이지에 보여지는 페이지 수
    private int beginPage; //화면에 보이는 첫번째 페이지 번호 다음 버튼누르면 맨처음 가는페이지
    private int endPage; //화면에 보이는 마지막 페이지 번호
    private boolean prev; //이전 버튼의 유무..
    private boolean next;//다음 버튼의 유무/\/\/\/\/\/\/\
    int totalPageCnt;
    public PageVO() {
        nowPage = 1;
        displayDataCnt = 20;
        displayPageCnt = 5;
    }

    public void setPageInfo(){
        //화면에 보이는 마지막 페이지 번호 세팅

        //displayPageCnt, nowPage , 올림(현재페이지번호/한페이지수) * 한 페이지에 보이는 페이지수
        endPage = (int)Math.ceil(nowPage/(double)displayPageCnt)* displayPageCnt;
        //화면에 보이는 첫번째 페이지 번호 세팅
        beginPage= endPage - displayPageCnt +1;
        //전체 페이지 수
        totalPageCnt = (int) Math.ceil(totalDataCnt /(double) displayDataCnt);
        //next 버튼의 유무
        if(endPage<totalPageCnt){
            next = true;
        }else{
            next = false;
            endPage = totalPageCnt;
        }
        prev = beginPage == 1 ? false : true;

    }

    public void setNowPage(int nowPage){
        this.nowPage = nowPage;
    }
    public int getNowPage(){
        return nowPage;
    }
    public int getEndPage(){
        return endPage ;
    }
    public int getBeginPage(){
        return beginPage;
    }
    public int getDisplayDataCnt(){
        return displayDataCnt;
    }

    public void setTotalDataCnt(int a){
        this.totalDataCnt = a;
    }
    public int getTotalPageCnt(){
        return totalPageCnt;
    }
    public boolean getPrev(){
        return prev;
    }
    public boolean getNext(){
        return next;
    }

}
