package es.keensoft.repo.content.cleanup;

import java.io.File;
import java.util.Date;

import org.alfresco.repo.content.filestore.FileContentStore;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DeletedContentStoreCleaner {
	
    private static Log logger = LogFactory.getLog(DeletedContentStoreCleaner.class);
	
	private FileContentStore deletedContentStore;
    private Long minPurgeAgeMs;
	
	public void execute() {
		
		Long maxModifiedTime = System.currentTimeMillis() - minPurgeAgeMs;
		
		File rootFolder = new File(deletedContentStore.getRootLocation());
		
		logger.debug("[DeletedContentStoreCleaner] starts");
		logger.debug("[DeletedContentStoreCleaner] about to remove deleted files before " + new Date(maxModifiedTime) + " from Deleted Stored at " + rootFolder.getAbsolutePath());
		
		File[] files = rootFolder.listFiles();
		for (File file : files) {
			purgeAbandonedFiles(file, maxModifiedTime);
		}
		
		logger.debug("[DeletedContentStoreCleaner] ends");
		
    }
	
	private void purgeAbandonedFiles(File file, final Long maxModifiedTime) {
		if (file.isFile()) {
			if (file.lastModified() < maxModifiedTime) {
				logger.debug("[DeletedContentStoreCleaner] File " + file.getAbsolutePath() + " has been removed");
				file.delete();
			}
		} if (file.isDirectory()) {
			File[] ff = file.listFiles();
			// Empty folder
			if (ff.length == 0) {
				logger.debug("[DeletedContentStoreCleaner] Folder " + file.getAbsolutePath() + " has been removed");
			    file.delete();
			} else { 
				for (File f : ff) {
					purgeAbandonedFiles(f, maxModifiedTime);
				}
			}
		}
	}
	
    public void setMinPurgeAgeDays(int minPurgeAgeDays) {
        this.minPurgeAgeMs = ((long) minPurgeAgeDays) * 24L * 3600L * 1000L;
    }

	public void setDeletedContentStore(FileContentStore deletedContentStore) {
		this.deletedContentStore = deletedContentStore;
	}

}
