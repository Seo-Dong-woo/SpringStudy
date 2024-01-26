package com.sist.main;

public class MainClass {
	public static void main(String[] args) {
		String path="C:\\springDev\\springStudy\\SpringContainerProject\\src\\main\\java\\com\\sist\\main\\app.xml";
		ApplicationContext app=new ApplicationContext(path);
		
		Board board=(Board)app.getBean("board");
		board.board_list();
	}
	
}
