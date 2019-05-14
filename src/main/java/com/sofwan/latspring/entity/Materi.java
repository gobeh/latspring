package com.sofwan.latspring.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="m_materi")
public class Materi {
    @Id @GeneratedValue(generator = "uuid")
    @GenericGenerator(strategy = "uuid2", name = "uuid")
    private String id;
    @Column(nullable = false, unique = true, length = 10)
    private String kode;
    @Column(nullable = false)
    private String nama;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "materi"
    )
    private List<Sesi> daftarSesi = new ArrayList<>();

}
