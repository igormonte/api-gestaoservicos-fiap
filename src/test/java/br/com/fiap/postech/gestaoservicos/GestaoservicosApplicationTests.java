package br.com.fiap.postech.gestaoservicos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class GestaoservicosApplicationTests {


    @Test
    void contextLoads() {

        String regexPattern = "(.*)(=.*=)(.*)";
        String spec = "aa=eq=aa";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(spec);

        if (matcher.find()) {
            String field = matcher.group(1);
            String operator = matcher.group(2);
            String value = matcher.group(3);
            System.out.println(field);
            System.out.println(operator);
            System.out.println(value);
        }

    }



}
