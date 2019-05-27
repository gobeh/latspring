
package com.sofwan.latspring.dao;

import com.sofwan.latspring.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UsersDao extends PagingAndSortingRepository<Users, String>{
    Page<Users> findByUsername(Users u, Pageable page);    
    

}
