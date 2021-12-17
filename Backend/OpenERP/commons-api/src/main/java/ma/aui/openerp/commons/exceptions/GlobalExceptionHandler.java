package ma.aui.openerp.commons.exceptions;

import java.util.Date;
import org.axonframework.messaging.HandlerExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler(IllegalArgumentException.class)
 public ResponseEntity<?> illegalArgumentExceptionHandler(IllegalArgumentException ex, WebRequest request) {
  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
  return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
 }

 @ExceptionHandler(EmployeeNotFoundException.class)
 public ResponseEntity<?> employeeNotFoundExceptionHandler(EmployeeNotFoundException ex, WebRequest request) {
  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
  return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(AccessDeniedException.class)
 public ResponseEntity<?> accessDeniedExceptionHandler(AccessDeniedException ex, WebRequest request) {
  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
  return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
 }

 @ExceptionHandler(ManagerNotFoundException.class)
 public ResponseEntity<?> managerNotFoundExceptionHandler(ManagerNotFoundException ex, WebRequest request) {
  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
  return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(InsufficientLeaveBalanceException.class)
 public ResponseEntity<?> insufficientLeaveBalanceExceptionHandler(InsufficientLeaveBalanceException ex, WebRequest request) {
  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
  return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
 }

 @ExceptionHandler(InvalidDateException.class)
 public ResponseEntity<?> invalidDateExceptionHandler(InvalidDateException ex, WebRequest request) {
  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
  return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
 }

 @ExceptionHandler(InvalidDateIntervalException.class)
 public ResponseEntity<?> invalidDateIntervalExceptionHandler(InvalidDateIntervalException ex, WebRequest request) {
  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
  return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
 }

 @ExceptionHandler(SystemException.class)
 public ResponseEntity<?> systemExceptionHandler(SystemException ex, WebRequest request) {
  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
  return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
 }

 @ExceptionHandler(HandlerExecutionException.class)
 public ResponseEntity<?> axonExceptionHandler(HandlerExecutionException ex, WebRequest request) {
  System.out.println("OK1");
  ex.getCause().printStackTrace();
  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
  return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
 }


 @ExceptionHandler(Exception.class)
 public ResponseEntity<?> excpetionHandler(Exception ex, WebRequest request) {
  // Logging
  System.out.println("+++ GlobalExceptionHandler : Exception Not Handled...");
  System.out.println(ex.getClass());
  ex.printStackTrace();
  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
  return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
 }




}