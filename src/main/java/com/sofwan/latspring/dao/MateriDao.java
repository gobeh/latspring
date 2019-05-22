
package com.sofwan.latspring.dao;

import com.sofwan.latspring.entity.Materi;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface MateriDao extends PagingAndSortingRepository<Materi, String>{
    
    @Query("select p from Materi p order by p.id")
    List<Materi> semuaMateri();
}
