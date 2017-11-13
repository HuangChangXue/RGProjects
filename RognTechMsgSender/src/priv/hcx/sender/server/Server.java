package priv.hcx.sender.server;

import java.io.InputStream;
import java.io.OutputStream;

public interface Server {
	InputStream  getInputStream();
	OutputStream getOutputStream();
}
