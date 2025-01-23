package com.santhi;

import com.santhi.service.AddressService;
import com.santhi.service.BookService;
import com.santhi.service.LibraryService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       // LibraryService.insertLibraryAndMember();
      //   LibraryService.insertBooksToLibrary();
        //  BookService.insertBooksToLibrary();
      //   BookService.accessBook();
       // AddressService.addAddressesAndUpdateMembers();
        BookService.insertMembersAndBooks();

    }
}