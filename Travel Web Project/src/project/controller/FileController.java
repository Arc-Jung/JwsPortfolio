package project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;

import project.domain.FileList;
import project.domain.User;
import project.service.FileService;
import project.service.logic.FileServiceLogic;


@RequestMapping(value = "file")
@Controller
public class FileController {
	

	
	
	@Autowired
	FileServiceLogic fileServiceLogic;
	
	// 서버 업로드
	@RequestMapping(value = "/upload.do")
	//, method = RequestMethod.GET
	public String upload(Model model, @RequestParam("file1")MultipartFile file) {

		String url = fileServiceLogic.restore(file);
		model.addAttribute("url",url);
		
		return "/views/project/index.do";
	}
    
    @RequestMapping(value = "/uploads3.do")
    public String uploadFile(@RequestParam("file1")MultipartFile file2) throws Exception, IOException {
    	System.out.println("mapping success");
    	File convFile = new File(file2.getOriginalFilename());
	    System.out.println("convertMultiPartToFile : 1 ");
	    FileOutputStream fos = new FileOutputStream(convFile);
	    System.out.println("convertMultiPartToFile : 2 ");
	    fos.write(file2.getBytes());
	    System.out.println("convertMultiPartToFile : 3 ");
	    fos.close();
	    System.out.println("convertMultiPartToFile : 4 ");
    	
        return this.fileServiceLogic.uploadFiletoS3(convFile);
    }

    @RequestMapping(value = "/deletes3.do")
    public String deleteFile(Model model, @RequestParam("url") String fileUrl) {
        return this.fileServiceLogic.deleteFileFromS3Bucket(fileUrl);
    }

	  


}
