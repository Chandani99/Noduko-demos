package com.noduco.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noduco.entity.Book;
import com.noduco.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {
    
	@Autowired
	private BookService bService;
	
	@PostMapping("/admin/add-book")
	public ResponseEntity<String> addBookHandler(@RequestBody Book book){
		String msg = bService.uploadBook(book);
		return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/admin/delete-book/{id}")
	public ResponseEntity<String> deleteBookByIdHandler(@PathVariable Long id){
		String msg = bService.deleteBookById(id);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	@GetMapping("/user/get-all-book")
	public ResponseEntity<List<Book>> getAllBooksHandler(){
		List<Book> allBooks= bService.getAllBooks();
		return new ResponseEntity<>(allBooks,HttpStatus.OK);
	}
	@GetMapping("/user/get-book/{id}")
	public ResponseEntity<Book> addBookHandler(@PathVariable Long id){
		Book book = bService.getBookById(id);
		return new ResponseEntity<>(book,HttpStatus.OK);
	}
	@GetMapping("/user/get-book-author")
	public ResponseEntity<List<Book>> addBookHandler(@PathVariable String name){
		List<Book> books= bService.getBookByAuthor(name);
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
 	
}
