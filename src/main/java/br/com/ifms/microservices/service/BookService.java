package br.com.ifms.microservices.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifms.microservices.dto.BookDTO;
import br.com.ifms.microservices.exception.InternalServerError;
import br.com.ifms.microservices.exception.InvalidValueException;
import br.com.ifms.microservices.exception.NotFoundException;
import br.com.ifms.microservices.model.Book;
import br.com.ifms.microservices.repository.BookRepository;
import br.com.ifms.microservices.utils.Utils;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;


    public long getCount() {
        return this.bookRepository.count();
    }


    public List<Book> getAll() {
        if (getCount() == 0) {
            throw new NotFoundException("There are no books in the database");
        }
        return this.bookRepository.findAll();
    }


    public Book getOneById(long id) {
        Optional<Book> book = this.bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new NotFoundException("Book not found in the database");
        }
    }   


    public List<Book> getByName(String name) {
        if (getCount() == 0) {
            throw new NotFoundException("There are no books in the database");
        }
        return this.bookRepository.findByName(name);
    }


    public Book save(BookDTO bookDTO) {
        try {
            Book book = new Book();
            BeanUtils.copyProperties(bookDTO, book);
            return this.bookRepository.save(book);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in saving the book");
        }
    }

    
    public String delete(long id) {
        Optional<Book> book = this.bookRepository.findById(id);
        if (book.isPresent()) {
            this.bookRepository.deleteById(id);
            return "The book was deleted successfully";
        } else {
            throw new NotFoundException("Book not found in the database");
        }
    }


    public Book update(BookDTO bookDTO) {
        if (bookDTO.getId() == 0) {
            throw new InvalidValueException("Missing parameter id in request body");
        }
        Book book = getOneById(bookDTO.getId());
        String[] ignoreAttributes = Utils.getNullAttributes(bookDTO);
        BeanUtils.copyProperties(bookDTO, book, ignoreAttributes);  
        try {
            return this.bookRepository.save(book);
        } catch (Exception e) {
            throw new InternalServerError("An error occurred in updating the book");
        }
    }
}
