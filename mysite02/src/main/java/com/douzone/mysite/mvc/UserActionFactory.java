package com.douzone.mysite.mvc;

import com.douzone.mysite.mvc.user.JoinAction;
import com.douzone.mysite.mvc.user.JoinFormAction;
import com.douzone.mysite.mvc.user.JoinSuccessAction;
import com.douzone.mysite.mvc.user.LoginAction;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if ("joinform".equals(actionName)) {
			action = new JoinFormAction();
		} else if ("join".equals(actionName)) {
			action = new JoinAction();
		} else if ("joinsuccess".equals(actionName)) {
			action = new JoinSuccessAction();
		} else if ("loginform".equals(actionName)) {
			action = new LoginAction();
		} else {
			action = new MainAction();
		}

		return action;
	}

}