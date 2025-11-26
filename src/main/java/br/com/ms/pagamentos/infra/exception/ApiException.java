package br.com.ms.pagamentos.infra.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private final HttpStatus status;
    private final String path;
    private final Object detalhes;

    public ApiException(HttpStatus status, String mensagem, String path) {
        super(mensagem);
        this.status = status;
        this.path = path;
        this.detalhes = null;
    }

    public ApiException(HttpStatus status, String mensagem, String path, Object detalhes) {
        super(mensagem);
        this.status = status;
        this.path = path;
        this.detalhes = detalhes;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }

    public Object getDetalhes() {
        return detalhes;
    }
}
