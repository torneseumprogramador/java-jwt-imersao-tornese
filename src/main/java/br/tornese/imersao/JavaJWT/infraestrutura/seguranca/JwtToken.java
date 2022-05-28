package br.tornese.imersao.JavaJWT.infraestrutura.seguranca;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.tornese.imersao.JavaJWT.domain.entidades.IAdministrador;
import br.tornese.imersao.JavaJWT.domain.seguranca.IToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtToken implements IToken {
	private static final int    SEGUNDOS = 1000;
	private static final int    MINUTOS  = 60*SEGUNDOS;
	private static final int    HORAS    = 60*MINUTOS;
	private static final int    DIAS     = 24*HORAS;
	
	private static final String HEADER = "Authorization";  // cabecalho http
	private static final String PREFIX = "Bearer ";        // prefixo do token
	private static final long   EXPIRATION = 1*DIAS;    // tempo de validade
	private static final String SECRET_KEY = "3c0MMerc3Do1f00dP@r@T3st3sD3JWT*";  // palavra chave do token
	
	@Override
    public String build(IAdministrador administrador) {
		Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
		
		JwtBuilder jwtBuilder =	Jwts.builder()
										 .setSubject(administrador.getEmail())
								     	 .setIssuer(administrador.getRegra());

		jwtBuilder.claim(administrador.getRegra(), administrador.getRegra());

		String token = jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
								.signWith(secretKey, SignatureAlgorithm.HS256)
								.compact();
		return PREFIX + token;
	}
	
	private static boolean isExpirationValid(Date expiration) {
		return expiration.after(new Date(System.currentTimeMillis()));	
	}
	private static boolean isSubjectValid(String email) {
		return email!=null && email.length() > 0;
	}
	
	public static Authentication validarRequest(HttpServletRequest request) {

		String token = request.getHeader(HEADER);
		token = token.replace(PREFIX, "");
		
		Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes())
				                                    .build()
				                                    .parseClaimsJws(token);
		
		String email	 	= jwsClaims.getBody().getSubject();
		String issuer   = jwsClaims.getBody().getIssuer();
		Date   expira   = jwsClaims.getBody().getExpiration();

		System.out.println("Nome do usuário logado" + email + " com acesso de: " + issuer);

		String admin = jwsClaims.getBody().get("ADM", String.class);
		String editor = jwsClaims.getBody().get("EDITOR", String.class);

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		if(admin != null) authorities.add(new SimpleGrantedAuthority(admin));
		if(editor != null) authorities.add(new SimpleGrantedAuthority(editor));
		
		if (isSubjectValid(email) && isExpirationValid(expira)) {
			return new UsernamePasswordAuthenticationToken(email, null, authorities);
		}
	
		return null; 
	}
}
