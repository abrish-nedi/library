@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    // Getters and Setters
}

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    // Getters and Setters
}

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    // Getters and Setters
}



Step 2
Create the Repository

public interface BookRepository extends JpaRepository<Book, Long> {}

public interface UserRepository extends JpaRepository<User, Long> {}

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUserIdAndReturnDateIsNull(Long userId);
}
Create the Service

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void returnBook(Long rentalId) {
        Optional<Rental> rentalOpt = rentalRepository.findById(rentalId);
        if (rentalOpt.isPresent()) {
            Rental rental = rentalOpt.get();
            rental.setReturnDate(LocalDateTime.now());
            rentalRepository.save(rental);
            sendReturnNotification(rental.getUser());
        }
    }

    private void sendReturnNotification(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Book Return Confirmation");
        message.setText("Your book has been successfully returned.");
        mailSender.send(message);
    }
}
Create the Controller

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PutMapping("/{rentalId}/return")
    public ResponseEntity<Void> returnBook(@PathVariable Long rentalId) {
        rentalService.returnBook(rentalId);
        return ResponseEntity.ok().build();
    }
}
Update the Frontend
