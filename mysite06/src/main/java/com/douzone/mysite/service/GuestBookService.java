package com.douzone.mysite.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestbookRepository guestbookRepository;
	
	public void index(Model model) {
		List<GuestbookVo> list = guestbookRepository.findAll();
		model.addAttribute("list", list);
	}
	
	
	public boolean addGuestbook(GuestbookVo vo) {
		return guestbookRepository.insert(vo);
	}


	public void delete(GuestbookVo guestbookVo) {
		guestbookRepository.delete(guestbookVo);
	}
	
}
