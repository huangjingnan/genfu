package com.genfu.reform.handler;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.apache.struts2.rest.handler.ContentTypeHandler;

public class MyXmlContentHandler  implements ContentTypeHandler {
	public Object getModel() {
		return null;
	}
	public Object findModels() {
		return null;
	}
	@Override
	public void toObject(Reader in, Object target) throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String fromObject(Object obj, String resultCode, Writer stream)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getExtension() {
		// TODO Auto-generated method stub
		return null;
	}
}
