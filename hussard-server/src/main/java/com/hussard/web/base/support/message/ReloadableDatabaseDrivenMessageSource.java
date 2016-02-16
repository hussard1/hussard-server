package com.hussard.web.base.support.message;

import com.hussard.web.base.domain.Message;
import com.hussard.web.base.setup.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Matthew on 2014-08-25.
 */
@Component("messageSource")
public class ReloadableDatabaseDrivenMessageSource extends AbstractMessageSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReloadableDatabaseDrivenMessageSource.class);

    private final Map<String, Map<String, String>> properties = new HashMap<>();

    @Autowired
    private MessageRepository messageRepository;

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String msg = getText(code, locale);
        if (StringUtils.hasText(msg)) {
            MessageFormat result = createMessageFormat(msg, locale);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        return getText(code, locale);
    }

    private String getText(String code, Locale locale) {
        if (properties.isEmpty()) {
            refreshProperties();
        }

        Map<String, String> localized = properties.get(locale.getLanguage());
        if (localized == null) {
            localized = properties.get(Locale.ENGLISH.getLanguage());
        }

        return localized.get(code);
    }

    public void reload() {
        refreshProperties();
    }

    protected void refreshProperties() {
        properties.clear();
        properties.putAll(loadProperties());
    }

    protected Map<String, Map<String, String>> loadProperties() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Loading properties from database...");
        }

        Map<String, Map<String, String>> prop = new HashMap<>();

        List<Message> messages = messageRepository.getMessages();
        for (Message message : messages) {
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("Adding message {}", message);
            }
            if (prop.containsKey(message.getLanguageCode())) {
                Map<String, String> messageValue = prop.get(message.getLanguageCode());
                messageValue.put(message.getCode(), message.getMessage());
            } else {
                Map<String, String> messageValue = new HashMap<>();
                messageValue.put(message.getCode(), message.getMessage());
                prop.put(message.getLanguageCode(), messageValue);
            }
        }

        return prop;
    }
}
