package com.noduco.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.noduco.model.Book;

import jakarta.annotation.PostConstruct;

@Service
public class BookService {
public List<Book> allBooks;
	
	
    public BookService(List<Book> allBooks) {
    	this.allBooks = allBooks;
    }
    
    @PostConstruct
    public void returnFewBooks() {
    	allBooks.add(new Book((long) 1,"Java","ABC"));
    	allBooks.add(new Book((long) 2,"C & C++","XYZ"));
    	allBooks.add(new Book((long) 3,"Applied Mathmetics","Veer"));
    } 
    
	public String uploadBook(Book book) {
		allBooks.add(book);
		return book+" : Has been uplaoded successfully";
		
	}
	
	public List<Book> getAllBooks(){
		return allBooks;
	}
	
	public Book getBookById(Long id) {
		for(Book book: allBooks) {
			if(book.getId()== id) {
				return book;
			}
		}
		return null;
	}
	
	public List<Book> getBookByAuthor(String name){
		
		List<Book> booksByAuthor = allBooks.stream().filter(book->book.getAuthor().equalsIgnoreCase(name)).collect(Collectors.toList());
		return booksByAuthor;
	}
	
	public String deleteBookById(Long id) {
		int index = -1;
		for(int i=0; i<allBooks.size(); i++) {
			
			if(allBooks.get(i).getId()== id) {
				index = i;
				break;
			}
		}
		if(index != -1) {
			allBooks.remove(index);
			return "  book is deleted successfully";
		}
		return " : this book is not exists";
		
	}

}
