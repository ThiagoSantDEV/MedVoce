package br.app.tads.medvoce.Config.responseBuilder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface IResponseBuilder {
    public ResponseEntity<?> build(String menssagem, HttpStatus httpStatus);
    public ResponseEntity<?> build(Object object, HttpStatus httpStatus);
    public ResponseEntity<?> build(HttpStatus httpStatus);

}