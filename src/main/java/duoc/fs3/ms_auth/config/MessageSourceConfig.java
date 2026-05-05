package duoc.fs3.ms_auth.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.nio.charset.StandardCharsets;

/**
 * Configuración de MessageSource para la internacionalización y centralización de mensajes.
 * 
 * Esta clase configura el bean MessageSource para que la aplicación pueda leer
 * mensajes desde archivos de propiedades, permitiendo la centralización de todos
 * los mensajes del microservicio en un único lugar.
 * 
 * @author Duoc UC Fullstack III
 * @version 1.0
 * @since 2026
 */
@Configuration
public class MessageSourceConfig {

    /**
     * Bean principal de MessageSource configurado para leer archivos de propiedades.
     * 
     * @return MessageSource configurado para usar UTF-8 y messages.properties
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setCacheSeconds(10); // Recargar cada 10 segundos para desarrollo
        messageSource.setFallbackToSystemLocale(true);
        return messageSource;
    }

    /**
     * Configura el validador de beans para usar MessageSource personalizado.
     * 
     * @return LocalValidatorFactoryBean configurado con nuestro MessageSource
     */
    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }
}
