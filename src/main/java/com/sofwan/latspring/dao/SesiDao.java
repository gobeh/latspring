package com.sofwan.latspring.dao;

import com.sofwan.latspring.entity.Materi;
import com.sofwan.latspring.entity.Sesi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface SesiDao extends PagingAndSortingRepository<Sesi, String> {
    Page<Sesi> findByMateri(Materi m, Pageable page);

    @Query("select x from Sesi x where x.mulai >= :m " +
            "and x.sampai < :s and x.materi.kode = :k " +
            "order by  x.mulai desc")
    Page<Sesi> cariBerdasarkanTanggalMulaiDanKodeMateri(
            @Param("m") Date mulai,
            @Param("s") Date sampai,
            @Param("k") String kode,
            Pageable page);
}
