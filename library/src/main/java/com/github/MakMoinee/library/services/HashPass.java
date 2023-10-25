package com.github.MakMoinee.library.services;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class HashPass {

    public HashPass() {
    }

    public String makeHashPassword(String password) {
        String hashPass = "";
        hashPass = BCrypt.withDefaults().hashToString(8, password.toCharArray());
        return hashPass;
    }

    public Boolean verifyPassword(String password, String hashPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashPassword);
        return result.verified;
    }
}
