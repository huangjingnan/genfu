package com.genfu.reform.jmx.mxbeans;

import java.io.IOException;

public interface ConfigNetworkMXBean {
	public ConfigResult cfgDebian(String address,String netmask,String gateway) throws IOException;
	public ConfigResult cfgUpgrade(String password,String password2,String password3) throws IOException;
}
