package com.douzone.mysite.mvc.guestbook;


import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		
		Action action = null;
		
		if ("deleteform".equals(action)) {
			
			action = new DeleteFormAction();
			
		} else if ("add".equals(action)) {
			
			action = new AddAction();
		} else if("delete".equals(action)){
			
			action = new DeletAction();
			
		} else {
			
			action = new IndexAction();
		}
		return action;
	}

}
