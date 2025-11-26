package br.com.ms.pagamentos.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApiException(ApiException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        HttpStatus status = ex.getStatus() != null ? ex.getStatus() : HttpStatus.BAD_REQUEST;

        body.put("sucesso", false);
        body.put("mensagem", ex.getMessage());
        body.put("status", status.value());
        body.put("path", ex.getPath());
        body.put("timestamp", OffsetDateTime.now().toString());
        body.put("erros", ex.getDetalhes() == null ? null : ex.getDetalhes());

        return ResponseEntity.status(status).body(body);
    }

    // Handler genérico (para outras exceções não tratadas) — retorna 500 sem stacktrace público
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("sucesso", false);
        body.put("mensagem", "Erro interno no servidor");
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("path", null);
        body.put("timestamp", OffsetDateTime.now().toString());
        body.put("erros", null);

        // aqui você pode logar o stacktrace em logs, mas não expor ao cliente
        ex.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
