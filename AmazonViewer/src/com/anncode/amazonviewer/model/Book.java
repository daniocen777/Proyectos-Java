package com.anncode.amazonviewer.model;

import com.anncode.util.AmazonUtil;

import java.util.ArrayList;
import java.util.Date;

public class Book extends Publication implements IVisualizable {
    private int id;
    private String isbn;
    private boolean readed;
    private int timeReaded;
    private ArrayList<Page> pages;


    public Book(String title, Date edititionDate, String editorial, String[] authors, ArrayList<Page> pages) {
        super(title, edititionDate, editorial);
        // TODO Auto-generated constructor stub
        setAuthors(authors);
        this.pages = pages;
    }

    public ArrayList<Page> getPages() {
        return pages;
    }

    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }

    public int getId() {
        return id;
    }


    public String getIsbn() {
        return isbn;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public String isReaded() {
        String leido = "";
        if (readed == true) {
            leido = "Sí";
        } else {
            leido = "No";
        }

        return leido;
    }


    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public boolean getIsReaded() {
        return readed;
    }


    public int getTimeReaded() {
        return timeReaded;
    }


    public void setTimeReaded(int timeReaded) {
        this.timeReaded = timeReaded;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String detailBook = "\n :: BOOK ::" +
                "\n Title: " + getTitle() +
                "\n Editorial: " + getEditorial() +
                "\n Edition Date: " + getEdititionDate() +
                "\n Authors: ";
        for (int i = 0; i < getAuthors().length; i++) {
            detailBook += "\t" + getAuthors()[i] + " ";
        }
        return detailBook;
    }


    @Override
    public Date startToSee(Date dateI) {
        // TODO Auto-generated method stub
        return dateI;
    }


    @Override
    public void stopToSee(Date dateI, Date dateF) {
        // TODO Auto-generated method stub
        if (dateF.getTime() > dateI.getTime()) {
            setTimeReaded((int) (dateF.getTime() - dateI.getTime()));
        } else {
            setTimeReaded(0);
        }
    }

    public static ArrayList<Book> makeBookList() {
        ArrayList<Book> books = new ArrayList();
        String[] authors = new String[3];
        for (int i = 0; i < 3; i++) {
            authors[i] = "author " + i;
        }
        // Páginas
        ArrayList<Page> pages = new ArrayList<>();
        int pagina = 0;
        for (int i = 0; i < 3; i++) {
            pagina = i + 1;
            pages.add(new Book.Page(pagina, "Contenido de la página " + pagina));
        }
        for (int i = 1; i <= 5; i++) {
            books.add(new Book("Book " + i, new Date(), "editorial " + i, authors, pages));
        }

        return books;
    }

    public void view() {
        setReaded(true);
        Date dateI = startToSee(new Date());

        int index = 0;
        do {
            System.out.println(".............");
            System.out.println("Page: " + getPages().get(index).getNumber());
            System.out.println(getPages().get(index).getContent());
            System.out.println(".............");
            if (index != 0) {
                System.out.println("1. Regresar página");
            }
            System.out.println("2. Siguiente página");
            System.out.println("0. Cerrar libro");
            System.out.println("*****************");

            int response = AmazonUtil.validateUserResponseMenu(0, 2);
            if (response == 2) {
                index += 1;
            } else if (response == 1) {
                index -= 1;
            } else if (response == 0) {
                break;
            }
        }
        while (index < getPages().size());
        //Terminé de verla
        stopToSee(dateI, new Date());
        System.out.println();
        System.out.println("Leíste: " + toString());
        System.out.println("Por: " + getTimeReaded() + " milisegundos");
    }

    public static class Page {
        private int id;
        private int number; // Número de la página
        private String content;

        public Page(int number, String content) {
            this.number = number;
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
