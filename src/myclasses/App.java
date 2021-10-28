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
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author user
 */
public class App {
    private Scanner scanner = new Scanner(System.in);
    //---------- Данные библиотеки ----------
    private List<Book> books = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    // -------- сохранение --------
    private Keeping keeping = new KeeperToFile();
    
    public App(){
        books = keeping.loadBooks();
        readers = keeping.loadReaders();
        histories = keeping.loadHistories();
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
                default:
                    System.out.println("Выберите цифру из списка!");;
            }
        }while("r".equals(repeat));
    }
    private void addBook(){
        System.out.println("--- Добавление книги ---");
        Book book = new Book();
        System.out.print("Введите название книги: ");
        book.setBookName(scanner.nextLine());
        System.out.print("Введите год издания книги: ");
        book.setPublishedYear(scanner.nextInt());scanner.nextLine();
        System.out.print("Введите количество экземпляров книги: ");
        book.setQuantity(scanner.nextInt());scanner.nextLine();
        book.setCount(book.getQuantity());
        System.out.println("Введите автора книги ");
        System.out.print("Сколько авторов у книги: ");
        int countAuthors = scanner.nextInt();scanner.nextLine();
        Author[] authors = new Author[countAuthors];
        for (int i = 0; i < authors.length; i++) {
            Author author = new Author();
            System.out.println("Имя автора "+(i+1)+": ");
            author.setFirstname(scanner.nextLine());
            System.out.println("Фамилия автора: ");
            author.setLastname(scanner.nextLine());
            authors[i] = author;
        }
        book.setAuthors(authors);
        System.out.println("---------------------");
        books.add(book);
        keeping.saveBooks(books);
    }
    private void addReader(){
        System.out.println("--- Добавление читателя ---");
        Reader reader = new Reader();
        System.out.println("Имя читателя");
        reader.setFirstname(scanner.nextLine());
        System.out.println("Фамилия читателя");
        reader.setLastname(scanner.nextLine());
        System.out.println("Телефон читателя");
        reader.setPhone(scanner.nextLine());
        System.out.println("---------------------");
        readers.add(reader);
        keeping.saveReaders(readers);
    }

    private void addHistory() {
        System.out.println("--- Выдача книги ---");
        System.out.println("Список книг:");
        History history = new History();
        int n = 0;
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i) != null && books.get(i).getCount()>0){
                StringBuilder sbAuthorNames = new StringBuilder();
                for (int j = 0; j < books.get(i).getAuthors().length; j++) {
                    sbAuthorNames.append(books.get(i).getAuthors()[j].getFirstname())
                                    .append(" ")
                                    .append(books.get(i).getAuthors()[j].getLastname())
                                    .append(". ");
                }
                
                System.out.println(i+1
                        +". "+books.get(i).getBookName()
                        +". "+books.get(i).getPublishedYear()
                        +". "+sbAuthorNames.toString()
                        +". В наличии: " + books.get(i).getCount()
                );
                n++;
            }
        }
        if(n < 1){
            System.out.println("Нет книг для чтения");
            return;
        }
        System.out.print("Выберите номер книги: ");
        int numberBook = scanner.nextInt(); scanner.nextLine();
        System.out.println("Список читателей:");
        for (int i = 0; i < readers.size(); i++) {
            if(readers.get(i) != null){
                System.out.println(i+1+". "+readers.get(i).toString());
            }
        }
        System.out.print("Выберите номер читателя: ");
        int numberReader = scanner.nextInt(); scanner.nextLine();
        history.setBook(books.get(numberBook-1));
        history.setReader(readers.get(numberReader-1));
        Calendar c = new GregorianCalendar();
        history.setGivenDate(c.getTime());
        history.getBook().setCount(history.getBook().getCount() - 1);
        keeping.saveBooks(books);
        histories.add(history);
        keeping.saveHistories(histories);
        System.out.println("Книга "+history.getBook().getBookName()
                            +" выдана читателю "+history.getReader().getFirstname()
                            +" " +history.getReader().getLastname()
        );
        System.out.println("-------------------");
        
    }

    private void printListBooks() {
        System.out.println("--- Список книг ---");
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i) != null && books.get(i).getCount() > 0){
                System.out.println(books.get(i).toString());
            }
        }
        System.out.println("-------------------");
    }

    private void printListReaders() {
        System.out.println("--- Список читателей ---");
        for (int i = 0; i < readers.size(); i++) {
            if(readers.get(i) != null){
                System.out.println(readers.get(i).toString());
            }
        }
        System.out.println("-------------------");
    }

    private void returnBook() {
        System.out.println("--- Возврат книги ---");
        System.out.println("Список читаемых книг:");
        int n = 0;
        for (int i = 0; i < histories.size(); i++) {
            if(histories.get(i) != null
                 && histories.get(i).getReturnedDate() == null
                    && histories.get(i).getBook().getCount() 
                    <  histories.get(i).getBook().getQuantity()
            ){
                System.out.printf("%d. Книгу \"%s\" читает %s %s%n"
                        ,i+1
                        ,histories.get(i).getBook().getBookName()
                        ,histories.get(i).getReader().getFirstname()
                        ,histories.get(i).getReader().getLastname()
                );
                 n++;
            }
        }
        if(n < 1){
            System.out.println("Нет читаемых книг!");
            System.out.println("-------------------");
            return;
        }
        System.out.print("Выберите номер возврщаемой книги: ");
        int numberHistory = scanner.nextInt(); scanner.nextLine();
        Calendar c = new GregorianCalendar();
        histories.get(numberHistory - 1).setReturnedDate(c.getTime());
        histories.get(numberHistory - 1).getBook().setCount(
                histories.get(numberHistory - 1).getBook().getCount()+1
        );
        keeping.saveBooks(books);
        keeping.saveHistories(histories);
        System.out.println("Книга "
                +histories.get(numberHistory - 1).getBook().getBookName()
                +" возвращена в библиотеку"
        );
        System.out.println("-------------------");
    }

    private void printListGivenBooks() {
        System.out.println("Список читаемых книг:");
        int n = 0;
        for (int i = 0; i < histories.size(); i++) {
            if(histories.get(i) != null && histories.get(i).getReturnedDate() == null){
                System.out.println(i+1+". Книгу "
                        +histories.get(i).getBook().getBookName()
                        +" читает "+histories.get(i).getReader().getFirstname()
                        +" "+histories.get(i).getReader().getLastname()
                );
                n++;
            }
        }
        if(n < 1){
            System.out.println("Нет читаемых книг!");
            System.out.println("-------------------");
        }
    }
   
}
