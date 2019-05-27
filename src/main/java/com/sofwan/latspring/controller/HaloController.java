package com.sofwan.latspring.controller;

import com.sofwan.latspring.dao.UsersDao;
import com.sofwan.latspring.entity.Users;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HaloController {

    @Autowired
    private UsersDao ud;

    @RequestMapping("/haloRest")
    public Map<String, Object> haloHtml(@RequestParam(name = "nama", required = false) String nama) {
        Map<String, Object> hasil = new HashMap<>();
        hasil.put("Nama", nama);
        hasil.put("Waktu", new Date());
        return hasil;
    }

    @RequestMapping("/halo")
    public Map<String, Object> haloThy(@RequestParam(name = "nama", required = false) String nama, Users u) {
        
        
        Map<String, Object> hasil = new HashMap<>();
        hasil.put("Nama", nama);
        hasil.put("nama", u.getUsername());
        hasil.put("Waktu", new Date());
        return hasil;
    }
}
