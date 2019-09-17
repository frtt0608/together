package com.ssafy.hashtag;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.transaction.annotation.EnableTransactionManagement;
// import org.springframework.web.bind.annotation.RequestMapping;

// @EnableOAuth2Client
@SpringBootApplication
@EnableTransactionManagement
public class SsafYhashtagApplication {
    public static void main(String[] args) {
      SpringApplication.run(SsafYhashtagApplication.class, args);
    }

	// @Override
  //   protected void configure(HttpSecurity http) throws Exception {
  //     http
  //       .csrf().disable()
  //       .antMatcher("/**")
  //       .authorizeRequests()
  //         .antMatchers("/", "/login**", "/webjars/**")
  //         .permitAll()
  //       .anyRequest()
  //         .authenticated()
  //       .and().logout().logoutSuccessUrl("/").permitAll();
	// }
	
	// @RequestMapping("/")
	// public Principal user(Principal principal) {
	// 			return principal;
	// }
}
