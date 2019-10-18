package com.sty.study2;


import com.sty.study2.model.Student;
import com.sty.study2.service.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface StudentDao extends BaseRepository<Student, Integer> {

}
