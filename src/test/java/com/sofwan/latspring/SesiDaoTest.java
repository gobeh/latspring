package com.sofwan.latspring;

import com.sofwan.latspring.dao.SesiDao;
import com.sofwan.latspring.entity.Materi;
import com.sofwan.latspring.entity.Sesi;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"/data/peserta.sql", "/data/materi.sql", "/data/sesi.sql"}
)
public class SesiDaoTest {
   @Autowired
   private SesiDao sd;

   @Test
   public void testCariByMateri() {
       Materi m = new Materi();
       m.setId("aa6");

       PageRequest page = PageRequest.of(0,5);

       Page<Sesi> hasilQuery = sd.findByMateri(m, page);
       Assert.assertEquals(2L,hasilQuery.getTotalElements());

       Assert.assertFalse(hasilQuery.isEmpty());
       Sesi s = hasilQuery.getContent().get(0);
       Assert.assertNotNull(s);
       Assert.assertEquals("Java Fundamental", s.getMateri().getNama());
   }

    @Test
    public void tesCariBerdasarkanTanggalMulaiDanKodeMateri()throws Exception{

       PageRequest page = PageRequest.of(0,5);
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       Date sejak = formatter.parse("2015-01-01");
       Date sampai = formatter.parse("2015-01-06");

       Page<Sesi> hasil = sd.cariBerdasarkanTanggalMulaiDanKodeMateri(
               sejak,
               sampai,
               "JF-002",
               page
       );

       Assert.assertEquals(1L,hasil.getTotalElements());
       Assert.assertFalse(hasil.getContent().isEmpty());

       Sesi s = hasil.getContent().get(0);
       Assert.assertEquals("Java Web", s.getMateri().getNama());

    }
}
