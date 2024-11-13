//package com.shoponline.chinaorder.config.minioConfig;
//
//import io.minio.MinioClient;
//import io.minio.Result;
//import io.minio.errors.MinioException;
//import io.minio.http.Method;
//import io.minio.messages.Item;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.xmlpull.v1.XmlPullParserException;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class MinioService {
//
//    private final MinioClient minioClient;
//    private final String bucketName;
//
//    public MinioService(MinioClient minioClient, @Value("${minio.bucket-name}") String bucketName) {
//        this.minioClient = minioClient;
//        this.bucketName = bucketName;
//
//        try {
//            // Kiểm tra xem bucket đã tồn tại chưa, nếu chưa thì tạo mới
//            if (!minioClient.bucketExists(bucketName)) {
//                minioClient.makeBucket(bucketName);
//            }
//        }catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    public void uploadFile(String fullPath, InputStream inputStream, String contentType) throws Exception {
//        try {
//            // Upload file vào đường dẫn
//            minioClient.putObject(bucketName, fullPath, inputStream, (long) inputStream.available(), contentType);
//        } catch (MinioException e) {
//            throw new RuntimeException("Error uploading file to MinIO: " + e.getMessage());
//        }
//    }
//
//
//    public InputStream downloadFile(String fileName) throws Exception {
//        try {
//            return minioClient.getObject(bucketName, fileName);
//        } catch (MinioException e) {
//            throw new RuntimeException("Error downloading file from MinIO: " + e.getMessage());
//        }
//    }
//    public String getFileUrl(String fileName) {
//        try {
//            // Tạo URL tạm thời (presigned URL) với thời gian hết hạn là 10 năm (hoặc thời gian bạn muốn)
//            return minioClient.presignedGetObject(bucketName, fileName, 60 * 60 * 24 * 365 * 10);
//        } catch (Exception e) {
//            throw new RuntimeException("Error generating file URL: " + e.getMessage());
//        }
//    }
//    public List<String> listFileVariants(String filePath) {
//        List<String> fileUrls = new ArrayList<>();
//        try {
//            Iterable<Result<Item>> objects = minioClient.listObjects(bucketName, filePath);
//            for (Result<Item> result : objects) {
//                Item item = result.get();
//                String fileName = item.objectName();
//                String fileUrl = minioClient.presignedGetObject(bucketName, fileName, 24 * 60 * 60); //Link ảnh sống được 24 giờ
//                fileUrls.add(fileUrl);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Error listing files: " + e.getMessage());
//        }
//        return fileUrls;
//    }
//
//    public List<String> listImagePaths() {
//        List<String> imagePaths = new ArrayList<>();
//
//        try {
//            // Duyệt qua các đối tượng trong bucket với tiền tố "products/"
//            Iterable<Result<Item>> results = minioClient.listObjects(
//                    bucketName,
//                    "products/",
//                    true // Liệt kê đệ quy, bao gồm cả thư mục con
//            );
//
//            // Lấy các đường dẫn hình ảnh
//            for (Result<Item> result : results) {
//                Item item = result.get();
//                String objectName = item.objectName();
//                imagePaths.add(objectName);
//                System.out.println("Found image path: " + objectName);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return imagePaths;
//    }
//
//}
package com.shoponline.chinaorder.config.minioConfig;

import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class MinioService {

    private final MinioClient minioClient;
    private final String bucketName;

    public MinioService(MinioClient minioClient, @Value("${minio.bucket-name}") String bucketName) {
        this.minioClient = minioClient;
        this.bucketName = bucketName;

        try {
            // Check if the bucket exists, if not create it
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            System.out.println("Bucket creation error: " + e.getMessage());
        }
    }

    @PostConstruct
    public void initialize() {
        setPublicBucketPolicy(bucketName);
    }
    public void setPublicBucketPolicy(String bucketName) {
        String policy = "{\n" +
                "  \"Version\": \"2012-10-17\",\n" +
                "  \"Statement\": [\n" +
                "    {\n" +
                "      \"Effect\": \"Allow\",\n" +
                "      \"Principal\": \"*\",\n" +
                "      \"Action\": [\"s3:GetObject\"],\n" +
                "      \"Resource\": [\"arn:aws:s3:::" + bucketName + "/*\"]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        try {
            minioClient.setBucketPolicy(
                    SetBucketPolicyArgs.builder()
                            .bucket(bucketName)
                            .config(policy)
                            .build()
            );
            System.out.println("Bucket policy set to public");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error setting bucket policy: " + e.getMessage());
        }
    }


    public void uploadFile(String fullPath, InputStream inputStream, String contentType) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fullPath)
                            .stream(inputStream, inputStream.available(), -1)
                            .contentType(contentType)
                            .build());
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file to MinIO: " + e.getMessage());
        }
    }

    public InputStream downloadFile(String fileName) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build());
        } catch (Exception e) {
            throw new RuntimeException("Error downloading file from MinIO: " + e.getMessage());
        }
    }

    public String getFileUrl(String fileName) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .method(Method.GET)
                            .expiry(60 * 60 * 24 * 365 * 10)
                            .build());
        } catch (Exception e) {
            throw new RuntimeException("Error generating file URL: " + e.getMessage());
        }
    }

    public void deleteFile(String objectPath) {
        try {
            // Kiểm tra sự tồn tại của file
            minioClient.statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectPath)
                            .build()
            );

            // Nếu không có ngoại lệ xảy ra, file tồn tại, tiếp tục xóa
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectPath)
                            .build()
            );
            System.out.println("File deleted successfully from MinIO: " + objectPath);

        } catch (MinioException e) {
            if (e.getMessage().contains("Object does not exist")) {
                System.out.println("File does not exist in MinIO: " + objectPath);
            } else {
                throw new RuntimeException("Error deleting file from MinIO: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unexpected error deleting file from MinIO: " + e.getMessage());
        }
    }


    public List<String> listFileVariants(String filePath) {
        List<String> fileUrls = new ArrayList<>();
        try {
            Iterable<Result<Item>> objects = minioClient.listObjects(
                    ListObjectsArgs.builder()
                            .bucket(bucketName)
                            .prefix(filePath)
                            .build());
            for (Result<Item> result : objects) {
                Item item = result.get();
                String fileName = item.objectName();
                String fileUrl = minioClient.getPresignedObjectUrl(
                        GetPresignedObjectUrlArgs.builder()
                                .bucket(bucketName)
                                .object(fileName)
                                .method(Method.GET)
                                .expiry(24 * 60 * 60)
                                .build());
                fileUrls.add(fileUrl);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error listing files: " + e.getMessage());
        }
        return fileUrls;
    }

    public List<String> listImagePaths() {
        List<String> imagePaths = new ArrayList<>();
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder()
                            .bucket(bucketName)
                            .prefix("products/")
                            .recursive(true)
                            .build());
            for (Result<Item> result : results) {
                Item item = result.get();
                imagePaths.add(item.objectName());
                System.out.println("Found image path: " + item.objectName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagePaths;
    }
}
