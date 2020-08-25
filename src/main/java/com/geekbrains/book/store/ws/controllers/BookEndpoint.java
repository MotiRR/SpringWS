package com.geekbrains.book.store.ws.controllers;

import com.geekbrains.book.store.entities.config.GetAllBooksRequest;
import com.geekbrains.book.store.entities.config.GetAllBooksResponse;
import com.geekbrains.book.store.entities.config.GetBookRequest;
import com.geekbrains.book.store.entities.config.GetBookResponse;
import com.geekbrains.book.store.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
@AllArgsConstructor
public class BookEndpoint {

    private static final String NAMESPACE_URI = "http://geekbrains.com/book/store/entities";

    private BookService bookService;

    /*
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:gs="http://geekbrains.com/book/store/entities">
    <soapenv:Header/>
    <soapenv:Body>
        <gs:getBookRequest>
            <gs:id>1</gs:id>
        </gs:getBookRequest>
    </soapenv:Body>
    </soapenv:Envelope>
    * */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
    @ResponsePayload
    public GetBookResponse getBookById(@RequestPayload GetBookRequest request) throws DatatypeConfigurationException {
        GetBookResponse response = new GetBookResponse();
        response.setBook(bookService.findById(request.getId()));
        return response;
    }


    /*
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:gs="http://geekbrains.com/book/store/entities">
    <soapenv:Header/>
    <soapenv:Body>
        <gs:getAllBooksRequest>
        </gs:getAllBooksRequest>
    </soapenv:Body>
    </soapenv:Envelope>
    * */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBooksRequest")
    @ResponsePayload
    public GetAllBooksResponse getAllBooks() throws DatatypeConfigurationException {
        GetAllBooksResponse response = new GetAllBooksResponse();
        response.setBook(bookService.findAll());
        return response;
    }

}
