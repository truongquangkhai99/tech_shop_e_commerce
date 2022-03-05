package com.techshop.admin.user.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.techshop.common.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	//get email use HQL
	@Query("SELECT u FROM User u WHERE u.email =:email")
	public User getUserbyEmail(@Param("email") String email);
	
	public Long countById(Integer id);
	
	@Query("UPDATE User u SET u.enabled = ?2 WHERE u.id = ?1")
	@Modifying
	public void updateEnabledStatus(Integer id, boolean enabled);
}
