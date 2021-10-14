package com.douzone.mysite.mvc.board;


import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {
	
	

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if ("modify".equals(actionName)) {
			action = new modifyAction();
		} else if ("modifyform".equals(actionName)) {
			action = new modifyformAction();
		} else if ("view".equals(actionName)) {
			action = new viewAction();
		} else if("write".equals(actionName)){
			action = new writeFormAction();
		} else if("sumit".equals(actionName)){
			action = new sumitAction();
		} else{
			action = new listAction();
		}
		return action;
	}

}
