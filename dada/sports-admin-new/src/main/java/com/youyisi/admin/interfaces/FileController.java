package com.youyisi.admin.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.lang.domain.WebResultInfoWrapper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class FileController {

	@RequestMapping(value = "/upload", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public WebResultInfoWrapper uploadFile(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
		List<String> paths = new ArrayList<String>();
		paths.add("/group2/M01/00/1B/Cqp1dlY4RpeANSOGAAILKMLuiqw182!1242x939.jpg");
		webResultInfoWrapper.addResult("path", paths);
		System.out.println("图片上传成功");
		return webResultInfoWrapper;
	}

}
