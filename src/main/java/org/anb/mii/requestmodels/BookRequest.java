package org.anb.mii.requestmodels;

import javax.persistence.Entity;

import org.anb.mii.model.Book;
import org.anb.mii.model.Library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {
	   private String name;
	    private String authorName;
	    private long library;
}
