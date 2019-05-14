package com.sofwan.latspring.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Sesi {

    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(strategy = "uuid2", name = "uuid")
    private String id;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date mulai;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date sampai;

    @ManyToOne
    @JoinColumn(name="id_materi", nullable = false)
    private Materi materi;

    @ManyToMany
    @JoinTable(
            name = "peserta_pelatihan",
            joinColumns = @JoinColumn(name = "id_sesi"),
            inverseJoinColumns = @JoinColumn(name = "id_peserta")
    )
    private List<Peserta> daftarPeserta = new ArrayList<>();

}
