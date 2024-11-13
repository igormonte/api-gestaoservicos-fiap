package br.com.fiap.postech.gestaoservicos.infrastructure.ical.config;

import br.com.fiap.postech.gestaoservicos.infrastructure.ical.repository.IcalRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.ical.repository.IcalService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class IcalConfig {
    
    @Bean
    public IcalRepository getIcalRepository() {
        return new IcalService();
    }

}
