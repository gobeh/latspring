package com.sofwan.latspring.controller;

import com.sofwan.latspring.dao.PesertaDao;
import com.sofwan.latspring.entity.Peserta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/peserta")
public class PesertaHtmlController {
    @Autowired
    private PesertaDao pd;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/list")
    public void daftarPeserta(Model m){
        m.addAttribute("daftarPeserta",pd.findAll());
    }

    @RequestMapping("/hapus")
    public String hapusPeserta(@RequestParam("id") Peserta id){
        pd.delete(id);
        return "redirect:list";
    }

    @RequestMapping(value="/form", params = "id", method = RequestMethod.GET)
    public String tampilkanForm(@RequestParam(value = "id", required = false) String id, Model m){
        //default value untuk m
        m.addAttribute("peserta", new Peserta());
        if(id!=null && !id.isEmpty()){
            Optional<Peserta> p=pd.findById(id);
            if(id!=null){
                m.addAttribute("peserta", p);
            }
        }
        return "/peserta/form";
    }

    @RequestMapping(value="/form", method = RequestMethod.POST)
    public String prosesForm(@Valid Peserta peserta, BindingResult errors){
        if(errors.hasErrors()){
            return "/peserta/form";
        }
            pd.save(peserta);
            return "redirect:list";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewPeserta(@RequestParam(value = "id", required = false) String id, Model m){
        m.addAttribute("peserta", new Peserta());

        if(id!=null && !id.isEmpty()){
            Optional<Peserta> p = pd.findById(id);
            if(p!=null){
                m.addAttribute("nama", p.get().getNama());
                m.addAttribute("email",p.get().getEmail());
                m.addAttribute("tanggalLahir",p.get().getTanggalLahir());
            }
        }
        return "/peserta/view";
    }



}
