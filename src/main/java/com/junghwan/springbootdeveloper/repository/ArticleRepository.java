package com.junghwan.springbootdeveloper.repository;

import com.junghwan.springbootdeveloper.domain.Article;
import com.junghwan.springbootdeveloper.repository.search.ArticleSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleSearch {

    @EntityGraph(attributePaths = {"imageSet"})
    @Query("SELECT a FROM Article a WHERE a.id = :id")
    Optional<Article> findByIdWithImages(Long id);
}