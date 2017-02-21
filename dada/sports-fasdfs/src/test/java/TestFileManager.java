import java.io.File;
import java.io.FileInputStream;

import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageServer;
import org.junit.Assert;
import org.junit.Test;

import com.youyisi.fastdfs.domain.FastDFSFile;
import com.youyisi.fastdfs.infrastructure.helper.FileManager;

public class TestFileManager {

	@Test
	public void upload() throws Exception {
		File content = new File("f:\\bg.png");
		
		FileInputStream fis = new FileInputStream(content);
	    byte[] file_buff = null;
	    if (fis != null) {
	    	int len = fis.available();
	    	file_buff = new byte[len];
	    	fis.read(file_buff);
	    }
		
		FastDFSFile file = new FastDFSFile("bg", file_buff, "png");
		
		String fileAbsolutePath = new FileManager().upload(file);
		System.out.println(fileAbsolutePath);
		fis.close();
	}
	
	@Test
	public void getFile() throws Exception {
		FileInfo file = new FileManager().getFile("group1", "M00/00/00/CgFQyFVHIU2ATRbOAABG2wdjU0c508.jpg");
		Assert.assertNotNull(file);
		String sourceIpAddr = file.getSourceIpAddr();
	    long size = file.getFileSize();
	    System.out.println("ip:" + sourceIpAddr + ",size:" + size);
	}
	
	@Test
	public void getStorageServer() throws Exception {
		StorageServer[] ss = new FileManager().getStoreStorages("group1");
		Assert.assertNotNull(ss);
		
		for (int k = 0; k < ss.length; k++){
			System.err.println(k + 1 + ". " + ss[k].getInetSocketAddress().getAddress().getHostAddress() + ":" + ss[k].getInetSocketAddress().getPort());
	    }
	}
	
	@Test
	public void getFetchStorages() throws Exception {
		ServerInfo[] servers = new FileManager().getFetchStorages("group1", "M00/00/00/CgFQyFVHIU2ATRbOAABG2wdjU0c508.jpg");
		Assert.assertNotNull(servers);
		
		for (int k = 0; k < servers.length; k++) {
    		System.err.println(k + 1 + ". " + servers[k].getIpAddr() + ":" + servers[k].getPort());
    	}
	}
	
}