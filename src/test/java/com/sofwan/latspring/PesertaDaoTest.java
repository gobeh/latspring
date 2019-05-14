package com.sofwan.latspring;

import com.sofwan.latspring.dao.PesertaDao;
import com.sofwan.latspring.entity.Peserta;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


import javax.sql.DataSource;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PesertaDaoTest {
   @Autowired
   private PesertaDao pd;

   @Autowired
   private DataSource ds;

   @Test
   public void tesInsert()throws SQLException {
       Peserta p = new Peserta();
       p.setNama("Ahmad");
       p.setEmail("ahmad@mail.com");
       p.setTanggalLahir(new Date());

       pd.save(p);

       String sql ="select count(*) as jumlah from peserta where email ='ahmad@mail.com'";

       try (Connection c = ds.getConnection()) {
           ResultSet rs = c.createStatement().executeQuery(sql);
           Assert.assertTrue(rs.next());

           Long jumlahRow = rs.getLong("jumlah");
           Assert.assertEquals(1L,jumlahRow.longValue());
       }

   }
}
