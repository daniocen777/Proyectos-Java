package com.danicode.marvel.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;


@Component("md5Encoder")
public class MD5Encoder implements PasswordEncoder {
    // rawPassword => texto sin codificar
    @Override
    public String encode(CharSequence rawPassword) {
        return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(this.encode(rawPassword));
    }
}
