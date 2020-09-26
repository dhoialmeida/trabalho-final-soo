package br.unesp.grupo5.trabalhofinal.security;

public class SecurityConstants {

    public static final String SECRET = "gngSLzG[bf?0eXto0~KEBZ{qrL2Z40u!HR02Bs(ju`W=m$'OX%XF`G5:9`j#b*u";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
}
