package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.entity.User;
import otc.be.repository.UserRepository;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void create(User user) {
        userRepository.save(user);
        System.out.println("Jetzt sollte ein neuer User in der Tabelle User eingetragen worden sein.");
    }

    public User update(User user) {
        User updatedUser = user; //derweil zur Sicherstellung der Grund-Funktionalität
        //aus dem book herauskopiert - kann in alle himmelsrichtungen hin geändert werden
//        Restaurant updatedRestaurant = restaurantRepository.findById(restaurant.getId().get());
//        if (!book.getTitle().equals("")) updatedBook.setTitle(book.getTitle());
//        if (book.getIdAuthor() > 0) updatedBook.setIdAuthor(book.getIdAuthor());
//        if (book.getIdCategory() > 0) updatedBook.setIdCategory((book.getIdCategory()));
//        if (book.getIsbn() > 0) updatedBook.setIsbn(book.getIsbn());
//        if (book.getFsk() > 0) updatedBook.setFsk(book.getFsk());
//        if (!book.getPublisher().equals("")) updatedBook.setPublisher(book.getPublisher());
//        if (!book.getEdition().equals("")) updatedBook.setEdition(book.getEdition());
//        if (!book.getFirstEdition().equals("")) updatedBook.setFirstEdition(book.getFirstEdition());
//        if (book.getAmountPages() > 0) updatedBook.setAmountPages(book.getAmountPages());
//        if (!book.getLanguage().equals("")) updatedBook.setLanguage(book.getLanguage());
//        if (book.getIdRow() > 0) updatedBook.setIdRow(book.getIdRow());
//        if (book.getIdColumn() > 0) updatedBook.setIdColumn(book.getIdColumn());
//        System.out.println("Nun sollte das Buch mit der ID " + book.getIdBook() + " geändert worden sein.");
//        //bookRepository.updateBook(book.getIdBook(), updatedBook.getTitle(), updatedBook.getIdAuthor(), updatedBook.getIdCategory(), updatedBook.getIsbn(), updatedBook.getFsk(), updatedBook.getPublisher(), updatedBook.getEdition(), updatedBook.getFirstEdition(), updatedBook.getAmountPages(), updatedBook.getLanguage(), updatedBook.getIdRow(), updatedBook.getIdColumn());
        userRepository.save(updatedUser);
        return updatedUser;
    }

    public void deleteById(Integer id) {
        System.out.println("Nun sollte der User mit der ID " + id + " gelöscht worden sein.");
        userRepository.deleteById(id);
    }

}
