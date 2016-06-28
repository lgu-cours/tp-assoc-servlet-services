package services;

import javax.servlet.http.HttpServletRequest;

public class DefaultService implements ServiceInterface {

	@Override
	public boolean execute(HttpServletRequest request) {
		return true;
	}

}
