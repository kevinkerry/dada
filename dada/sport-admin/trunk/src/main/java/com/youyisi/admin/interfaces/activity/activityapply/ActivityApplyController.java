package com.youyisi.admin.interfaces.activity.activityapply;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.activity.ActivityService;
import com.youyisi.admin.application.activity.activityapply.ActivityApplyService;
import com.youyisi.admin.application.sportuser.SportUserService;
import com.youyisi.admin.domain.activity.Activity;
import com.youyisi.admin.domain.activity.activityapply.ActivityApply;
import com.youyisi.admin.domain.sportuser.SportUser;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.admin.infrastructure.helper.gexinpush.PushToSingleHelper;
import com.youyisi.admin.infrastructure.helper.gexinpush.TransmissionContent;
import com.youyisi.lang.Page;
import com.youyisi.lang.domain.WebResultInfoWrapper;

/**
 * @author shuye
 * @time 2015-07-09 10 02 06
 */
@Controller
@RequestMapping("/activityapply")
public class ActivityApplyController{
    
    @Autowired
    private ActivityApplyService activityapplyService;
    
    @Autowired 
    private ActivityService activityService;
    
    @Autowired
    private SportUserService sportuserService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Integer currentPage,Integer pageSize, Long activityId){
        Page<ActivityApply> page = new Page<ActivityApply>();
        if (currentPage != null) {
            page.setCurrentPage(currentPage);
        }
        if(null != pageSize) {
            page.setPageSize(pageSize);
        }else {
            page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
        }
        
        page.addParam("activityId", activityId);
        
        model.addAttribute("page", activityapplyService.queryPage(page));
        model.addAttribute("activityId", activityId);
        Activity activity = activityService.get(activityId);
        model.addAttribute("activityTitle", activity.getActivityTitle());
        return "activity/activityapply/list";
    }
    
    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Long id,Model model) {
        model.addAttribute("activityapply", activityapplyService.get(id));
        return "activityapply/form";
    }
    
    @RequestMapping(value = "{applyId}/update", method = RequestMethod.GET)
    @ResponseBody
    public WebResultInfoWrapper update(@PathVariable(value = "applyId")Long applyId) {
        WebResultInfoWrapper webResultInfoWrapper = new WebResultInfoWrapper();
        ActivityApply activityApply = activityapplyService.get(applyId);
        webResultInfoWrapper.success();
        webResultInfoWrapper.addResult("activityApply", activityApply);
        return webResultInfoWrapper;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model,String userName, String telephone, String weixin, String org, Long activityId) {
       // activityapplyService.save(userName, telephone, weixin, org, null,  activityId);
        return list(model, 1, null, activityId);
    }
    
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public String modify(Model model, Long applyId, Integer auditStatus, String auditComments) {
        ActivityApply activityapply = activityapplyService.get(applyId);
        if(activityapply != null) {
            if(null != auditStatus) {
                activityapply.setAuditStatus(auditStatus);
            }
            if(StringUtils.isNotBlank(auditComments)) {
                activityapply.setAuditComments(auditComments);
            }
            activityapply.setAuditTime(new Date());
            activityapplyService.save(activityapply);
            sendMessage(activityapply);
        }
        return "success";
    }
    
    private void sendMessage(ActivityApply activityapply) {
    	SportUser sportUser = sportuserService.get(activityapply.getUserId());
    	Activity activity = activityService.get(activityapply.getActivityId());
    	TransmissionContent content = new TransmissionContent();
    	if(activityapply.getAuditStatus()==2){
    		content.setTitle("恭喜您成功报名活动\""+activity.getActivityTitle()+"\"");
    	}else{
    		content.setTitle("很遗憾您未能成功报名活动\""+activity.getActivityTitle()+"\""+":"+activityapply.getAuditComments());
    	}
    	content.setType("ACTIVITY_APPLY_AUDIT");
    	Map<String, Object> entity = new HashMap<String, Object>();
    	entity.put("activityId", activityapply.getActivityId());
    	entity.put("auditComments", activityapply.getAuditComments());
		content.setEntity(entity );
    	PushToSingleHelper.push(sportUser, content);
	}

	@RequestMapping(value = "{applyId}/delete")
    public String delete(Model model, @PathVariable("applyId") Long applyId) {
        ActivityApply activityApply = activityapplyService.get(applyId);
        Activity acvitity = null;
        if(null != activityApply) {
            acvitity = activityService.get(activityApply.getActivityId());
            activityapplyService.delete(activityApply); 
        }
        Long activityId = null;
        if(null != acvitity ) {
            activityId = acvitity.getActivityId();
        }
        return list(model, 1,null, activityId);
    }
    
	@RequestMapping("exportExcel")
	public void exportExcel(Long activityId, String excellName, HttpServletRequest request,
			HttpServletResponse response) {
		// 生成提示信息，
		response.setContentType("application/vnd.ms-excel");
		OutputStream fOut = null;
		try {
			response.setHeader("content-disposition", "attachment;filename="
					+ excellName + ".xls");
			// response.addHeader("Content-Disposition",
			// "attachment;   filename=" + codedFileName + ".xls");
			// 产生工作簿对象
			HSSFWorkbook workbook = createHSSWorkbook(activityId);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (UnsupportedEncodingException e1) {
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {
			}
		}
    }

	private HSSFWorkbook createHSSWorkbook(Long activityId) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 产生工作表对象
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow row = sheet.createRow(0);// 创建一行
		HSSFCell cell = row.createCell(0);// 创建一列
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("姓名(昵称)");
		HSSFCell cell1 = row.createCell(1);// 创建一列
		cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell1.setCellValue("手机");
		HSSFCell cell2 = row.createCell(2);// 创建一列
		cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell2.setCellValue("备注");
		HSSFCell cell3 = row.createCell(3);// 创建一列
		cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell3.setCellValue("报名时间");
		HSSFCell cell4 = row.createCell(4);// 创建一列
		cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell4.setCellValue("审核状态");
		
		
		 List<ActivityApply> activityapply = activityapplyService.queryAll(activityId);
		for (int i = 0; i < activityapply.size(); i++) {
			HSSFRow datarow = sheet.createRow(i+1);// 创建一行
			
			HSSFCell name = datarow.createCell(0);// 创建一列
			name.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(StringUtils.isNotBlank(activityapply.get(i).getName())){
				name.setCellValue(activityapply.get(i).getName()+"("+activityapply.get(i).getMember().getMemberAlias()+")");
			}else{
				name.setCellValue("("+activityapply.get(i).getMember().getMemberAlias()+")");
			}
			
			
			HSSFCell mobile = datarow.createCell(1);// 创建一列
			mobile.setCellType(HSSFCell.CELL_TYPE_STRING);
			mobile.setCellValue(activityapply.get(i).getPhone());
			
			HSSFCell applyDesc = datarow.createCell(2);// 创建一列
			applyDesc.setCellType(HSSFCell.CELL_TYPE_STRING);
			applyDesc.setCellValue(activityapply.get(i).getApplyDesc());
			
			HSSFCell time = datarow.createCell(3);// 创建一列
			time.setCellType(HSSFCell.CELL_TYPE_STRING);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			time.setCellValue(sdf.format(activityapply.get(i).getApplyTime()));
			
			HSSFCell auditStatus = datarow.createCell(4);// 创建一列
			auditStatus.setCellType(HSSFCell.CELL_TYPE_STRING);
			auditStatus.setCellValue(activityapply.get(i).getAuditStatus()==1 ? "未审核" : activityapply.get(i).getAuditStatus()==2 ? "审核通过" :  activityapply.get(i).getAuditStatus()==3 ? "审核不通过":"未审核");
			
		}
		return workbook;
	}  
}

