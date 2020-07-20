package org.anb.mii.requestmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksToLIBMapping {
	   private Long libID;
	    private Long bookID;
}
