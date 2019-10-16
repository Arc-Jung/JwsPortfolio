package project.service.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

import project.domain.FileList;
import project.service.FileService;
import project.store.ContentStore;

@Service
public class FileServiceLogic{
	
	private AmazonS3 s3client;
    private String endpointUrl ="https://s3.ap-northeast-2.amazonaws.com";
    private String bucketName = "travelprojectlinkplus";
    private String accessKey = "AKIAIAEJXTW3DROAHQ5Q";
    private String secretKey = "89fJo4Ha9Pr159oN81RmNmGQSfHm";
	private static final String SAVE_PATH = "/upload";
	private static final String PREFIX_URL = "/upload/";
	
	
	public String restore(MultipartFile multipartFile) {
		String url = null;
		
		try {
			// 파일 정보
			String originFilename = multipartFile.getOriginalFilename();
			String extName
				= originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
			Long size = multipartFile.getSize();
			// 서버에서 저장 할 파일 이름
			String saveFileName = genSaveFileName(extName);
			
			System.out.println("originFilename : " + originFilename);
			System.out.println("extensionName : " + extName);
			System.out.println("size : " + size);
			System.out.println("saveFileName : " + saveFileName);
			
			writeFile(multipartFile, saveFileName);
			url = PREFIX_URL + saveFileName;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		return url;
	}
	
	  public Long restores3(MultipartFile multipartFile) {
			String url = null;
			Long size = multipartFile.getSize();
			
			return size;
				}
	
	
	// 현재 시간을 기준으로 파일 이름 생성
	private String genSaveFileName(String extName) {
		String fileName = "";
		
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;
		
		return fileName;
	}
	
	
	// 파일을 실제로 write 하는 메서드
	private boolean writeFile(MultipartFile multipartFile, String saveFileName)
								throws IOException{
		boolean result = false;

		byte[] data = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(data);
		fos.close();
		
		return result;
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////////
	
	private File convertMultiPartToFilERROR(MultipartFile file2) throws IOException {
	    File convFile = new File(file2.getOriginalFilename());
	    System.out.println("convertMultiPartToFile : 1 ");
	    FileOutputStream fos = new FileOutputStream(convFile);
	    System.out.println("convertMultiPartToFile : 2 ");
	    fos.write(file2.getBytes());
	    System.out.println("convertMultiPartToFile : 3 ");
	    fos.close();
	    System.out.println("convertMultiPartToFile : 4 ");
	    return convFile;
	}
	
	public File convertMultiPartToFile2(@RequestParam("file1")MultipartFile file2) throws IOException 
	{
	    File convFile = new File(file2.getOriginalFilename());
	    file2.transferTo(convFile);
	    return convFile;
	}

	private String generateFileName(File fi) {
	    return "test";// new Date().getTime(); + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}

	private void uploadFileTos3bucket(String fileName, File file) {
	    s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
	            .withCannedAcl(CannedAccessControlList.PublicRead));
	}
	
	public String uploadFiletoS3(File newfile) {

	    String fileUrl = "";
	    System.out.println("fileurl : NULL");
	    try {
	        //File newfile = convertMultiPartToFile2(file2);
	        System.out.println("Convert success");
	        String fileName = generateFileName(newfile);
	        System.out.println("Filename generate");
	        fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
	        uploadFileTos3bucket(fileName, newfile);
	        System.out.println("upload success");
	        newfile.delete();
	    } catch (Exception e) {
	       e.printStackTrace();
	       System.out.println("upload fail");
	    }
	    return fileUrl;
	}

	public String deleteFileFromS3Bucket(String fileUrl) {
	    String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
	    s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
	    return "Successfully deleted";
		}
	
	
}
