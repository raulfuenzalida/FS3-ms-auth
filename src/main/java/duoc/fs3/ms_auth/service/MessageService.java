package duoc.fs3.ms_auth.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Servicio para la gestión centralizada de mensajes de la aplicación.
 * 
 * Esta clase actúa como un wrapper de MessageSource para facilitar
 * el acceso a los mensajes centralizados en messages.properties.
 * Proporciona métodos convenientes para obtener mensajes con y sin parámetros.
 * 
 * @author Duoc UC Fullstack III
 * @version 1.0
 * @since 2026
 */
@Service
public class MessageService {

    private final MessageSource messageSource;
    private static final Locale DEFAULT_LOCALE = new Locale("es", "CL");

    /**
     * Constructor que inyecta el MessageSource configurado.
     * 
     * @param messageSource Bean de MessageSource configurado en MessageSourceConfig
     */
    public MessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Obtiene un mensaje del archivo de propiedades sin parámetros.
     * 
     * @param key Clave del mensaje en messages.properties
     * @return Mensaje formateado o la clave si no se encuentra
     */
    public String getMessage(String key) {
        return getMessage(key, null, DEFAULT_LOCALE);
    }

    /**
     * Obtiene un mensaje del archivo de propiedades con parámetros.
     * 
     * @param key Clave del mensaje en messages.properties
     * @param args Parámetros para formatear el mensaje
     * @return Mensaje formateado o la clave si no se encuentra
     */
    public String getMessage(String key, Object... args) {
        return getMessage(key, args, DEFAULT_LOCALE);
    }

    /**
     * Obtiene un mensaje del archivo de propiedades con locale específico.
     * 
     * @param key Clave del mensaje en messages.properties
     * @param locale Locale para el mensaje
     * @return Mensaje formateado o la clave si no se encuentra
     */
    public String getMessage(String key, Locale locale) {
        return getMessage(key, null, locale);
    }

    /**
     * Método interno para obtener mensajes con todos los parámetros.
     * 
     * @param key Clave del mensaje
     * @param args Parámetros de formateo (puede ser null)
     * @param locale Locale a usar
     * @return Mensaje formateado o la clave si no se encuentra
     */
    private String getMessage(String key, Object[] args, Locale locale) {
        try {
            if (args != null && args.length > 0) {
                return messageSource.getMessage(key, args, locale);
            } else {
                return messageSource.getMessage(key, null, locale);
            }
        } catch (Exception e) {
            // Si no se encuentra el mensaje, devolver la clave como fallback
            return key;
        }
    }
}
