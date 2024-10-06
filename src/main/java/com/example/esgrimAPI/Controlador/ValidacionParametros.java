package com.example.esgrimAPI.Controlador;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import java.util.List;
import java.util.stream.Collectors;
import com.example.esgrimAPI.DTO.Respuesta.MessageError;
public class ValidacionParametros {

    public static ResponseEntity<MessageError> manejarValidacion(BindingResult bindingResult) {
        List<String> errores = bindingResult.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(new MessageError(errores), HttpStatus.BAD_REQUEST);
    }

}
