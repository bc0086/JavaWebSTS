package thumbnail;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileDownloadController {
	private static String CURR_IMAGE_REPO_PATH = "c:\\03Workspace\\file_repo";
	
	@RequestMapping("/download")
	// 다운로드할 이미지 파일이름을 전달
	protected void download(@RequestParam("imageFileName") String imageFileName,
							HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		File image = new File(filePath); 
		
		// 확장자를 제외한 원본 이미지 파일의 이름을 가져옴
		int lastIndex = imageFileName.lastIndexOf(".");
		String fileName = imageFileName.substring(0, lastIndex);
		
		File thumbnail = new File(CURR_IMAGE_REPO_PATH + "\\" 
									+ "thumbnail" + "\\" + fileName+".png");
			// 원본 이미지 파일이름과 같은 이름의 썸네일 파일에 대한 File객체를 생성함
		
		if(image.exists()) {
			Thumbnails.of(image).size(50,50).outputFormat("png").toOutputStream(out);
				// 원본 이미지에 대한 썸네일 이미지를 생성한 후 OutputStream객체에 할당함
		} else {
			return;
		}
		
		byte[] buffer = new byte[1024*8]; // 썸네일 이미지를 OutputStream객체를 이용해 브라우저로 전송
		out.write(buffer);
		out.close();
	}
}
