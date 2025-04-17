package com.blog.blogging_application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blogging_application.entities.Role;

public interface RoleRepo  extends JpaRepository<Role, Integer>{

}
