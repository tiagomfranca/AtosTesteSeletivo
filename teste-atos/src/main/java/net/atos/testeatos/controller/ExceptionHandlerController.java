package net.atos.testeatos.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * Responsible for handling bad requests and renders the error page or the
 * ResponseEntity according to the error.
 * 
 * @author Tiago de Morais França
 * @version 1.0
 */
@Controller
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler
    implements ErrorController {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getErrorPath() {
    return "error";
  }
  
  /**
   * Processes the request and adds the appropriate error code to the error
   * template model, directing the user to the error page.
   * 
   * @param request Malformed request to be handled
   * @param model Model template, injected by Spring's IoC container.
   * @return redirects the user to the error.html template.
   */
  @RequestMapping("/error")
  public String handleError(HttpServletRequest request, Model model) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    
    if (status != null) {
      Integer statusCode = Integer.valueOf(status.toString());
      
      switch(statusCode) {
        case 400: model.addAttribute("error", "400 - BAD REQUEST");
                  break;
        case 404: model.addAttribute("error", "404 - NOT FOUND");
                  break;
        case 405: model.addAttribute("error", "405 - METHOD NOT ALLOWED");
                  break;
        case 500: model.addAttribute("error", "500 - INTERNAL SERVER ERROR");
      }
    }
    return "error";
  }
  
  /**
   * Used to properly send back a 400 - Bad Request status for the user if the
   * skills PathVariable is missing.
   */
  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(
      MissingPathVariableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    return super.handleMissingPathVariable(ex, headers, status, request);
  }
}
