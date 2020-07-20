package org.anb.mii;

import org.anb.mii.model.Book;
import org.anb.mii.model.Library;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class MiiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiiApplication.class, args);
	}
	 /*Spring Data Rest hides the ID by default, in order to have it in the JSON you have to manually configure that for your entity. 
	Depending on your spring version you can either provide your own configuration (old):*/
	@Component
	public class ExposeEntityIdRestMvcConfiguration extends RepositoryRestConfigurerAdapter {
	  @Override
	  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
	    config.exposeIdsFor(Library.class);
	    config.exposeIdsFor(Book.class);
	  }
	}

}
