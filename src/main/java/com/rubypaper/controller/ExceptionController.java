package com.rubypaper.controller;

import com.rubypaper.exception.BoardNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
public class ExceptionController {

	//BoardNotFoundException 은 BoardException.java 를 상속한다.
	//GlobalException.java 에서 BoardException 발생시 매핑되는 html 을 세팅
	//boardError.html 화면이 보여짐
	@RequestMapping("/boardError")
	public String boardError() {
		throw new BoardNotFoundException("검색된 게시글이 없습니다.");
	}

	//iillegalArgumentError 는 GlobalException 에서 정의하지 않음
	//디폴트 에러 handleException 에 매핑되어 globalError.html 화면이 보여짐
	@RequestMapping("/illegalArgumentError")
	public String ellegalArgumentError() {
		throw new IllegalArgumentException("부적절한 인자가 전달되었습니다.");
	}

	//==========================================================
	//하단 핸들러를 GlobalExceptionHandler 나 컨트롤러에 정의하지 않으면 디폴트 익셉션으로 흘러감
	@RequestMapping("/sqlError")
	public String sqlError() throws SQLException {
		throw new SQLException("SQL 구문에 오류가 있습니다.");
	}
	
	@ExceptionHandler(SQLException.class)
	public String numberFormatError(SQLException exception, Model model) {
		model.addAttribute("exception", exception);
		return "/errors/sqlError";
	}

}