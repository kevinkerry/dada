package com.youyisi.admin.application.member.memberimage.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youyisi.admin.application.member.memberimage.MemberImageService;
import com.youyisi.admin.domain.member.memberimage.MemberImage;
import com.youyisi.admin.domain.member.memberimage.MemberImageRepository;
import com.youyisi.lang.Page;
/**
 * @author yinjunfeng
 * @time 2015-07-23 20 22 12
 */
@Service
public class MemberImageServiceImpl implements MemberImageService {
@Autowired
private MemberImageRepository repository;
@Override
public MemberImage get(Long id) {
return repository.get(id);
}
@Override
public MemberImage save(MemberImage entity) {
return repository.save(entity);
}
@Override
public Integer delete(MemberImage entity) {
return repository.delete(entity);
}
@Override
public Integer update(MemberImage entity) {
return repository.update(entity);
}
@Override
public Page<MemberImage> queryPage(Page<MemberImage> page) {
return repository.queryPage(page);
}
}

