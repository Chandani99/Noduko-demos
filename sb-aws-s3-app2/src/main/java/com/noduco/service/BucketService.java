package com.noduco.service;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;

@Service
public class BucketService {
	
	@Autowired
	private AmazonS3 amazonS3;
	
	Logger logger = LogManager.getLogger(this.getClass().getName());
	
//	public Bucket getBucket(String bucket_name) {
//		List<Bucket> buckets = s3.get
//		for (Bucket b : buckets) {
//            if (b.getName().equals(bucket_name)) {
//                return b;
//            }
//        }
//        return null;
//	}
	public Bucket createBucket(String bucket_name) {
		
		if (amazonS3.doesBucketExistV2(bucket_name)) {
		    System.out.format("Bucket %s already exists.\n", bucket_name);
		    
		} else {
		    try {
		    	return amazonS3.createBucket(bucket_name);
		    } catch (AmazonS3Exception e) {
		        System.err.println(e.getErrorMessage());
		    }
		}
		return null;
	}
	
	public void deleteBucket(String bucketName) {
		amazonS3.deleteBucket(bucketName);
	}
	
	public void uploadFiletoBucket(MultipartFile multiPart,String bucketName) throws Exception {
		logger.info("Inside method upload");
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + multiPart.getOriginalFilename());
		multiPart.transferTo(convFile);
		try {
			amazonS3.putObject(bucketName, convFile.getName(), convFile);
			
		} catch (AmazonS3Exception s3Exception) {
			logger.error("Unable to upload file :" + s3Exception.getMessage());
		}
	}

	public void deletFile(String bucketName, String fileName) {
		amazonS3.deleteObject(bucketName, fileName);
	}

}
