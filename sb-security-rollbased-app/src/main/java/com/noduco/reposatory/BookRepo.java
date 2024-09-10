package com.noduco.reposatory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noduco.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long>{

}
