package com.youyisi.admin.application.member.memberimage;
import com.youyisi.admin.domain.member.memberimage.MemberImage;
import com.youyisi.lang.Page;
/**
 * @author yinjunfeng
 * @time 2015-07-23 20 22 12
 */
public interface MemberImageService {
public MemberImage save(MemberImage entity);
public MemberImage get(Long id);
public Integer delete(MemberImage entity);
public Integer update(MemberImage entity);
public Page<MemberImage> queryPage(Page<MemberImage> page);
}

