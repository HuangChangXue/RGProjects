package priv.hcx.sender.server;

import priv.hcx.sender.tool.CommonTools;

public class ServerConf {
	private String id = CommonTools.createRandomID(), name, host, port, protel, encoder, decoder;

	public String getId() {
		return id;
	}
	private String encoderConfigName=null,decoderConfigName=null,serverType="remote";
	
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getProtel() {
		return protel;
	}

	public void setProtel(String protel) {
		this.protel = protel;
	}

	public String getEncoder() {
		return encoder;
	}

	public void setEncoder(String encoder) {
		this.encoder = encoder;
	}

	public String getDecoder() {
		return decoder;
	}

	public void setDecoder(String decoder) {
		this.decoder = decoder;
	}

	public String getEncoderConfigName() {
		return encoderConfigName;
	}

	public void setEncoderConfigName(String encoderConfigName) {
		this.encoderConfigName = encoderConfigName;
	}

	public String getDecoderConfigName() {
		return decoderConfigName;
	}

	public void setDecoderConfigName(String decoderConfigName) {
		this.decoderConfigName = decoderConfigName;
	}

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}



}
