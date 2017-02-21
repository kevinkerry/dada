package com.youyisi.admin.application.member.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.member.MemberService;
import com.youyisi.admin.domain.member.Member;
import com.youyisi.admin.domain.member.MemberRepository;
import com.youyisi.admin.domain.member.memberimage.MemberImage;
import com.youyisi.admin.domain.member.memberimage.MemberImageRepository;
import com.youyisi.admin.infrastructure.constant.Constant;
import com.youyisi.admin.infrastructure.helper.CurrentUserHelper;
import com.youyisi.admin.infrastructure.message.ActiveMqSender;
import com.youyisi.lang.Page;

/**
 * 
 * @author yinjunfeng
 * @date Jul 9, 2015
 */
@Service
public class MemberServiceImpl implements MemberService {
    
    @Autowired
    private MemberRepository repository;
    @Autowired
    private ActiveMqSender activeMqSender;
    @Autowired
    private MemberImageRepository memberImageRepository;
    
    @Override
    public Member get(Long id) {
        return repository.get(id);
    }
    
    @Override
    public Member save(Member entity) {
        return repository.save(entity);
    }
    
    @Override
    public Integer delete(Member entity) {
        return repository.delete(entity);
    }
    
    @Override
    public Integer update(Member member) {
        Integer currentUserId = CurrentUserHelper.getCurrentUser().getId();
        Member existMember = repository.get(member.getMemberId());
        Date curDate = new Date();
        
        List<MemberImage> memberImages = member.getMemberImages();
        if(null != memberImages && memberImages.size() > 0) {
            for(MemberImage memberImage : memberImages){
                if(null == memberImage.getImgId()) {
                    memberImage.setCreator(Long.valueOf(currentUserId.toString()));
                    memberImage.setModifier(Long.valueOf(currentUserId.toString()));
                    memberImage.setCreatedTime(curDate);
                    memberImage.setUpdatedTime(curDate);
                    memberImage.setMemberId(member.getMemberId());
                    memberImage.setUserId(existMember.getUserId());
                    memberImage.setStatus("A");
                }else {
                    MemberImage existedMemberImage = memberImageRepository.get(memberImage.getImgId());
                    memberImage.setUpdatedTime(curDate);
                    memberImage.setMemberId(member.getMemberId());
                    memberImage.setUserId(existMember.getUserId());
                    memberImage.setCreator(Long.valueOf(currentUserId.toString()));
                    memberImage.setModifier(Long.valueOf(currentUserId.toString()));
                    memberImage.setCreatedTime(existedMemberImage.getCreatedTime());
                    memberImage.setUpdatedTime(curDate);
                    memberImage.setStatus(existedMemberImage.getStatus());
                }
                memberImageRepository.save(memberImage);
            }
        }
        existMember.setMemberLogo(member.getMemberLogo());
        Integer count =  repository.update(existMember);
        activeMqSender.send(member.getMemberId().toString(),Constant.JMS_QUEUE_MEMBER);
        return count;
    }
    
    @Override
    public Page<Member> queryPage(Page<Member> page) {
        return repository.queryPage(page);
    }
    
    @Override
    public Member getByUserId(Long userId) {
        return repository.getByUserId(userId);
    }

    @Override
    public void modify(Member member) {
        repository.update(member);
        activeMqSender.send(member.getMemberId().toString(),Constant.JMS_QUEUE_MEMBER);
    }
}

