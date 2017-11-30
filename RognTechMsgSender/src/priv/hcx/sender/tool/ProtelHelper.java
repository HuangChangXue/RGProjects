package priv.hcx.sender.tool;

import java.util.List;

import priv.hcx.sender.protel.ProtelHandler;

public class ProtelHelper {
	public static ProtelHandler getProtelHandler(String name){
		List<ProtelHandler> handlers=CommonTools.loadService(ProtelHandler.class);
		for(ProtelHandler handler:handlers){
			if(handler.canHandProtel(name)){
				return handler;
			}
		}
		return null;
	}
}
