package com.youyisi.admin.interfaces.club.clubmember;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mchange.v2.beans.BeansUtils;
import com.youyisi.admin.application.club.ClubService;
import com.youyisi.admin.application.club.clubmember.ClubMemberService;
import com.youyisi.admin.domain.club.Club;
import com.youyisi.admin.domain.club.clubmember.ClubMember;
import com.youyisi.admin.infrastructure.helper.CommonHelper;
import com.youyisi.lang.Page;

/**
 * @author yinjunfeng
 * @time 2015-08-04
 */
@Controller
@RequestMapping("/clubmember")
public class ClubMemberController{

	@Autowired
	private ClubMemberService clubMemberService;
	
	@Resource
	private ClubService clubService;

	@RequestMapping(value = "/list")
	public String list(Model model, Integer currentPage, Integer pageSize, Long clubId, ClubMember clubMember) {
		Page<ClubMember> page = new Page<ClubMember>();
		if(null != currentPage) {
			page.setCurrentPage(currentPage);
		}
		if(null != pageSize) {
			page.setPageSize(pageSize);
		}else {
			page.setPageSize(CommonHelper.DEFAULT_PAGESIZE);
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
        try {
            if(null != clubMember) {
               BeansUtils.extractAccessiblePropertiesToMap(params, clubMember);
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        params.put("clubId", clubId);
        page.addParams(params);
		
		model.addAttribute("clubId", clubId);
		Club club = clubService.get(clubId);
		model.addAttribute("clubName", club.getClubName());
		model.addAttribute("page", clubMemberService.queryPage(page));
		return "club/clubmember/list";
	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) {
		model.addAttribute("clubMember", clubMemberService.get(id));
		return "club/clubmember/form";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String detail(ClubMember clubMember) {
		clubMemberService.update(clubMember);
		return "redirect:clubmember/list";
	}
	
	@RequestMapping(value = "/changeStatus")
    public String changeStatus(Model model, Long memberId, String memberStatus) {
        ClubMember clubMember = clubMemberService.get(memberId);
        clubMember.setStatus(memberStatus);
        clubMemberService.save(clubMember);
        
        return list(model, 1, null ,clubMember.getClubId(), null);
    }
	
	@RequestMapping(value = "/check")
    public String check(Model model, Long memberId, Integer result) {
        ClubMember clubMember = clubMemberService.get(memberId);
        clubMember.setRelationFlag(result);
        clubMemberService.save(clubMember);
        
        return list(model, 1, null ,clubMember.getClubId(), null);
    }
}

