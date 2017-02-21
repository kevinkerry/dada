package com.youyisi.admin.interfaces.member;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyisi.admin.application.member.MemberService;
import com.youyisi.admin.domain.member.Member;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;
/**
 * @author shuye
 * @time 2015-07-09 14 38 16
 */
@Controller
@RequestMapping("/member")
public class MemberController{
    
    @Autowired
    private MemberService memberService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Integer currentPage, Integer pageSize, String memberName, String memberAlias, String city, String sex, Integer recommendFlag) {
        Page<Member> page = new Page<Member>();
        if(null != currentPage) {
            page.setCurrentPage(currentPage);
        }
        if(null != pageSize) {
            page.setPageSize(pageSize);
        }else {
            page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
        }
        try {
            if(StringUtils.isNotEmpty(memberName)) {
                page.addParam("memberName", URLDecoder.decode(memberName, "UTF-8"));
            }
            if(StringUtils.isNotEmpty(memberAlias)) {
                page.addParam("memberAlias", URLDecoder.decode(memberAlias, "UTF-8"));
            }
            if(StringUtils.isNotEmpty(city)) {
                page.addParam("city", URLDecoder.decode(city, "UTF-8"));
            }
            if(StringUtils.isNotEmpty(sex)) {
                page.addParam("sex", URLDecoder.decode(sex, "UTF-8"));
            }
            if(null != recommendFlag) {
                page.addParam("recommendFlag", recommendFlag);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        model.addAttribute("page", memberService.queryPage(page));
        return "member/list";
    }
    
    @RequestMapping(value = "/{memberId}/update", method = RequestMethod.GET)
    public String detail(@PathVariable("memberId")Long memberId,Model model) {
        model.addAttribute("member", memberService.get(memberId));
        return "member/update";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String detail(Model model, Member member) {
        memberService.update(member);
        model.addAttribute("member", memberService.get(member.getMemberId()));
        return "member/update";
    }
    
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modify(Model model, Long memberId, Integer recommendFlag, String memberState, Integer recommendOrder ) {
        Member member = memberService.get(memberId);
        if(member != null) {
            if(null != recommendFlag) {
                member.setRecommendFlag(recommendFlag);
            }
            if(StringUtils.isNotBlank(memberState)) {
                member.setMemberState(memberState);
            }
            if(null != recommendOrder) {
                member.setRecommendOrder(recommendOrder);
            }
            
            memberService.modify(member);
        }
        return list(model, 1, null, null, null,null,null, null);
    }
    
    @RequestMapping(value = "{memberId}/delete")
    public String delete(Model model, @PathVariable("memberId") Long memberId) {
        Member member = memberService.get(memberId);
        if(null != member) {
            memberService.delete(member); 
        }
        return list(model, 1, null, null, null,null,null, null);
    }
    
    @RequestMapping(value = "/batchRecommend")
    public String batchRecommend(Model model, Long[] memberIds) {
        if(null != memberIds && memberIds.length > 0) {
            for(Long memberId : memberIds) {
                Member member = memberService.get(memberId); 
                if(null != member && null != member.getRecommendFlag() && member.getRecommendFlag() != 1) {
                    member.setRecommendFlag(1);
                    member.setRecommendOrder(0);
                    memberService.modify(member);
                }
            }
        }
        return list(model, 1, null, null, null,null,null, null);
    }
}

