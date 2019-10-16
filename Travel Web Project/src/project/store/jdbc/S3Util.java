package project.store.jdbc;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;

public class S3Util {

	private String accesskey = "AKIAIAEJXTW3DROAHQ5Q";
	private String secretkey = "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";
	private AmazonS3 conn;

	public S3Util() {
		AWSCredentials credentials = new BasicAWSCredentials(accesskey, secretkey);
		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setProtocol(Protocol.HTTP);
		this.conn = new AmazonS3Client(credentials, clientConfig);
		conn.setEndpoint("s3.ap-northeast-2.amazonaws.com");
		System.out.println("conect");
	}

	public List<Bucket> getBucketList() {
		return conn.listBuckets();
	}

	// 버킷을 생성하는 메서드이다.
	public Bucket createBucket(String bucketName) {
		return conn.createBucket(bucketName);
	}

	// 폴더 생성 (폴더는 파일명 뒤에 "/"를 붙여야한다.)
	public void createFolder(String bucketName, String folderName) {
		conn.putObject(bucketName, folderName + "/", new ByteArrayInputStream(new byte[0]), new ObjectMetadata());
	}

	// 파일 업로드
	public void fileUpload(String bucketName, String fileName, byte[] fileData) throws FileNotFoundException {

		String filePath = (fileName).replace(File.separatorChar, '/'); 
																		
		ObjectMetadata metaData = new ObjectMetadata();

		metaData.setContentLength(fileData.length);
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData); // 파일 넣음

		conn.putObject(bucketName, filePath, byteArrayInputStream, metaData);

	}

	// 파일 삭제
	public void fileDelete(String bucketName, String fileName) {
		String imgName = (fileName).replace(File.separatorChar, '/');
		conn.deleteObject(bucketName, imgName);
		System.out.println("삭제성공");
	}

	// 파일 URL
	public String getFileURL(String bucketName, String fileName) {
		System.out.println("넘어오는 파일명 : " + fileName);
		String imgName = (fileName).replace(File.separatorChar, '/');
		return conn.generatePresignedUrl(new GeneratePresignedUrlRequest(bucketName, imgName)).toString();
	}
	
	private static final Logger logger = LoggerFactory.getLogger(S3Util.class);


	public static String uploadFile(String uploadPath, String originalName, byte[] byteData) throws Exception {
		S3Util s3 = new S3Util();
		String bucketName = "travelprojectlinkplus";
	
		UUID uid = UUID.randomUUID();

		String savedName = "/"+uid.toString() + "_" + originalName;

		String uploadedFileName = null;

		uploadedFileName = (uploadPath + savedName).replace(File.separatorChar, '/');
		System.out.println(uploadPath+savedName);
		s3.fileUpload(bucketName, uploadPath+savedName, byteData);


		logger.info(uploadedFileName);

		return uploadedFileName;

	}
	public static void deleteFile(String fileName) throws Exception {
		S3Util s3 = new S3Util();
		String bucketName = "travelprojectlinkplus";
		s3.fileDelete(bucketName, fileName);
	}
}
