package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import otc.be.entity.RestaurantTable;
import otc.be.repository.RestaurantTableRepository;

import java.util.Optional;

@Controller
public class RestaurantTableController {
    @Autowired
    RestaurantTableRepository restaurantTableRepository;


    public Iterable<RestaurantTable> getAllRestaurantTables() {
        return restaurantTableRepository.findByOrderByIdAsc();
    }

    public Optional<RestaurantTable> getRestaurantTableById(Integer id) {
        return restaurantTableRepository.findById(id);
    }

    public void create(RestaurantTable restaurantTable) {
        restaurantTableRepository.save(restaurantTable);
        System.out.println("Jetzt sollte ein neuer Tisch in der Tabelle RestaurantTable eingetragen worden sein.");
    }

    public RestaurantTable update(RestaurantTable restaurantTable) {
        RestaurantTable updatedRestaurantTable = restaurantTableRepository.findById(restaurantTable.getId()).get();; //derweil zur Sicherstellung der Grund-Funktionalität
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
        restaurantTableRepository.save(updatedRestaurantTable);
        return updatedRestaurantTable;
    }

    public void deleteById(Integer id) {
        System.out.println("Nun sollte der Tisch mit der ID " + id + " gelöscht worden sein.");
        restaurantTableRepository.deleteById(id);
    }

}
