package com.genfu.agent.mxbeans;

import java.io.IOException;

public interface ConfigNetworkMXBean {
	public ConfigResult cfgDebian(String address,String netmask,String gateway) throws IOException;
}
