package com.sty.study2;


import com.sty.study2.model.Student;
import com.sty.study2.service.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends BaseRepository<Student, Integer> {
    Long countByName(String name);
    @Override
    Page<Student> findAll(Pageable pageable);

}
