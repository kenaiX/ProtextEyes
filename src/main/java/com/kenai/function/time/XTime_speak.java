//package com.kenai.function.time;
//
//import com.kenai.xiankong.R;
//
//import android.content.Context;
//import android.media.MediaPlayer;
//
//public class XTime_speak implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener{
//	
//	Context context;
//	public XTime_speak(Context context){
//		this.context=context;
//	}
//	
//	
//	MediaPlayer mymedia=new MediaPlayer();
//	mymedia=MediaPlayer.create(context, R.raw.h0);
//	   mymedia.start();
//	
//	
//	
//	public final int speak_time(int part){
//		switch(part){
//		case 1:
//		   int lock_hour=XTime.gettime_partly(4);
//		   if(lock_hour>12)
//			   lock_hour-=12;
//		   switch(lock_hour){
//		   case 0:
//			   return R.raw.h0;
//			   break;
//		   case 1:
//			   return R.raw.h1;
//			   break;
//		   case 2:
//			   return R.raw.h2;
//			   break;
//		   case 3:
//			   return R.raw.h3;
//			   break;
//		   case 4:
//			   return R.raw.h4;
//			   break;
//		   case 5:
//			   return R.raw.h5;
//			   break;
//		   case 6:
//			   return R.raw.h6;
//			   break;
//		   case 7:
//			   return R.raw.h7;
//			   break;	   
//		   case 8:
//			   return R.raw.h8;
//			   break;
//		   case 9:
//			   return R.raw.h9;
//			   break;
//		   case 10:
//			   return R.raw.h10;
//			   break;
//		   case 11:
//			   return R.raw.h11;
//			   break;
//		   case 12:
//			   return R.raw.h12;
//			   break;
//		   }
//		break;
//		case 2:
//			
//			break;
//			
//	}
//	}
//
//
//
//
//
//
//	public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//
//
//
//
//	public void onCompletion(MediaPlayer mp) {
//		// TODO Auto-generated method stub
//		
//	}
//}
