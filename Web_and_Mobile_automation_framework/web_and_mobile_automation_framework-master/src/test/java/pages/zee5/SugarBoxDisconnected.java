package pages.zee5;

import org.openqa.selenium.By;

import utils.AppiumUtils;

public class SugarBoxDisconnected extends AppiumUtils{

	public static class SBDisconnected{
		
		public static final By crossBtn = By.xpath("//*[contains(@resource-id,'close_dialogueBTID')]");
	}
	
	public void closeDisconnectPopup(int deviceIndex, String deviceName){
		clickElement(SBDisconnected.crossBtn, deviceIndex, deviceName);
	}
}
