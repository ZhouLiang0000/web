package com.ai.web.service;

import java.sql.SQLException;
import java.util.List;

import com.ai.web.daoo.SearchDao;
import com.ai.web.domain.Product;

public class SearchService {

	public List<Object> searchWord(String word) throws SQLException {
		SearchDao dao = new SearchDao();
		List<Object> productList = dao.searchWord(word);
		return productList;
	}

}
