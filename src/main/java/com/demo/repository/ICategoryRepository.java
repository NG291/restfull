package com.demo.repository;

import com.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = " call deleteCategory_id(: id)")
    void  deleteByIdCategory(@Param("id") Long id);
}
