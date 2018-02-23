package com.mall.util;

import org.springframework.dao.DataAccessException;

public class DaoDataAccessException extends DataAccessException {

	public DaoDataAccessException(String msg) {
		super(msg);
	}

}
