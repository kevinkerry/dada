package com.youyisi.vote.interfaces.controller.luck;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Encoder;

import com.youyisi.lang.Page;
import com.youyisi.lang.helper.JsonHelper;
import com.youyisi.vote.application.luck.LuckService;
import com.youyisi.vote.application.luck.LuckUserService;
import com.youyisi.vote.application.luck.UserInfoService;
import com.youyisi.vote.application.user.RedisUserService;
import com.youyisi.vote.domain.luck.Luck;
import com.youyisi.vote.domain.luck.LuckUser;
import com.youyisi.vote.domain.luck.UserInfo;
import com.youyisi.vote.infrastructure.cache.redis.RedisClient;
import com.youyisi.vote.infrastructure.util.SignUtil;
import com.youyisi.vote.infrastructure.util.SpotHelper;
import com.youyisi.vote.infrastructure.util.Ticket;

/**
 * @author shuye
 * @time 2014年5月8日
 */
@Controller
@RequestMapping(value = "/luck")
public class LuckController {
    @Autowired
    private RedisUserService redisUserService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private LuckUserService luckUserService;
    @Autowired
    private LuckService luckService;
	
    private String url = "http://vote.dadasports.cn";
    private static final Log logger = LogFactory.getLog(LuckController.class);
	@RequestMapping(value="/index")
	public String index(@RequestParam(value="code", required=false) String code,@RequestParam(value="state", required=false) String state,Model model,HttpServletRequest request){
		logger.info("code:" + code);
		if (StringUtils.isNotBlank(code)) {
			 UserInfo u = Ticket.getUserInfo(code);
			 u.setImageurl(JsonHelper.fromJsonString((String)RedisClient.get("vote:vote:imageurl:" + state), String.class));
			 if(StringUtils.isNotBlank(code)&& (u==null||StringUtils.isBlank(u.getOpenid()))){
					return "redirect:/luck/index-share";
				}
			 UserInfo userInfo = userInfoService.getByOpenId(u.getOpenid());
			 if(userInfo==null&&u!=null&&StringUtils.isNotBlank(u.getOpenid())){
				 userInfoService.save(u);
				 model.addAttribute("userInfo", u);
				 model.addAttribute("isLottery", false);
			 }else{
				 model.addAttribute("userInfo", userInfo);
				 LuckUser luckUser = luckUserService.getByUserId(userInfo.getId());
				 if(luckUser != null){
					 model.addAttribute("luckUser", luckUser);
					 model.addAttribute("isLottery", true);
				 }else{
					 model.addAttribute("isLottery", false);
				 }
			 }
			model.addAttribute("sign", SignUtil.getSin(url+"/luck/index?"+request.getQueryString()));
		}else{
			return "redirect:/luck/index-share";
		}
		Page<LuckUser> page = new Page<LuckUser>();
		page.setCurrentPage(1);
		page.setPageSize(5);
		model.addAttribute("luckList",luckUserService.queryPage(page).getResult());
		return "luck/index";
	}



    @RequestMapping(value = "/index-share", method = RequestMethod.GET)
    public void share(Long id, HttpServletResponse response) {
        try {
            String redirect_url = "http://vote.dadasports.cn/luck/index";
            redirect_url = URLEncoder.encode(redirect_url, "utf-8");
            response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf8bd7ea02a023bb7&redirect_uri=" + redirect_url + "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/lottery")
    @ResponseBody
    public Map<String, Object> luck(@RequestParam(value = "id") Long userId, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        LuckUser luckUser = luckUserService.getByUserId(userId);
        if (luckUser != null) {
            result.put("status", "fail");
            result.put("data", luckUser);
            result.put("msg", "haveluck" + luckUser.getId());
            return result;
        }
        LuckUser entity = new LuckUser();
        entity.setLuckId(getLuckId());
        entity.setLuck(luckService.get(entity.getLuckId()));
        entity.setUserId(userId);
        entity.setCreateTime(System.currentTimeMillis());
        luckUserService.save(entity);
        result.put("status", "success");
        result.put("data", luckUserService.save(entity));
        return result;
    }

    @RequestMapping(value = "/{id}/luck")
    public String luckDetail(@PathVariable("id") Long id, Model model, HttpServletRequest request) {
        LuckUser luckUser = luckUserService.get(id);
        model.addAttribute("luckUser", luckUser);
        return "luck/detail";
    }

    @RequestMapping(value = "/queryPage")
    @ResponseBody
    public Page<LuckUser> queryPage(Page<LuckUser> page) {
        page = luckUserService.queryPage(page);
        return page;
    }

    @RequestMapping(value = "/result")
    public String result(Model model) {
        Page<LuckUser> page = new Page<LuckUser>();
        page.setCurrentPage(1);
        page.setPageSize(5000);
        model.addAttribute("luckUsers", luckUserService.queryPage(page).getResult());
        return "luck/result";
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public Map<String, Long> test(Model model) {
        Map<String, Long> map = new HashMap<String, Long>();
        for (int i = 0; i < 1000; i++) {
            Long id = getLuckId();
            map.put(i + "", id);
        }
        return map;
    }


    private Long getLuckId() {
        Boolean b = SpotHelper.spot(80);
        Integer luckId = SpotHelper.random(4) + 1;
        if (b) {
            Luck luck = luckService.get(Long.parseLong(luckId + ""));
            String voted = (String) RedisClient.get("vote:lucks:" + luck.getId());
            if (StringUtils.isNotBlank(voted)) {
                Integer count = Integer.parseInt(voted);
                if (count.longValue() < luck.getTotal().longValue()) {
                    RedisClient.increment("vote:lucks:" + luck.getId(), 1l);
                    return luck.getId();
                } else {
                    List<Luck> lucks = luckService.getLuck();
                    for (Luck l : lucks) {
                        String vote = (String) RedisClient.get("vote:lucks:" + l.getId());
                        if (StringUtils.isNotBlank(vote)) {
                            Integer counts = Integer.parseInt(vote);
                            if (counts.longValue() < l.getTotal().longValue()) {
                                RedisClient.increment("vote:lucks:" + l.getId(), 1l);
                                return l.getId();
                            }
                        }
                    }
                    return 5l;
                }

            } else {
                RedisClient.increment("vote:lucks:" + luck.getId(), 1l);
                return luck.getId();
            }
        } else {
            return 5l;
        }
    }

    @RequestMapping(value = "/getImgData", method = RequestMethod.GET)
    @ResponseBody
    public String getBase64Data(@RequestParam String imgUrl) {
        String[] tempArr = imgUrl.split("!");
        String head = "data:image/jpg;base64,";
        String type = "jpg";
        if (tempArr.length > 1) {
            String[] imgParam = tempArr[1].split("\\.");
            if (imgParam.length > 1) {
                type = imgParam[1];
            }
            head = "data:image/" + type + ";base64,";
        }
        return head + getBase64(imgUrl, type);
    }

    /**
     * 根据地址获得数据的字节流
     *
     * @param strUrl 网络连接地址
     * @return
     */
    public static String getBase64(String strUrl, String type) {
        try {
            URL url = new URL(strUrl);
            BufferedImage image = ImageIO.read(url);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, type, bos);
            return new BASE64Encoder().encode(bos.toByteArray());//得到图片的二进制数据
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
