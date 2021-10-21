package com.douzone.mysite.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if ("modify".equals(actionName)) {
			action = new ModifyAction();
		} else if ("modifyform".equals(actionName)) {
			action = new ModifyformAction();
		} else if ("view".equals(actionName)) {
			action = new ViewAction();
		} else if ("write".equals(actionName)) {
			action = new WriteFormAction();
		} else if ("sumit".equals(actionName)) {
			action = new SumitAction();
		} else if ("del".equals(actionName)) {
			action = new DelAction();
		} else if ("reply".equals(actionName)) {
			action = new ReplyAction();
		} else if ("search".equals(actionName)) {
			action = new SearchAction();
		} else{
			action = new ListAction();
		}
		return action;
	}

}
