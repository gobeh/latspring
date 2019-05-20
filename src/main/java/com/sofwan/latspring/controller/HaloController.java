package com.sofwan.latspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HaloController {
    @RequestMapping("/haloRest")
    public Map <String, Object> haloHtml(@RequestParam(name="nama", required = false) String nama){
        Map<String, Object> hasil = new HashMap<>();
        hasil.put("Nama", nama);
        hasil.put("Waktu", new Date());
        return hasil;
    }
    @RequestMapping("/halo")
    public Map <String, Object> haloThy(@RequestParam(name="nama", required = false) String nama){
        Map<String, Object> hasil = new HashMap<>();
        hasil.put("Nama", nama);
        hasil.put("Waktu", new Date());
        return hasil;
    }
}
