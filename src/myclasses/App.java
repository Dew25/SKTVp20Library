/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import facade.AuthorFacade;
import facade.BookFacade;
import facade.HistoryFacade;
import facade.ReaderFacade;
import interfaces.Keeping;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import tools.Singleton;


/**
 *
 * @author user
 */
public class App {
    private Scanner scanner = new Scanner(System.in);
    private ReaderFacade readerFacade;
    private BookFacade bookFacade;
    private HistoryFacade historyFacade;
    private AuthorFacade authorFacade;
    private Singleton singleton;
    public App(){
        singleton = Singleton.getInstance();
        init();
    }
    private void init(){
        readerFacade= new ReaderFacade(Reader.class);
        bookFacade = new BookFacade(Book.class);
        historyFacade = new HistoryFacade(History.class);
        authorFacade = new AuthorFacade(Author.class);
    }
    public void run(){
       String repeat = "r";
        do{
            System.out.println("Выберите номер задачи:");
            System.out.println("0: Закончить программу");
            System.out.println("1: Добавить книгу");
            System.out.println("2: Список книг");
            System.out.println("3: Добавить читателя");
            System.out.println("4: Список читателей");
            System.out.println("5: Выдать книгу читателю");
            System.out.println("6: Вернуть книгу");
            System.out.println("7: Список выданных книг");
            System.out.println("8: Добавить автора");
            System.out.println("9: Список авторов");
            System.out.println("10: Изменить данные книги");
            int task = scanner.nextInt(); scanner.nextLine();
            switch (task) {
                case 0:
                    repeat="q";
                    System.out.println("Пока!");
                    break;
                case 1:
                    addBook();
                    break;
                case 2:
                    printListBooks();
                    break;
                case 3:
                    addReader();
                    break;
                case 4:
                    printListReaders();
                    break;
                case 5:
                    addHistory();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    printListGivenBooks();
                    break;
                case 8:
                    addAuthor();
                    break;
                case 9:
                    printListAuthors();
                    break;
                case 10:
                    changeBook();
                    break;
                default:
                    System.out.println("Выберите цифру из списка!");;
            }
        }while("r".equals(repeat));
    }
    private void addBook(){
        System.out.println("--- Добавление книги ---");
        if(isQuit()){
            return;
        }
        Book book = new Book();
        Set<Integer> setNumbersAuthors = printListAuthors();
        if(setNumbersAuthors.isEmpty()){
            return;
        }
        System.out.println("Если есть авторы книги в списке выберите 1, если нет, выберите 2");
        if(getNumber() != 1){
            System.out.println("Добавьте автора!");
            return;
        }
        System.out.print("Сколько авторов у книги: ");
        int countAuthors = getNumber();
        List<Author> authorsBook = new ArrayList<>();
        for (int i = 0; i < countAuthors; i++) {
            System.out.println("Введите номер автора "+(i+1));
            int numberAuthor = insertNumber(setNumbersAuthors);
            authorsBook.add(authorFacade.find((long)numberAuthor));
        }
        book.setAuthors(authorsBook);
        System.out.print("Введите название книги: ");
        book.setBookName(scanner.nextLine());
        System.out.print("Введите год издания книги: ");
        book.setPublishedYear(getNumber());
        System.out.print("Введите количество экземпляров книги: ");
        book.setQuantity(getNumber());
        book.setCount(book.getQuantity());
        System.out.println("---------------------");
        bookFacade.create(book);
    }
    private void addReader(){
        if(isQuit()){
            return;
        }
        System.out.println("--- Добавление читателя ---");
        Reader reader = new Reader();
        System.out.println("Имя читателя");
        reader.setFirstname(scanner.nextLine());
        System.out.println("Фамилия читателя");
        reader.setLastname(scanner.nextLine());
        System.out.println("Телефон читателя");
        reader.setPhone(scanner.nextLine());
        System.out.println("---------------------");
        readerFacade.create(reader);
    }

    private void addHistory() {
        if (isQuit()) {
            return;
        }
        System.out.println("--- Выдача книги ---");
        History history = new History();
        System.out.println("Выберите номер книги: ");
        Set<Integer> setNumbersBooks = printListBooks();
        if(setNumbersBooks.isEmpty()){
            return;
        }
        int numberBook = insertNumber(setNumbersBooks);
        Set<Integer> setNumbersReaders = printListReaders();
        if(setNumbersReaders.isEmpty()){
            return;
        }
        System.out.println("Выберите номер читателя: ");
        int numberReader = insertNumber(setNumbersReaders);  
        Book book = bookFacade.find((long)numberBook);
        history.setBook(book);
        history.setReader(readerFacade.find((long)numberReader));
        Calendar c = new GregorianCalendar();
        history.setGivenDate(c.getTime());
        book.setCount(book.getCount() - 1);
        bookFacade.edit(book);
        historyFacade.create(history);
        System.out.println("Книга "+history.getBook().getBookName()
                            +" выдана читателю "+history.getReader().getFirstname()
                            +" " +history.getReader().getLastname()
        );
        System.out.println("-------------------");
        
    }

    private Set<Integer> printListBooks() {
        System.out.println("--- Список книг ---");
        Set<Integer> setNumbersBooks = new HashSet<>();
        List<Book> books = bookFacade.findAll();
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i) != null && books.get(i).getCount() > 0
                    ){
                System.out.printf("%d. %s. %s. %d. В наличии екземпляров: %d%n"
                        ,books.get(i).getId()
                        ,books.get(i).getBookName()
                        ,Arrays.toString(books.get(i).getAuthors().toArray())
                        ,books.get(i).getPublishedYear()
                        ,books.get(i).getCount()
                );
                setNumbersBooks.add(books.get(i).getId().intValue());
            }else if(books.get(i) != null && books.get(i).getQuantity()>0){
                System.out.printf("%1$d. (Книга читается до: %5$s) %2$s. %4$d. %3$s%n"
                        ,books.get(i).getId()
                        ,books.get(i).getBookName()
                        ,Arrays.toString(books.get(i).getAuthors().toArray())
                        ,books.get(i).getPublishedYear()
                        ,getReturnDate(books.get(i))
                );
            }
        }
        System.out.println("-------------------");
        return setNumbersBooks;
    }
    private Set<Integer> printListAllBooks() {
        System.out.println("--- Список книг ---");
        Set<Integer> setNumbersBooks = new HashSet<>();
        List<Book> books = bookFacade.findAll();
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i) != null && books.get(i).getCount() >= 0
                    ){
                System.out.printf("%d. %s. %s. %d. В наличии екземпляров: %d%n"
                        ,i+1
                        ,books.get(i).getBookName()
                        ,Arrays.toString(books.get(i).getAuthors().toArray())
                        ,books.get(i).getPublishedYear()
                        ,books.get(i).getCount()
                );
                setNumbersBooks.add(i+1);
            }else if(books.get(i) != null){
                System.out.printf("%1$d. (Книга читается до: %5$s) %2$s. %4$d. %3$s%n"
                        ,i+1
                        ,books.get(i).getBookName()
                        ,Arrays.toString(books.get(i).getAuthors().toArray())
                        ,books.get(i).getPublishedYear()
                        ,getReturnDate(books.get(i))
                );
            }
        }
        System.out.println("-------------------");
        return setNumbersBooks;
    }
    private String getReturnDate(Book book){
        History history = historyFacade.find(book);
        if(history == null) return "";
        Date givenDate = history.getGivenDate();
        LocalDate localGivenDate = givenDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        localGivenDate = localGivenDate.plusDays(14);
        return localGivenDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    private Set<Integer> printListReaders() {
        System.out.println("--- Список читателей ---");
        Set<Integer> setNumbersReaders = new HashSet<>();
        List<Reader> readers = readerFacade.findAll();
        for (int i = 0; i < readers.size(); i++) {
            if(readers.get(i) != null){
                System.out.printf("%d. %s %s. Телефон: %s%n"
                        ,readers.get(i).getId()
                        ,readers.get(i).getFirstname()
                        ,readers.get(i).getLastname()
                        ,readers.get(i).getPhone()
                );
               setNumbersReaders.add(readers.get(i).getId().intValue());
            }
        }
        System.out.println("-------------------");
        return setNumbersReaders;
    }

    private void returnBook() {
        System.out.println("--- Возврат книги ---");
        if(isQuit()){
            return;
        }
        Set<Integer> setNumbersGivenBooks = printListGivenBooks();
        if(setNumbersGivenBooks.isEmpty()){
            return;
        }
        System.out.print("Выберите номер возврщаемой книги: ");
        int numberHistory = insertNumber(setNumbersGivenBooks);
        Calendar c = new GregorianCalendar();
        History history = historyFacade.find((long)numberHistory);
        history.setReturnedDate(c.getTime());
        Book book = bookFacade.find(history.getBook().getId());
        book.setCount(book.getCount() + 1);
        bookFacade.edit(book);
        historyFacade.edit(history);
        
        System.out.println("Книга "
                +history.getBook().getBookName()
                +" возвращена в библиотеку"
        );
        System.out.println("-------------------");
    }

    private Set<Integer> printListGivenBooks() {
        System.out.println("Список читаемых книг:");
        Set<Integer> setNumbersBook = new HashSet<>();
        List<History> historiesGivenBooks = historyFacade.findGivenBooks();
        for (int i = 0; i < historiesGivenBooks.size(); i++) {
            System.out.println(historiesGivenBooks.get(i).getId()+". Книгу "
                    +historiesGivenBooks.get(i).getBook().getBookName()
                    +" читает "+historiesGivenBooks.get(i).getReader().getFirstname()
                    +" "+historiesGivenBooks.get(i).getReader().getLastname()
            );
            setNumbersBook.add(historiesGivenBooks.get(i).getId().intValue());
        }
        if(setNumbersBook.isEmpty()){
            System.out.println("Нет читаемых книг!");
            System.out.println("-------------------");
        }
        return setNumbersBook;
    }

    private int getNumber() {
        int number = 0;
        do{
            String strNumber = scanner.nextLine();
            try {
                number = Integer.parseInt(strNumber);
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Попробуй еще раз: ");
            }
        }while(true);
    }

    private int insertNumber(Set<Integer> setNumbers) {
        int number;
        do{
            number = getNumber();
            if(setNumbers.contains(number)){
                return number;
            }
            System.out.println("Попробуй еще: ");
        }while(true);
    }

    private Set<Integer> printListAuthors() {
        Set<Integer> numbersAuthors = new HashSet<>();
        List<Author> authors = authorFacade.findAll();
        if(authors.isEmpty()){
            System.out.println("Список авторов пуст, добавьте авторов книги.");
            return new HashSet<>();
        }else{
            System.out.println("Список авторов:");
        }
        for (int i = 0; i < authors.size(); i++) {
            System.out.printf("%d. %s %s%n"
                    ,authors.get(i).getId()
                    ,authors.get(i).getFirstname()
                    ,authors.get(i).getLastname()
            ); 
            numbersAuthors.add(authors.get(i).getId().intValue());
        }
        return numbersAuthors;
    }

    private void addAuthor() {
        System.out.println("---- Добавить автора -----");
        if(isQuit()){
            return;
        }
        Author author = new Author();
        System.out.println("Имя автора: ");
        author.setFirstname(scanner.nextLine());
        System.out.println("Фамилия автора: ");
        author.setLastname(scanner.nextLine());
        authorFacade.create(author);
    }

    private void changeBook() {
        System.out.println("----- Изменение данных книги ------");
        if(isQuit()){
            return;
        }
        Set<Integer> changeNumbers = new HashSet<>();
        changeNumbers.add(1);
        changeNumbers.add(2);
        Set<Integer> setNumbersBooks = printListAllBooks();
        if(setNumbersBooks.isEmpty()){
            return;
        }
        System.out.println("Введите номер книги: ");
        int numberBook = insertNumber(setNumbersBooks);
        Book book = bookFacade.find((long)numberBook);
        System.out.println("Название книги: " + book.getBookName());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        int change = insertNumber(changeNumbers);
        if(1 == change){
            System.out.println("Введите новое название книги: ");
            book.setBookName(scanner.nextLine());
        }
        System.out.println("Год издания книги: " + book.getPublishedYear());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if(1 == change){
            System.out.println("Введите новый год издания книги: ");
            book.setPublishedYear(getNumber());
        }
        System.out.println("Количество экземпляров книги: " + book.getQuantity());
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if(1 == change){
            System.out.println("Введите новое количество экземпляров книги: ");
            int oldQuantity = book.getQuantity();
            int oldCount = book.getCount();
            int newQuantity;
            do{
                newQuantity = getNumber();
                //if(newQuantity >= oldQuantity - oldCount){
                if(newQuantity >= oldQuantity - oldCount){
                    break;
                }
                System.out.println("Книг должно быть больше.");
            }while(true);
            int newCount = oldCount + (newQuantity - oldQuantity);
            
            book.setQuantity(newQuantity);
            book.setCount(newCount);
        }
        System.out.println("Авторы книги: ");
        for (int i = 0; i < book.getAuthors().size(); i++) {
            System.out.printf("%d. %s %s%n"
                    ,i+1
                    ,book.getAuthors().get(i).getFirstname()
                    ,book.getAuthors().get(i).getLastname()
            );
        }
        System.out.println("Если нужно изменить, нажми 1, если пропустить, нажми 2");
        change = insertNumber(changeNumbers);
        if(1 == change){
            book.getAuthors().clear();
            Set<Integer> setNumbersAuthors = printListAuthors();
            if(setNumbersAuthors.isEmpty()){
                return;
            }
            System.out.println("Введите новый новое количество авторов: ");
            int countAuthors = getNumber();
            for (int i = 0; i < countAuthors; i++) {
                System.out.println("Введите автора "+(i+1)+": ");
                int numberAuthor = insertNumber(setNumbersAuthors);
                book.getAuthors().add(authorFacade.find((long)numberAuthor));
            }
        }
        bookFacade.edit(book);
    }
    private boolean isQuit(){
        System.out.println("Чтобы закончить задачу нажми \"q\"");
        String q = scanner.nextLine();
        if("q".equals(q)){
            return true;
        }else{
            return false;
        }
    }
}
