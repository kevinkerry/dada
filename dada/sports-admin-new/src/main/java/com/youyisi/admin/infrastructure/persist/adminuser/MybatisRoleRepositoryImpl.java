package com.youyisi.admin.infrastructure.persist.adminuser;

import org.springframework.stereotype.Repository;

import com.youyisi.admin.domain.adminuser.Role;
import com.youyisi.admin.domain.adminuser.RoleRepository;
import com.youyisi.mybatis.MybatisOperations;


@Repository
public class MybatisRoleRepositoryImpl extends MybatisOperations<Integer, Role> implements RoleRepository {

	@Override
	public Role save(Role entity) {
		Role role = super.save(entity.beforeCreate());
		saveAuth(entity);
		return role;
	}

	@Override
	public int update(Role entity) {
		int number = super.update(entity.beforeCreate());
		saveAuth(entity);
		return number;
	}

	@Override
    public int delete(Role entity) {
	    getSqlSession().delete(getNamespace().concat(".deleteAuths"),entity);
        return super.delete(entity);
    }

    private void saveAuth(Role entity){
		getSqlSession().delete(getNamespace().concat(".deleteAuths"),entity);
		if(entity.hasAuth()){
			getSqlSession().insert(getNamespace().concat(".saveAuths"), entity);
		}
	}

    @Override
    public boolean isExistRole(Role role) {
        Long count = getSqlSession().selectOne(getNamespace().concat(".count"), role);
        if(count>0){
            return true;
        }
        return false;
    }
	
}	
