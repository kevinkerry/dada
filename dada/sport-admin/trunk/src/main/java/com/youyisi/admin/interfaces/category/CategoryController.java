package com.youyisi.admin.interfaces.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.category.CategoryService;
import com.youyisi.admin.domain.category.Category;
import com.youyisi.lang.domain.WebResultInfoWrapper;

/**
 * 
 * @author yinjunfeng
 * @date Jul 8, 2015
 */
@Controller
@RequestMapping("/category")
public class CategoryController{
    
    @Autowired
    private CategoryService categoryService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public WebResultInfoWrapper list(Category category) {
        WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
        List<Category> categoryList = categoryService.queryAll(category);
        webResultInfoWrapper.success();
        webResultInfoWrapper.addResult("categories", categoryList);
        
        return webResultInfoWrapper;
    }

}

