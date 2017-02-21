package com.youyisi.admin.application.withdraw.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.user.UserService;
import com.youyisi.admin.application.wallet.WalletDetailService;
import com.youyisi.admin.application.wallet.WalletService;
import com.youyisi.admin.application.withdraw.WithdrawService;
import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.domain.wallet.Wallet;
import com.youyisi.admin.domain.wallet.WalletDetail;
import com.youyisi.admin.domain.withdraw.Withdraw;
import com.youyisi.admin.domain.withdraw.WithdrawRepository;
import com.youyisi.admin.infrastructure.constant.Constant;
import com.youyisi.admin.infrastructure.helper.ArithHelper;
import com.youyisi.admin.infrastructure.helper.DateHelper;
import com.youyisi.admin.infrastructure.helper.gexinpush.PushToSingleHelper;
import com.youyisi.admin.infrastructure.helper.gexinpush.TransmissionContent;
import com.youyisi.admin.infrastructure.utils.DateUtil;
import com.youyisi.lang.Page;

/**
 * @author shuye
 * @time 2016-05-21
 */
@Service
public class WithdrawServiceImpl implements WithdrawService {

	@Autowired
	private WithdrawRepository repository;
	@Autowired
	private UserService userService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private WalletDetailService walletDetailService;

	@Override
	public Withdraw get(Long id) {
		return repository.get(id);
	}

	@Override
	public Withdraw save(Withdraw entity) {
		return repository.save(entity);
	}

	@Override
	public Integer delete(Withdraw entity) {
		return repository.delete(entity);
	}

	@Override
	public Integer update(Withdraw entity) {
		return repository.update(entity);
	}

	@Override
	public Page<Withdraw> queryPage(Page<Withdraw> page) {
		return repository.queryPage(page);
	}

	@Override
	public Page<Withdraw> queryPageWithdraw(Page<Withdraw> page) {
		Page<Withdraw> queryPageWithdraw = repository.queryPageWithdraw(page);
		List<Withdraw> result = queryPageWithdraw.getResult();
		Double withdrawBalance = null;
		for (Withdraw withdraw : result) {
			withdrawBalance = walletDetailService.getWithdrawBalance(withdraw.getUserId(), withdraw.getMoney(),
					DateUtil.timestampToDate(withdraw.getCreateTime()), withdraw.getCreateTime());
			if (withdrawBalance == null) {
				withdrawBalance = 0.0;
			}
			withdraw.setBalance(withdrawBalance);
		}
		return queryPageWithdraw;
	}

	@Override
	public Withdraw getByDrawbackNum(String withdrawNumber) {

		return repository.getByDrawbackNum(withdrawNumber);
	}

	@Override
	public Integer reset(Long[] wid) {
		int row = 0;
		Withdraw withdraw = null;
		long value = 0;
		if (wid.length > 1) {
			withdraw = get(wid[0]);
			if (withdraw != null) {
				value = withdraw.getUserId();
			}
			for (Long l : wid) {
				withdraw = get(l);
				if (value != withdraw.getUserId()) {
					return row;
				}
			}
		}
		for (Long l : wid) {
			withdraw = get(l);
			if (withdraw != null) {
				withdraw.setStatus(2);
				withdraw.setUpdateTime(System.currentTimeMillis());
				row = update(withdraw);
				String dateTime = DateHelper.dateTimeToStr(withdraw.getCreateTime());
				pushMessage(withdraw, "提现成功", "您于" + dateTime + "申请的一笔提现" + withdraw.getMoney() + "元已审核通过，请注意查收。");
			}
		}
		return row;
	}

	private void pushMessage(Withdraw userWithDrawPackage, String title, String contents) {
		User sportUser = userService.get(userWithDrawPackage.getUserId());
		TransmissionContent content = new TransmissionContent();
		content.setTitle(title);
		Map<String, Object> entity = new HashMap<String, Object>();
		entity.put("content", contents);
		entity.put("sendTime", System.currentTimeMillis());
		content.setType(Constant.PUSH_SYS_MESSAGE);
		content.setEntity(entity);
		content.setToUserId(userWithDrawPackage.getUserId());
		PushToSingleHelper.push(sportUser, content);
	}

	@Override
	public Integer sendBack(Long wid, Integer code) {
		int row = 0;
		Withdraw withdraw = get(wid);
		if (withdraw != null) {
			if (withdraw.getPrincipal() != null) {
				Wallet wallet = walletService.getByUserId(withdraw.getUserId());
				wallet.setTotalAsset(ArithHelper.add(wallet.getTotalAsset(), withdraw.getMoney()));
				wallet.setPrincipal(ArithHelper.add(wallet.getPrincipal(), withdraw.getPrincipal()));
				row = walletService.update(wallet);
				if (row > 0) {
					WalletDetail entity = new WalletDetail();
					entity.setUserId(withdraw.getUserId());
					entity.setCreateTime(System.currentTimeMillis());
					entity.setDate(DateUtil.currentDateForDay());
					entity.setMoney(withdraw.getMoney());
					entity.setResult(wallet.getTotalAsset());
					entity.setType(4);
					walletDetailService.save(entity);

					withdraw.setStatus(-1);
					withdraw.setUpdateTime(System.currentTimeMillis());

					String dateTime = DateHelper.dateTimeToStr(withdraw.getCreateTime());
					String str = "您于" + dateTime + " 申请的一笔" + withdraw.getMoney() + "元提现申请处理失败，已退还到您哒哒app账户中。\n";
					String title = "提现申请失败";
					String reason = null;
					// 情况1：未实名认证
					if (code == 1) {
						reason = "原因：支付宝未实名认证。\n" + "解决方法：请在支付宝中实名认证后重新提现。";
						pushMessage(withdraw, title, str + reason);
					}
					// 情况2：账户不存在或设置隐私保护
					if (code == 2) {
						reason = "原因：支付宝账户不存在或设置隐私保护。\n" + "解决方法：请确认您的支付宝账号正确，并在支付宝的隐私设置中开启相关账号的隐私权限。";
						pushMessage(withdraw, title, str + reason);
					}
					// 情况3：账号与真实姓名不符
					if (code == 3) {
						reason = "原因：账号与真实姓名不符。\n" + "解决方法：请修改您提供的账号与真实姓名，确保符合后重新提现。";
						pushMessage(withdraw, title, str + reason);
					}
					// 情况4：账号或真实姓名错误
					if (code == 4) {
						reason = "原因：账号或真实姓名错误。\n" + "解决方法：请修改您提供的账号与真实姓名，确保正确后重新提现。";
						pushMessage(withdraw, title, str + reason);
					}
					// 情况5:多账号使用同一个支付宝提现
					if (code == 5) {
						reason = "原因：提现账号异常。\n" + "解决方法：请您联系客服QQ：2689572887，或拨打客服电话020-62326560。";
						pushMessage(withdraw, title, str + reason);
					}
					withdraw.setNote(reason.replaceAll("\n", ""));
					row = update(withdraw);
				}
			} else {
				row = -1;
			}

		}
		return row;
	}

	@Override
	public Double sumWithdraw(Long userId, Integer status) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userId != null) {
			map.put("userId", userId);
		}
		if (status != null) {
			map.put("status", status);
		}
		return repository.sumWithdraw(map);
	}

	@Override
	public Integer batchReject(Long[] wid, Integer code) {
		Integer row = 0;
		Integer sendBack = 0;
		for (Long id : wid) {
			sendBack = sendBack(id, code);
			if (sendBack != -1) {
				row += sendBack;
			} else {
				return -1;
			}

		}
		return row;
	}

	@Override
	public ResponseEntity<byte[]> exportReport(String path, Long beginTime, Long endTime) {
		Map<String, Object> map = null;
		HttpHeaders headers = new HttpHeaders();
		byte[] bytes = null;
		path = path + "resources\\report\\" + beginTime.toString() + endTime.toString() + ".xls";
		File file = new File(path);
		String name = null;
		try {
			if (!file.exists()) {
				map = new HashMap<String, Object>();
				map.put("beginTime", beginTime);
				map.put("endTime", endTime);
				List<Withdraw> withdrawList = repository.getWithdrawList(map);
				String xlsPath = createTable(path, withdrawList);
				file = new File(xlsPath);
			}
			name = DateUtil.timestampToStr(beginTime) + "至" + DateUtil.timestampToStr(endTime) + "提现.xls";
			// 为了解决中文名称乱码问题
			headers.setContentDispositionFormData("attachment", new String(name.getBytes("UTF-8"), "iso-8859-1"));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			bytes = FileUtils.readFileToByteArray(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);

	}

	private String createTable(String fileName, List<Withdraw> withdrawList) {
		FileOutputStream fos = null;
		HSSFWorkbook demoWorkBook = new HSSFWorkbook();// 创建excel
		HSSFSheet demoSheet = demoWorkBook.createSheet("提现详情");// 创建sheet
		String[] tableHeader = { "ID", "昵称", "用户ID", "提现编号", "提现账号", "真实姓名", "提现申请时间", "处理时间", "提现金额", "包含本金", "包含利息",
				"本金余额", "利息余额", "状态" };// 表头名字
		int cellNumber = tableHeader.length;// 表头数目

		/************ 表头 *************/
		HSSFRow headerRow = demoSheet.createRow((short) 0); // 第一行
		for (int i = 0; i < cellNumber; i++) {
			HSSFCell headerCell = headerRow.createCell(i);// 创建第一行第i个单元格,从0开始
			headerCell.setCellType(HSSFCell.CELL_TYPE_STRING);// 设置内容格式为字符串型
			headerCell.setCellValue(tableHeader[i]);// 设置内容
		}

		/*********************** 内容 ********************/
		int withdrawSize = withdrawList.size();
		Withdraw withdraw = null;
		for (int i = 0; i < withdrawSize; i++) {
			HSSFRow row = demoSheet.createRow(i + 1);// 创建第rowIndex行

			withdraw = withdrawList.get(i);
			row.createCell(0).setCellValue(withdraw.getId());
			row.createCell(1).setCellValue(withdraw.getUser().getNickname());
			row.createCell(2).setCellValue(withdraw.getUser().getId());
			row.createCell(3).setCellValue(withdraw.getWithdrawNumber());
			row.createCell(4).setCellValue(withdraw.getAlipay().getAlipay());
			row.createCell(5).setCellValue(withdraw.getAlipay().getRealName());
			row.createCell(6).setCellValue(DateUtil.timestampToStrDateTime(withdraw.getCreateTime()));
			if (withdraw.getUpdateTime() != null) {
				row.createCell(7).setCellValue(DateUtil.timestampToStrDateTime(withdraw.getUpdateTime()));
			} else {
				row.createCell(7).setCellValue("");
			}
			row.createCell(8).setCellValue(withdraw.getMoney());
			if (withdraw.getPrincipal() == null) {
				withdraw.setPrincipal(0.0);
			}
			row.createCell(9).setCellValue(withdraw.getPrincipal());
			if (withdraw.getMoney() == null) {
				withdraw.setMoney(0.0);
			}
			row.createCell(10).setCellValue(ArithHelper.sub(withdraw.getMoney(), withdraw.getPrincipal()));
			if (withdraw.getWalletPrincipal() == null) {
				withdraw.setWalletPrincipal(0.0);
			}
			if (withdraw.getTotalAsset() == null) {
				withdraw.setTotalAsset(0.0);
			}
			row.createCell(11).setCellValue(withdraw.getWalletPrincipal());
			row.createCell(12).setCellValue(ArithHelper.sub(withdraw.getTotalAsset(), withdraw.getWalletPrincipal()));
			if (withdraw.getStatus() == 0) {
				row.createCell(13).setCellValue("申请中");
			}
			if (withdraw.getStatus() == 1) {
				row.createCell(13).setCellValue("提现中");
			}
			if (withdraw.getStatus() == 2) {
				row.createCell(13).setCellValue("处理完成");
			}
			if (withdraw.getStatus() == -1) {
				row.createCell(13).setCellValue("驳回");
			}
		}
		// 写入文件
		try {
			fos = new FileOutputStream(fileName);
			demoWorkBook.write(fos);
			fos.close();
			demoWorkBook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileName;
	}

}
