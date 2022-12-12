package co.eficacia.com.rewardsapp.utils;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public abstract class FileHandler {

    public static final String BUCKET_NAME = "rewards-app-18b40.appspot.com";
    public static final String DOWNLOAD_URL =
            "https://firebasestorage.googleapis.com/v0/b/rewards-app-18b40.appspot.com/o/%s?alt=media";
    public static final String PRIVATE_KEY = "src/main/resources/rewards-app-18b40-firebase-adminsdk-40f1q-d381866121.json";

    public static Object upload(MultipartFile multipartFile, String folder) {
        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            assert fileName != null;
            fileName = UUID.randomUUID().toString().concat(getExtension(fileName));  // to generated random string values for file name.
            File file = convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            String TEMP_URL = uploadFile(file, fileName, folder);                            // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
//            return "Successfully Uploaded! " + TEMP_URL;                                  // Your customized response
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "Unsuccessfully Uploaded!";
        }
    }

    private static String uploadFile(File file, String fileName, String folder) throws IOException {
        BlobId blobId = BlobId.of(BUCKET_NAME, folder+fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(PRIVATE_KEY));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private static File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }

    private static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public static Object download(String fileName) throws IOException {
        String destFileName = UUID.randomUUID().toString().concat(getExtension(fileName));     // to set random string for destination file name
        String destFilePath = "Z:\\New folder\\" + destFileName;                                    // to set destination file path
        ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(PRIVATE_KEY));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of(BUCKET_NAME, fileName));
        blob.downloadTo(Paths.get(destFilePath));
        return "Successfully Downloaded!";
    }
}
