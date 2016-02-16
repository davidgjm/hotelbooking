/**
 * 
 */
package com.jinjiang.hotel;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;

import com.jinjiang.hotel.config.AppConfig;

/**
 * @author gaojianm
 *
 */
public class ZKWebUtil {
	private static Logger log=LoggerFactory.getLogger(ZKWebUtil.class);
	private static AppConfig config=AppConfig.getInstance();
	private static String repoPath = config.getString("repo.images.path");
	private static final String BACKUP_PATH = config.getString("repo.images.backup.path");
	
	public static boolean deleteImage(String imagePath) {
		if(imagePath==null||imagePath.trim().isEmpty()) return false;
		if (log.isInfoEnabled()) {
			log.info("Attempting to delete image: {}", imagePath);
		}
		String imageName=imagePath.substring(imagePath.lastIndexOf("/")+1);
		Path imgRealPath=getImageRealPath(imageName);
		boolean isDeleted=false;
		try {
			isDeleted=Files.deleteIfExists(imgRealPath);
			File backupImage=new File(BACKUP_PATH, imageName);
			if (log.isInfoEnabled()) {
				log.info("Deleting backup image");
			}
			Files.deleteIfExists(backupImage.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Error when deleting image.", e);
		}
		if (log.isInfoEnabled()) {
			log.info("Is image deleted? {}", isDeleted);
		}
		return isDeleted;
	}
	private static Path getImageRealPath(String imagePath) {
		ServletContext context = Executions.getCurrent().getDesktop().getWebApp().getServletContext();
		String realRepoPath=context.getRealPath(repoPath);
		Path image=FileSystems.getDefault().getPath(realRepoPath, imagePath);
		if (log.isDebugEnabled()) {
			log.debug("real repo path: {}", realRepoPath);
			log.debug("Image real path: {}", image);
		}
		return image;
	}
	public static String uploadImage(Media media) throws IOException {
		String imageName=String.format("%s.%s",
				UUID.randomUUID().toString(),
				FilenameUtils.getExtension(media.getName()));
		if (log.isDebugEnabled()) {
			log.debug("imageName: {}", imageName);
		}
		Path imgPath=getImageRealPath(imageName);
		//save to web content
		Files.copy(media.getStreamData(), imgPath, StandardCopyOption.REPLACE_EXISTING);
		
		/*
		 * save uploaded images to local disk according to config.xml
		 */
		if (config.getBoolean("repo.images.backup.isEnabled")) {
			Path backupFile=new File(BACKUP_PATH, imageName).toPath();
			if (log.isDebugEnabled()) {
				log.debug("Saving image to backup path: [{}]", backupFile.toAbsolutePath());
			}
			Files.copy(media.getStreamData(), backupFile, StandardCopyOption.REPLACE_EXISTING);
		}
		
		String imagePath=repoPath+imageName;
		if (log.isDebugEnabled()) {
			log.debug("Image path to be saved: {}", imagePath);
		}
		return imagePath;
	}
}
