package com.xnx3.writecode.client;

import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.NebulaSkin;

import com.xnx3.interfaces.DelayCycleExecute;
import com.xnx3.net.HttpResponse;
import com.xnx3.net.HttpUtil;
import com.xnx3.writecode.client.ui.MainJframe;

public class Entry {
	
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() { 
			public void run() {
//				SubstanceImageWatermark watermark = new SubstanceImageWatermark(MainEntry.class.getResourceAsStream("/res/icon.png"));
//				watermark.setKind(ImageWatermarkKind.SCREEN_CENTER_SCALE);
//				SubstanceSkin skin = new OfficeBlue2007Skin().withWatermark(watermark);   //初始化有水印的皮肤
				
				try{
					SubstanceLookAndFeel.setSkin(new NebulaSkin());
//					SubstanceLookAndFeel.setSkin(skin);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				MainJframe mainJframe = new MainJframe();
				mainJframe.setVisible(true);
				
				
				
				
				
				
				
				
				//新版本检测
				//VersionCheck.check("http://version.xnx3.com/shopStorePCClient.html", Global.VERSION, "storeClient");
			}
		});
		
		
		
	}
	
}
