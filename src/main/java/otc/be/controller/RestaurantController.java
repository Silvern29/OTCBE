package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import otc.be.entity.Restaurant;
import otc.be.repository.RestaurantRepository;

import java.util.Optional;

@CrossOrigin
@Controller
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    public Iterable<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
        //sortByIdAsc());
    }

//    private Sort sortByIdAsc() {
//        return new Sort(Sort.Direction.ASC, "id");
//    }

    public Optional<Restaurant> getRestaurantById(Integer id) {
        return restaurantRepository.findById(id);
    }

    public void create(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        System.out.println("Jetzt sollte ein neues Restaurant in der Tabelle Restaurant eingetragen worden sein.");
    }

    public Restaurant update(Restaurant restaurant) {
        Restaurant updatedRestaurant = restaurant; //derweil zur Sicherstellung der Grund-Funktionalität
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
       restaurantRepository.save(updatedRestaurant);
        return updatedRestaurant;
    }

    public void deleteById(Integer id) {
        System.out.println("Nun sollte das Restaurant mit der ID " + id + " gelöscht worden sein.");
        restaurantRepository.deleteById(id);
    }
}