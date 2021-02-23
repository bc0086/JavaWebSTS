package thumbnail;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {
	private static final String CURR_IMAGE_REPO_PATH = "c:\\03Workspace\\file_repo";
	
	@RequestMapping(value="/form")
	public String form() {
		return "uploadForm";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public ModelAndView upload(MultipartHttpServletRequest multipartRequest,
								HttpServletResponse response) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map map = new HashMap(); // 매개변수와 파일정보를 저장할 Map을 생성함
		Enumeration enu = multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			map.put(name, value);
		}
		
		// 파일을 업로드 한 후 반환된 파일 이름이 저장된 fileList를 다시 Map에 저장함
		List fileList = fileProcess(multipartRequest);
		map.put("fileList", fileList);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map", map);
		mav.setViewName("result");
		return mav;
	}

	private List<String> fileProcess(MultipartHttpServletRequest multipartRequest) throws Exception {
		List<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
			// 첨부된 파일이름을 가져옴
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
				// 파일이름에 대한 MultipartFile 객체를 가져옴
			String originalFileName = mFile.getOriginalFilename();
			fileList.add(originalFileName); // 파일 이름을 하나씩 fileList에 저장함
			
			File file = new File(CURR_IMAGE_REPO_PATH + "\\" + fileName);
			if(mFile.getSize() != 0) { // 첨부된 파일이 있는지 체크함
				if(! file.exists()) { // 경로에 파일이 없으면
					if(file.getParentFile().mkdirs()) { // 그 경로에 해당하는 디렉터리 만든후
						file.createNewFile(); // 파일을 생성함
					}
				}
				mFile.transferTo(new File(CURR_IMAGE_REPO_PATH + "\\" + originalFileName));
					// 임시로 저장된 multipartFile을 실제파일로 전송함
			}
		}
		return fileList;
			// 첨부한 파일 이름이 저장된 fileList를 반환함
	}
}
