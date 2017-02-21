package com.youyisi.admin.infrastructure.persist.adminuser;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.adminuser.AdminUser;
import com.youyisi.admin.domain.adminuser.AdminUserRepository;
import com.youyisi.mybatis.MybatisOperations;

@Repository
public class MybatisAdminUserRepositoryImpl extends MybatisOperations<Integer, AdminUser>
		implements AdminUserRepository {

	@Override
	public AdminUser save(AdminUser user) {
		user = super.save(user.beforeCreate());
		saveRoles(user);
		saveAuths(user);
		return user;
	}

	@Override
	public int update(AdminUser user) {
		int nummber = super.update(user.beforeCreate());
		saveRoles(user);
		saveAuths(user);
		return nummber;
	}

	@Override
	public int delete(AdminUser user) {
		getSqlSession().delete(getNamespace().concat(".deleteRoles"), user);
		getSqlSession().delete(getNamespace().concat(".deleteAuths"), user);
		return super.delete(user);
	}

	@Override
	public AdminUser getByUsername(String username) {
		AdminUser user = (AdminUser) getSqlSession().selectOne(getNamespace().concat(".getByUsername"), username);
		return user;
	}

	@Override
	public void LockUser(AdminUser user) {
		getSqlSession().update(getNamespace().concat(".lockUser"), user);
	}

	@Override
	public List<AdminUser> getSuperAdmin() {
		return getSqlSession().selectList(getNamespace().concat(".getSuperAdmin"));
	}

	private void saveRoles(AdminUser user) {
		getSqlSession().delete(getNamespace().concat(".deleteRoles"), user);
		if (user.hasRoles()) {
			getSqlSession().insert(getNamespace().concat(".saveRoles"), user);
		}
	}

	private void saveAuths(AdminUser user) {
		getSqlSession().delete(getNamespace().concat(".deleteAuths"), user);
		if (user.hasAuths()) {
			getSqlSession().insert(getNamespace().concat(".saveAuths"), user);
		}
	}

	@Override
	public boolean notExistUser(AdminUser user) {
		// TODO Auto-generated method stub
		Long count = getSqlSession().selectOne(getNamespace().concat(".count"), user);
		if (count > 0) {
			return false;
		}
		return true;
	}

}
