package com.douzone.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.exception.GalleryServiceException;
import com.douzone.mysite.repository.SiteRepository;
import com.douzone.mysite.vo.GalleryVo;
import com.douzone.mysite.vo.SiteVo;

@Service
public class SiteService {

	private static String SAVE_PATH = "/upload-mysite";
	private static String URL_BASE = "/gallery/images";
	
	@Autowired
	SiteRepository siteRepository;
	
	public SiteVo getSite() {
		return siteRepository.find();
	}

	public String ImageUploader(SiteVo site, MultipartFile multipartFile) {
		
		
		// 업로드된 이미지 파일을 저장할 새로운 디렉도리 파일 생성
		File uploadDirectory = new File(SAVE_PATH);

		// 디렉토리 파일이 존제하지 않으면 다시 생성
		if (!uploadDirectory.exists()) {
			uploadDirectory.mkdir();
		}

		// 만약 파일안에 이미지가 없다면 비어있다면 익셉션 드로우
		if (multipartFile.isEmpty()) {
			throw new GalleryServiceException("file upload error : image empty");
		}

		// 파일의 이름이 동일한 요청이 들어올수도 있기 때문에 파일 이름을 확장자와 이름으로 나누어
		// 이름을 변경하는 작업을 해주어야 한다
		try {
			String originFilename = multipartFile.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf('.') + 1);
			String saveFileName = generateSavaFileName(extName);

			// OutputStream 으로 넘어오는 이미지 파일 내용을 바이트 단위로 읽어서 저장
			byte[] data = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(data);
			os.close();

			SiteVo vo = new SiteVo();
			//vo.setUrl(URL_BASE + "/" + saveFileName);

			//siteRepository.insert(vo);
		} catch (Exception e) {
			throw new GalleryServiceException("file upload error:" + e);
		}
		return null;
	}
	
	private String generateSavaFileName(String extName) {
		// 넘겨줄 파일이름의 초기값을 지정
		String fileName = "";

		Calendar calendar = Calendar.getInstance();

		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);
		return fileName;
	}
}
