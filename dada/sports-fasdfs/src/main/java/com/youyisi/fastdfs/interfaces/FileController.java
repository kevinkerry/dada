package com.youyisi.fastdfs.interfaces;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.youyisi.fastdfs.application.FileService;
import com.youyisi.fastdfs.domain.FastDFSFile;
import com.youyisi.fastdfs.domain.WebResultInfoWrapper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class FileController {
	@Autowired
	private FileService fileService;
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public WebResultInfoWrapper uploadFile(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="orientations", required=false) Integer[] orientations) {
		
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		try {
			List<String> paths = new ArrayList<String>();
			// 转型为MultipartHttpRequest：
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 获得文件：
			MultiValueMap<String, MultipartFile> multipartFiles = multipartRequest.getMultiFileMap();
			if (multipartFiles != null && multipartFiles.size() > 0) {
				Set<String> fileNames = multipartFiles.keySet();
				Integer index = 0;
				for (String fileName : fileNames) {
					List<MultipartFile> fileList = multipartFiles.get(fileName);
					MultipartFile file = fileList.get(0);
					String path = saveFile(file,orientations,index);
					paths.add(path);
					index++;
				}
				webResultInfoWrapper.addResult("path", paths);
			}
			return webResultInfoWrapper;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			webResultInfoWrapper.setState(WebResultInfoWrapper.FAILED);
			webResultInfoWrapper.setMessage("上传文件出错！");
			return webResultInfoWrapper;
		}
	}

	private String saveFile(MultipartFile file, Integer[] orientations,Integer index) throws IOException {
		FastDFSFile fastDfsFile  = null;
		String extName = getExtName(file);
		if(extName.equalsIgnoreCase("jpg")||extName.equalsIgnoreCase("jpeg")||extName.equalsIgnoreCase("gif")||extName.equalsIgnoreCase("png")||extName.equalsIgnoreCase("bmp")){
			fastDfsFile = picSave(file, orientations, index, extName);
		}else{
			fastDfsFile = new FastDFSFile(file.getOriginalFilename(),file.getBytes(),extName);
		}
		
		return fileService.upload(fastDfsFile );
	}

	private FastDFSFile picSave(MultipartFile file, Integer[] orientations,
			Integer index, String extName) throws IOException {
		FastDFSFile fastDfsFile;
		BufferedImage image = ImageIO.read(file.getInputStream());
		if(orientations!=null&&orientations.length>0&&orientations[index] == 6){
			image = rotateImage(image,90);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ImageIO.write(image,extName,out);
			fastDfsFile = new FastDFSFile(file.getOriginalFilename(),out.toByteArray(),extName,image.getHeight()+"",image.getWidth()+"");
		}else{
			fastDfsFile = new FastDFSFile(file.getOriginalFilename(),file.getBytes(),extName,image.getHeight()+"",image.getWidth()+"");
		}
		return fastDfsFile;
	}
	
	
	public static BufferedImage rotateImage(final BufferedImage bufferedimage,
            final int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }
	private String getExtName(MultipartFile file){
		String fileName = file.getOriginalFilename();
		return fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
	}
	

}
