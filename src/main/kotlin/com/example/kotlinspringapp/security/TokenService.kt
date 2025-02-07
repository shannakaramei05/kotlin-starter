package com.example.kotlinspringapp.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Base64
import java.util.Date
import javax.crypto.spec.SecretKeySpec

@Service
class TokenService (@Value("jwt.secret") private val secret:String = ""){

    private val signKey:SecretKeySpec
        get() {
            val keyBytes:ByteArray = Base64.getDecoder().decode(secret)
            return SecretKeySpec(keyBytes,0,keyBytes.size,"HmacSHA256")
        }


    fun generateToken(subject:String, expiration:Date, additionalClaims:Map<String,Any> = emptyMap()) : String{
        return Jwts.builder()
            .setClaims(additionalClaims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(expiration)
            .signWith(signKey)
            .compact()
    }

    fun extractUserName(token:String) :String {
        return extraAllClaims(token).subject
    }

    private fun extraAllClaims(token:String) :Claims {
        return Jwts.parser()
            .setSigningKey(signKey)
            .build()
            .parseClaimsJws(token).body
    }
}