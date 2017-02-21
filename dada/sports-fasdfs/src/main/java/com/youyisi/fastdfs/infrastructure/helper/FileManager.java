package com.youyisi.fastdfs.infrastructure.helper;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.youyisi.fastdfs.domain.FastDFSFile;
import com.youyisi.fastdfs.domain.FileManagerConfig;

public class FileManager implements FileManagerConfig {
  
  private static final long serialVersionUID = 1L;

  private  Logger logger  = LoggerFactory.getLogger(FileManager.class);
  
  private  TrackerClient  trackerClient;
  private  TrackerServer  trackerServer;
  private  StorageServer  storageServer;
  private  StorageClient  storageClient;

  public FileManager() { // Initialize Fast DFS Client configurations
    
    try {
      /*String classPath = new File(FileManager.class.getResource("/").getFile()).getCanonicalPath();
      
      String fdfsClientConfigFilePath = classPath + File.separator + CLIENT_CONFIG_FILE;
      
      logger.info("Fast DFS configuration file path:" + fdfsClientConfigFilePath);
      ClientGlobal.init(fdfsClientConfigFilePath);*/
   // 连接超时的时限，单位为毫秒  
      ClientGlobal.setG_connect_timeout(2000);  
      // 网络超时的时限，单位为毫秒  
      ClientGlobal.setG_network_timeout(30000);  
      ClientGlobal.setG_anti_steal_token(false);  
      // 字符集  
      ClientGlobal.setG_charset("UTF-8");  
      ClientGlobal.setG_secret_key(null);  
      // HTTP访问服务的端口号  
      ClientGlobal.setG_tracker_http_port(8088);  
      // Tracker服务器列表  
      InetSocketAddress[] tracker_servers = new InetSocketAddress[1];  
      tracker_servers[0] = new InetSocketAddress("120.25.101.105", 22122);  
      tracker_servers[1] = new InetSocketAddress("112.74.202.205", 22122); 
      ClientGlobal.setG_tracker_group(new TrackerGroup(tracker_servers));
      
      trackerClient = new TrackerClient();
      trackerServer = trackerClient.getConnection();
      
      storageClient = new StorageClient(trackerServer, storageServer);
      
    } catch (Exception e) {
    	logger.error(e.getMessage());
      
    }
  }
  
  
  
  public  String upload(FastDFSFile file) {
	  logger.info( "File Name: " + file.getName() + "		File Length: " + file.getContent().length);
    NameValuePair[] meta_list = new NameValuePair[3];
      meta_list[0] = new NameValuePair("width", file.getWidth());
      meta_list[1] = new NameValuePair("heigth", file.getHeight());
      meta_list[2] = new NameValuePair("author", file.getAuthor());
    
      long startTime = System.currentTimeMillis();
    String[] uploadResults = null;
    try {
     // if(ProtoCommon.activeTest(storageServer.getSocket())){
    	  uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
    //  }
    } catch (IOException e) {
      logger.error("IO Exception when uploadind the file: " + file.getName(), e);
    } catch (Exception e) {
      logger.error("Non IO Exception when uploadind the file: " + file.getName(), e);
    }
    logger.info("upload_file time used: " + (System.currentTimeMillis() - startTime) + " ms");
    
    if (uploadResults == null) {
    	logger.error( "upload file fail, error code: " + storageClient.getErrorCode());
    }
    
    String groupName 		= uploadResults[0];
    String remoteFileName   = uploadResults[1];
    
    String[] remoteFileNames = remoteFileName.split("\\.");
    logger.info( "upload file successfully!!!  " +"group_name: " + groupName + ", remoteFileName:"
            + " " + remoteFileName);
    if(remoteFileNames[1].equalsIgnoreCase("jpg")||remoteFileNames[1].equalsIgnoreCase("jpeg")||remoteFileNames[1].equalsIgnoreCase("gif")||remoteFileNames[1].equalsIgnoreCase("png")||remoteFileNames[1].equalsIgnoreCase("bmp")){
    	return SEPARATOR
    		        + groupName 
    		        + SEPARATOR 
    		        + remoteFileNames[0]+"!"+file.getWidth()+"x"+file.getHeight()+"."+remoteFileNames[1];
    }else{
    	return SEPARATOR
		        + groupName 
		        + SEPARATOR 
		        + remoteFileName;
    }
    
  }
  
  public  FileInfo getFile(String groupName, String remoteFileName) {
    try {
      return storageClient.get_file_info(groupName, remoteFileName);
    } catch (IOException e) {
      logger.error("IO Exception: Get File from Fast DFS failed", e);
    } catch (Exception e) {
      logger.error("Non IO Exception: Get File from Fast DFS failed", e);
    }
    return null;
  }
  
  public  void deleteFile(String groupName, String remoteFileName) throws Exception {
    storageClient.delete_file(groupName, remoteFileName);
  }
  
  public  StorageServer[] getStoreStorages(String groupName) throws IOException {
    return trackerClient.getStoreStorages(trackerServer, groupName);
  }
  
  public  ServerInfo[] getFetchStorages(String groupName, String remoteFileName) throws IOException {
    return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
  }
}