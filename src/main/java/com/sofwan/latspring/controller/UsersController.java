package com.sofwan.latspring.controller;

import com.sofwan.latspring.dao.UsersDao;
import com.sofwan.latspring.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UsersDao ud;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Page<Users> cariUsers(Pageable page) {
        return ud.findAll(page);
    }
}
