package com.sofwan.latspring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HaloController {
    @RequestMapping("/halo")
    public Map <String, Object> halo(@RequestParam(name="nama", required = false) String nama){
        Map<String, Object> hasil = new HashMap<>();
        hasil.put("Nama", nama);
        hasil.put("Waktu", new Date());
        return hasil;
    }
}
