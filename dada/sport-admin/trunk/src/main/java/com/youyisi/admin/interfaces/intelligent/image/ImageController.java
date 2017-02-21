package com.youyisi.admin.interfaces.intelligent.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.intelligent.image.ImageService;
import com.youyisi.admin.domain.intelligent.image.Image;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/image")
public class ImageController {
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public String delete(Image image) {
		imageService.delete(image);
		return "success";
	}
}
