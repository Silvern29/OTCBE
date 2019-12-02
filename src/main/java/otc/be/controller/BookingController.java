package otc.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import otc.be.entity.Booking;
import otc.be.repository.BookingRepository;

import java.util.Optional;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    public Iterable<Booking> getAllBookings() {
        return bookingRepository.findByOrderByIdAsc();
    }

    public Optional<Booking> getBookingById(Integer id) {
        return bookingRepository.findById(id);
    }

    public void create(Booking booking) {
        bookingRepository.save(booking);
        System.out.println("Jetzt sollte eine neue Buchung in der Tabelle Booking eingetragen worden sein.");
    }

    public Booking update(Booking booking) {
        Booking updatedBooking = bookingRepository.findById(booking.getId()).get();
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
        bookingRepository.save(updatedBooking);
        return updatedBooking;
    }

    public void deleteById(Integer id) {
        System.out.println("Nun sollte das Restaurant mit der ID " + id + " gelöscht worden sein.");
        bookingRepository.deleteById(id);
    }
}