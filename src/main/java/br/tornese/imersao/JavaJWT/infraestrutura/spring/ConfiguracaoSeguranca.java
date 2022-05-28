package br.tornese.imersao.JavaJWT.infraestrutura.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity 
public class ConfiguracaoSeguranca  extends WebSecurityConfigurerAdapter {

  @Autowired
  private ApiEntryPoint entryPoint;

  public void configure(HttpSecurity httpSec) throws Exception{

    httpSec.csrf().disable()
					  .exceptionHandling().authenticationEntryPoint(entryPoint)   // estou colocando um tratador de exceções
					  .and()
					  .authorizeRequests()
					  .antMatchers(HttpMethod.POST, "/login").permitAll()
					  .antMatchers(HttpMethod.GET, "/").permitAll()
					  .antMatchers(HttpMethod.GET, "/gerar-adm").permitAll()
					  .antMatchers("/clientes/**").hasAnyAuthority("ADM", "EDITOR") // autorização
					  .antMatchers("/administradores/**").hasAnyAuthority("ADM") // autorização
					  .anyRequest().authenticated().and().cors();
		
		httpSec.addFilterBefore(new ApiFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
}