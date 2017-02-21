package com.youyisi.admin.infrastructure.persist.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.user.User;
import com.youyisi.admin.domain.user.UserRepository;
import com.youyisi.mybatis.MybatisOperations;


@Repository
public class MybatisUserRepositoryImpl  extends MybatisOperations<Integer, User> implements UserRepository{

	
	@Override
	public User save(User user) {
		user = super.save(user.beforeCreate());
		saveRoles(user);
		saveAuths(user);
		return user;
	}

	@Override
	public int update(User user) {
		int nummber = super.update(user.beforeCreate());
		saveRoles(user);
		saveAuths(user);
		return nummber;
	}

	@Override
    public int delete(User user) {
	    getSqlSession().delete(getNamespace().concat(".deleteRoles"), user);
	    getSqlSession().delete(getNamespace().concat(".deleteAuths"),user);
        return super.delete(user);
    }

    @Override
	public User getByUsername(String username) {
		User user = (User)getSqlSession().selectOne(getNamespace().concat(".getByUsername"), username);
		return user;
	}
	

	@Override
	public void LockUser(User user) {
		getSqlSession().update(getNamespace().concat(".lockUser"), user);
	}

	@Override
	public List<User> getSuperAdmin() {
		return getSqlSession().selectList(getNamespace().concat(".getSuperAdmin"));
	}
	
	private void saveRoles(User user){
		getSqlSession().delete(getNamespace().concat(".deleteRoles"), user);
		if(user.hasRoles()){
			getSqlSession().insert(getNamespace().concat(".saveRoles"), user);
		}
	}
	
	private void saveAuths(User user){
		getSqlSession().delete(getNamespace().concat(".deleteAuths"),user);
		if(user.hasAuths()){
			getSqlSession().insert(getNamespace().concat(".saveAuths"), user);
		}
	}

	@Override
	public boolean notExistUser(User user) {
		// TODO Auto-generated method stub
		Long count = getSqlSession().selectOne(getNamespace().concat(".count"), user);
		if(count>0){
			return false;
		}
		return true;
	}
}
