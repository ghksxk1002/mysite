package com.douzone.mysite.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestbookRepository guestbookRepository;
	
	public List<GuestbookVo> findAll() {
		return guestbookRepository.selectAll();
	}
	
	// API
	public List<GuestbookVo> findAll(Long no) {
		return guestbookRepository.selectAll(no);
	}
	
	public boolean addGuestbook(GuestbookVo vo) {
		return guestbookRepository.insert(vo);
	}


	public boolean delete(GuestbookVo guestbookVo) {
		return guestbookRepository.delete(guestbookVo);
	}


	
}
