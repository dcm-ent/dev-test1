package com.dcm.petbox.controller;
 
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.dcm.petbox.model.FileDTO;
import com.dcm.petbox.service.FileService;
 
@Controller
public class FileController {
 
	
  @Autowired
  private FileService fileService;
	
	
  @RequestMapping(value = "/file.do", method = RequestMethod.GET)
  public ModelAndView fileForm() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("fileForm");
    return mv;
  }
   
  @RequestMapping(value = "/file.do", method = RequestMethod.POST)
  public String fileSubmit(FileDTO dto) throws IOException {
    MultipartFile uploadfile = dto.getUploadfile();
    if (uploadfile != null) {
      String fileName = uploadfile.getOriginalFilename();
      dto.setFileName(fileName);
      try {
        // 1. FileOutputStream ���
        // byte[] fileData = file.getBytes();
        // FileOutputStream output = new FileOutputStream("C:/images/" + fileName);
        // output.write(fileData);
         
        // 2. File ���
        File file = new File("C:/images/" + fileName);
        uploadfile.transferTo(file);
      } catch (IOException e) {
        e.printStackTrace();
      } // try - catch
    } // if
    // ������ ���̽� ó���� ���� ��ġ���� ó��
     
    fileService.saveFileInfo(dto);
    
    return "boardList"; // ����Ʈ ��û���� �������ϴµ� �ϴ� �����ϰ� ����
  }
  
  
  @RequestMapping("/getBoardList.do")
	public String getBoardList(Model model) {
//		 List<BoardVO> boardList = boardService.getBoardList();
//		 
//		// Model ���� ����
//		model.addAttribute("boardList",boardList);
		return "boardList"; // View �̸� ����
	}
}


