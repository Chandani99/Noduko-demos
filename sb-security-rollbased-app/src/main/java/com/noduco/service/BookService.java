package com.noduco.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noduco.entity.Book;
import com.noduco.reposatory.BookRepo;

@Service
public class BookService {
	
	@Autowired
	private BookRepo bRepo;
	
	public String uploadBook(Book book) {
		bRepo.save(book);
		return book+" : Has been uplaoded successfully";
		
	}
	
	public List<Book> getAllBooks(){
		return bRepo.findAll();
	}
	
	public Book getBookById(Long id) {
		return bRepo.findById(id).get();
	}
	
	public List<Book> getBookByAuthor(String name){
		List<Book> allBooks = bRepo.findAll();
		List<Book> booksByAuthor = allBooks.stream().filter(book->book.getAuthor().equalsIgnoreCase(name)).collect(Collectors.toList());
		return booksByAuthor;
	}
	
	public String deleteBookById(Long id) {
		Book book = bRepo.findById(id).get();
		bRepo.deleteById(id);
		return book+" : this book is deleted successfully";
		
	}

}
