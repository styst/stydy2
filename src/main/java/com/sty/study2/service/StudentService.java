package com.sty.study2.service;

import com.sty.study2.StudentDao;
import com.sty.study2.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.text.html.Option;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentDao studentRepository;
    public List<Student> list() {
       return studentRepository.findAll();
    }
    public Student getById(Integer id) {
        return studentRepository.getOne(id);
    }
    public String test() {

        Specification<Student> specification = new Specification<Student>() {

            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate condition = criteriaBuilder.gt(root.get("id"), 3);
                return criteriaBuilder.and(condition);
            }
        };
        List<Student> all = studentRepository.findAll(specification);
        return all.toString();
    }
    public String test2() {
        Long count = studentRepository.countByName("赵丽颖");
        return count.toString();
    }
    public Page<Student> test3(PageRequest pageable) {
        return studentRepository.findAll((Specification<Student>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("age")));
            predicates.add(criteriaBuilder.gt(root.get("id"), 0));
            Predicate[] array = new Predicate[predicates.size()];
            return criteriaBuilder.and(predicates.toArray(array));
        }, pageable);
    }



}
