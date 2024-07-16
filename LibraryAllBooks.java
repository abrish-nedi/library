package gsu.edu.library.entity;

@Table(name = "booktable")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
    private String author;
    private String genre;
    private Integer yearPublished;
    private Integer copies; 
}