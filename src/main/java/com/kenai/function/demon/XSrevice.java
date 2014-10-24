package com.kenai.function.demon;

import android.content.Context;
import android.content.Intent;

public abstract class XSrevice {

	public Context context;
	public XSrevice (Context context){
		this.context=context;
	}
	
	public abstract void xBind(Intent arg0);
	
	public abstract void xCreate();
	
	public abstract void xDestroy() ;
	
	public abstract void xstart(Intent intent) ;
	
	
	

}
