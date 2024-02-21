package com.green.basicBoard.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@ToString
public class BasicBoardVO {
    private int boardNum;
    private String boardTitle;
    private String boardContent;
    private String writer;
    private String createDate;
    private int readCnt;

}
