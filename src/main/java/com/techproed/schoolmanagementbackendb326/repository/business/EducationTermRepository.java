package com.techproed.schoolmanagementbackendb326.repository.business;

import com.techproed.schoolmanagementbackendb326.entity.concretes.business.EducationTerm;
import com.techproed.schoolmanagementbackendb326.entity.enums.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationTermRepository extends JpaRepository<EducationTerm, Long> {

    @Query("SELECT (count (e) > 0) FROM EducationTerm e WHERE e.term =? 1 and EXTRACT (year FROM e.startDate) =? 2 ")
    boolean existByTermAndYear(Term term, int year);

    //girilen yilda var olan education term'leri geri donduren sorgu
    @Query("SELECT e FROM EducationTerm e WHERE EXTRACT(year from e.startDate) =? 1 ")
    List<EducationTerm> findByYear(int year);

}
