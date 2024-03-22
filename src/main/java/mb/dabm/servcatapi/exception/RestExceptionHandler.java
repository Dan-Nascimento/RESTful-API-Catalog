package mb.dabm.servcatapi.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

//@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;
    @Value("${info.app.version}")
    private String version;
    @Autowired
    private ErrorAttributes errorAttributes;
    @Value("${debug}")
    private boolean debug;



    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(
        DataIntegrityViolationException ex, WebRequest request)
    {
        String mensagemUsuario = messageSource.getMessage(
            "recurso.operacao-nao-permitida", null,
            LocaleContextHolder.getLocale());
        // retorna a causa da excessao
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        List<Erro> erros = Arrays
            .asList(new Erro(mensagemUsuario, mensagemDesenvolvedor, ex.getMessage()));

        return handleExceptionInternal(ex, erros, new HttpHeaders(),
            HttpStatus.BAD_REQUEST, request);
    }

    /// refactor Anders

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
        HttpMediaTypeNotSupportedException ex,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request) {
        //return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

        var apiError = new ApiErrorResponse.ApiErrorResponseBuilder()
            .withDetail(ex.getDetailMessageCode())
            .withMessage(ex.getMessage())
            .withError_code(status.value())
            .withStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
            .withVersion(version)
            .withPath(request.getContextPath())
            .atTime(LocalDateTime.now())
            .build()
            ;

//        return buildResponseEntity(new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
//            builder.substring(0, builder.length() - 2), ex))
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
        HttpRequestMethodNotSupportedException ex,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request) {
        //return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
        //String error = ex.getMethod()+ " parameter is missing";
        //return buildResponseEntity(new ApiError(BAD_REQUEST, error, ex));

        var apiError = new ApiErrorResponse.ApiErrorResponseBuilder()
            .withDetail(ex.getDetailMessageCode())
            .withMessage(ex.getMessage())
            .withError_code(status.value())
            .withStatus(HttpStatus.BAD_REQUEST)
            .withVersion(version)
            .withPath(request.getContextPath())
            .atTime(LocalDateTime.now())
            .build()
            ;

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
        MissingServletRequestParameterException ex,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request) {

//        String error = ex.getParameterName() + " parameter is missing";
//        return buildResponseEntity(new ApiError(BAD_REQUEST, error, ex));
        var apiError = new ApiErrorResponse.ApiErrorResponseBuilder()
            .withDetail(ex.getDetailMessageCode())
            .withMessage(ex.getMessage())
            .withError_code(status.value())
            .withStatus(HttpStatus.BAD_REQUEST)
            .withVersion(version)
            .withPath(request.getContextPath())
            .atTime(LocalDateTime.now())
            .build()
            ;

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

    }

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request) {

        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());

        String error = messageSource.getMessage("json.malformed", null, LocaleContextHolder.getLocale());

        var apiError = new ApiErrorResponse.ApiErrorResponseBuilder()
            .withDetail(ex.getMessage() + ex.getLocalizedMessage())
            .withMessage(error)
            .withError_code(status.value())
            .withStatus(HttpStatus.BAD_REQUEST)
            .withVersion(version)
            .withPath(request.getContextPath())
            .atTime(LocalDateTime.now())
            .build()
            ;

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

        //return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request) {

//        ApiError apiError = new ApiError(BAD_REQUEST);
//        apiError.setMessage(messageSource.getMessage("error.validation", null, LocaleContextHolder.getLocale()));
//        apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
//        apiError.addValidationError(ex.getBindingResult().getGlobalErrors());

        String error = messageSource.getMessage("error.validation", null, LocaleContextHolder.getLocale());

        var apiError = new ApiErrorResponse.ApiErrorResponseBuilder()
            .withDetail(ex.getDetailMessageCode())
            .withMessage(error + " | " + ex.getMessage())
            .withError_code(status.value())
            .withStatus(HttpStatus.BAD_REQUEST)
            .withVersion(version)
            .withPath(request.getContextPath())
            .atTime(LocalDateTime.now())
            .build()
            ;

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

        //return buildResponseEntity(apiError);
    }

    /**
     * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
     *
     * @param ex the ConstraintViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
        jakarta.validation.ConstraintViolationException ex) {
//        ApiError apiError = new ApiError(BAD_REQUEST);
//        apiError.setMessage(messageSource.getMessage("error.validation", null, LocaleContextHolder.getLocale()));
//        apiError.addValidationErrors(ex.getConstraintViolations());
//        return buildResponseEntity(apiError);

        String error = messageSource.getMessage("error.validation", null, LocaleContextHolder.getLocale());

        var apiError = new ApiErrorResponse.ApiErrorResponseBuilder()
            .withDetail(ex.getConstraintViolations().toString())
            .withMessage(error + " | " + ex.getMessage())
            .withError_code(404)
            .withStatus(HttpStatus.BAD_REQUEST)
            .withVersion(version)
            .withPath(ex.getCause().getMessage())
            .atTime(LocalDateTime.now())
            .build()
            ;

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles EntityNotFoundException. Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
     *
     * @param ex the EntityNotFoundException
     * @return the ApiError object
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
//        ApiError apiError = new ApiError(NOT_FOUND);
//        apiError.setMessage(ex.getMessage());
//        return buildResponseEntity(apiError);

        var apiError = new ApiErrorResponse.ApiErrorResponseBuilder()
            .withDetail(ex.getStackTrace().toString())
            .withMessage(ex.getMessage())
            .withError_code(404)
            .withStatus(HttpStatus.NOT_FOUND)
            .withVersion(version)
            .withPath(ex.getCause().getMessage())
            .atTime(LocalDateTime.now())
            .build()
            ;

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);

    }

    /**
     * Handle HttpMessageNotWritableException.
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
        HttpMessageNotWritableException ex,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request) {
        //return super.handleHttpMessageNotWritable(ex, headers, status, request);
        String error = messageSource.getMessage("json.error.w", null, LocaleContextHolder.getLocale());
//        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));

        var apiError = new ApiErrorResponse.ApiErrorResponseBuilder()
            .withDetail(ex.getStackTrace().toString())
            .withMessage(error + " | " + ex.getMessage())
            .withError_code(status.value())
            .withStatus(HttpStatus.NOT_FOUND)
            .withVersion(version)
            .withPath(ex.getCause().getMessage())
            .atTime(LocalDateTime.now())
            .build()
            ;

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
     *
     * @param ex the DataIntegrityViolationException
     * @return the ApiError object
     */
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
//                                                                  WebRequest request) {
//        if (ex.getCause() instanceof ConstraintViolationException) {
//            return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, "Database error", ex.getCause()));
//        }
//        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
//    }



    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
//        ApiError apiError = new ApiError(BAD_REQUEST);
//        apiError.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
//        apiError.setDebugMessage(ex.getMessage());
//        return buildResponseEntity(apiError);

        String error = String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", ex.getName(),
            ex.getValue(), ex.getRequiredType().getSimpleName());

        var apiError = new ApiErrorResponse.ApiErrorResponseBuilder()
            .withDetail(ex.getValue().toString())
            .withMessage(error + " | " + ex.getMessage())
            .withError_code(404)
            .withStatus(HttpStatus.BAD_REQUEST)
            .withVersion(version)
            .withPath(ex.getCause().getMessage())
            .atTime(LocalDateTime.now())
            .build()
            ;

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
        NoHandlerFoundException ex,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request) {
        //return super.handleNoHandlerFoundException(ex, headers, status, request);
//        ApiError apiError = new ApiError(BAD_REQUEST);
//        apiError.setMessage(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()));
//        apiError.setDebugMessage(ex.getMessage());
//        return buildResponseEntity(apiError);
        String error = String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL());

        var apiError = new ApiErrorResponse.ApiErrorResponseBuilder()
            .withDetail(ex.getDetailMessageCode())
            .withMessage(error + " | " + ex.getMessage())
            .withError_code(404)
            .withStatus(HttpStatus.BAD_REQUEST)
            .withVersion(version)
            .withPath(ex.getCause().getMessage())
            .atTime(LocalDateTime.now())
            .build()
            ;

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    //@Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter
        (MissingServletRequestParameterException ex,
         HttpHeaders headers, HttpStatus status,
         WebRequest request) {

        //String local = Utils.getUriError(request.toString());

        ErrorJson e = new ErrorJson(HttpStatus.NOT_FOUND.value(), getErrorAttributes(request, debug));

//        errorMessages = new CustomErrorMessages("Parâmetro inexistente na " + local,
//            HttpStatus.NOT_FOUND.value());


        return handleExceptionInternal(ex, e, headers, HttpStatus.NOT_FOUND, request);
    }

    //@Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {

        //String local = Utils.getUriError(request.toString());
//        errorMessages = new CustomErrorMessages("Tipo de parâmentro errado na " + local,
//            HttpStatus.BAD_REQUEST.value());

        var apiError = new ApiErrorResponse.ApiErrorResponseBuilder()
            .withDetail(ex.getPropertyName())
            .withMessage(ex.getMessage())
            .withError_code(status.value())
            .withStatus(HttpStatus.BAD_REQUEST)
            .withVersion(version)
            .withPath(ex.getCause().getMessage())
            .atTime(LocalDateTime.now())
            .build()
            ;

        return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ SQLException.class })
    public ResponseEntity<Object> handleSqlException(SQLException ex, WebRequest request) {
       // String local = Utils.getUriError(request.toString());

//        errorMessages = new CustomErrorMessages("Erro inseperado ao executar consulta na " + local,
//            HttpStatus.INTERNAL_SERVER_ERROR.value());

        var apiError = new ApiErrorResponse.ApiErrorResponseBuilder()
            .withDetail(ex.getSQLState())
            .withMessage(ex.getMessage())
            .withError_code(500)
            .withStatus(HttpStatus.CONFLICT)
            .withVersion(version)
            .withPath(ex.getCause().getMessage())
            .atTime(LocalDateTime.now())
            .build()
            ;

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({ IndexOutOfBoundsException.class })
    public ResponseEntity<Object> handleIndexOutOfBoundsException(IndexOutOfBoundsException ex, WebRequest request) {
        //String local = Utils.getUriError(request.toString());

//        errorMessages = new CustomErrorMessages("Erro inseperado ao executar consulta na " + local,
//            HttpStatus.INTERNAL_SERVER_ERROR.value());

        var apiError = new ApiErrorResponse.ApiErrorResponseBuilder()
            .withDetail(ex.getStackTrace().toString())
            .withMessage(ex.getMessage())
            .withError_code(500)
            .withStatus(HttpStatus.CONFLICT)
            .withVersion(version)
            .withPath(ex.getCause().getMessage())
            .atTime(LocalDateTime.now())
            .build()
            ;

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private Map<String, Object> getErrorAttributes(WebRequest request, boolean includeStackTrace) {
        return errorAttributes.getErrorAttributes(request,
            includeStackTrace
                ? ErrorAttributeOptions.defaults().including(ErrorAttributeOptions.Include.STACK_TRACE)
                : ErrorAttributeOptions.defaults().excluding(ErrorAttributeOptions.Include.STACK_TRACE));
    }

    private List<Erro> criarListaDeErros(BindingResult bindingResult)
    {
        List<Erro> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String mensagemUsuario = messageSource.getMessage(fieldError,
                LocaleContextHolder.getLocale());
            String mensagemDesenvolvedor = fieldError.toString();
            erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        }

        return erros;
    }

    // classe interna
    public static class Erro
    {

        private String mensagemUsuario;
        private String mensagemDesenvolvedor;

        private String message;

        public Erro(String mensagemUsuario, String mensagemDesenvolvedor, String message)
        {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
            this.message = message;
        }

        public Erro(String mensagemUsuario, String mensagemDesenvolvedor)
        {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }

        public String getMessage() {
            return message;
        }

        public String getMensagemUsuario()
        {
            return mensagemUsuario;
        }

        public String getMensagemDesenvolvedor()
        {
            return mensagemDesenvolvedor;
        }

    }
}
