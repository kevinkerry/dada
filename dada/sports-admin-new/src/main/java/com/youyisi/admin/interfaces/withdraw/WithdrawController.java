package com.youyisi.admin.interfaces.withdraw;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyisi.admin.application.withdraw.WithdrawService;
import com.youyisi.admin.domain.withdraw.Withdraw;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.admin.infrastructure.utils.ResponseModel;
import com.youyisi.admin.infrastructure.utils.StrUtil;
import com.youyisi.admin.interfaces.BaseController;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-21
 */
@Controller
@RequestMapping("/withdraw")
public class WithdrawController extends BaseController {

	@Autowired
	private WithdrawService withdrawService;

	// @Autowired
	// private AlipayHelper alipayHelper;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer currentPage, Integer pageSize, Integer status, String condition,
			String field, String sort) {
		Page<Withdraw> page = new Page<Withdraw>();
		if (null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if (null != pageSize) {
			page.setPageSize(pageSize);
		} else {
			page.setPageSize(defaultPageSize);
		}
		if (StrUtil.notEmpty(status)) {
			page.addParam("status", status);
		}
		if (StrUtil.notEmpty(condition)) {
			page.addParam("condition", condition);
		}
		if (StrUtil.notEmpty(field)) {
			page.addParam("field", field);
		}
		if (StrUtil.notEmpty(sort)) {
			page.addParam("sort", sort);
		}
		model.addAttribute("page", withdrawService.queryPageWithdraw(page));
		return "withdraw/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("withdraw", withdrawService.get(id));
		return "withdraw/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(Withdraw withdraw) {
		withdrawService.update(withdraw);
		return "redirect:withdraw/list";
	}

	@RequestMapping(value = "/passWithdraw", method = RequestMethod.GET)
	public String passWithdraw(Model model, Integer currentPage, Integer pageSize, Integer status, String condition,
			Long[] wid, String field, String sort) {
		if (wid != null) {
			Integer row = withdrawService.reset(wid);
			if (row == 0) {
				model.addAttribute(result, "出错了! 多选只能是同一个用户");
			}
		}
		return list(model, currentPage, pageSize, status, condition, field, sort);
	}

	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String reset(Model model, Integer currentPage, Integer pageSize, Integer status, String condition,
			Long[] wid, String field, String sort) {
		if (wid != null) {
			Integer row = withdrawService.reset(wid);
			if (row == 0) {
				model.addAttribute(result, "处理出错了");
			}
		}
		return list(model, currentPage, pageSize, status, condition, field, sort);
	}

	@RequestMapping(value = "/sendBack", method = RequestMethod.GET)
	public String sendBack(Model model, Integer currentPage, Integer pageSize, Integer status, String condition,
			Long wid, String field, String sort, Integer code) {
		if (wid != null) {
			Integer row = withdrawService.sendBack(wid, code);
			if (row == -1) {
				model.addAttribute(result, "无法驳回该提现");
			}
		}
		return list(model, currentPage, pageSize, status, condition, field, sort);
	}

	@RequestMapping(value = "/batchReject", method = RequestMethod.GET)
	public String batchReject(Model model, Integer currentPage, Integer pageSize, Integer status, String condition,
			Long[] wid, String field, String sort, Integer code) {
		if (wid != null) {
			Integer row = withdrawService.batchReject(wid, code);
			if (row == -1) {
				model.addAttribute(result, "无法驳回该提现");
			}
		}
		return list(model, currentPage, pageSize, status, condition, field, sort);
	}

	@ResponseBody
	@RequestMapping(value = "/getUserRecord", method = RequestMethod.GET)
	public ResponseModel getUserRecord(Integer currentPage, Integer pageSize, Long userId, Integer status) {
		ResponseModel response = new ResponseModel();
		if (userId != null) {
			Page<Withdraw> page = new Page<Withdraw>();
			if (null != currentPage) {
				page.setCurrentPage(currentPage);
			}
			if (null != pageSize) {
				page.setPageSize(pageSize);
			} else {
				page.setPageSize(7);
			}
			if (status != null) {
				page.addParam("status", status);
			}
			page.addParam("userId", userId);
			page.addParam("status", 2);
			response.setMap("record", withdrawService.queryPage(page));
			Double sumWithdraw = withdrawService.sumWithdraw(userId, 2);
			if (sumWithdraw == null) {
				sumWithdraw = 0.0;
			}
			response.setMap("sumWithdraw", sumWithdraw);
			response.success();
		}
		return response;
	}

	@RequestMapping(value = "/exportReport", method = RequestMethod.GET)
	public ResponseEntity<byte[]> exportReport(HttpServletRequest request, String beginTime, String endTime) {
		String path = request.getSession().getServletContext().getRealPath("/");
		ResponseEntity<byte[]> exportReport = null;
		if (StrUtil.notEmpty(beginTime) && StrUtil.notEmpty(endTime)) {
			long beginTimestamp = DateUtil.strToTimestamp(beginTime.replace("T", " ") + ":00");
			long endTimestamp = DateUtil.strToTimestamp(endTime.replace("T", " ") + ":00");
			exportReport = withdrawService.exportReport(path, beginTimestamp, endTimestamp);
		}
		return exportReport;
	}

}
