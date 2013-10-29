package com.pm.commons.api;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Pm 的实体类
 * @author peizicheng
 *
 */
public class PmInfo implements Serializable{
	private static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
	
	public int avergageAQE;
	public int positionAQE;
	public long updateUTGTime = -1;
	public String cityName;
	
	public PmInfo(){
		
	}
	
	public PmInfo(PmInfo info){
		avergageAQE = info.avergageAQE;
		positionAQE = info.positionAQE;
		updateUTGTime = info.updateUTGTime;
		cityName = info.cityName;
	}
	
	@Override
	public String toString() {
		return "PmInfo [avergageAQE=" + avergageAQE + ", positionAQE="
				+ positionAQE + ", updateUTGTime=" + updateUTGTime
				+ ", cityName=" + cityName + "]";
	}
	/**
	 * 增加Json
	 * @param jsonStr
	 * @return
	 * @throws JSONException 
	 */
	public static PmInfo fromJsonStr(String jsonStr) throws JSONException{
		PmInfo info = new PmInfo();
		JSONObject obj = new JSONObject(jsonStr);
		info.avergageAQE = obj.getInt("CityAverageAQE");
		info.positionAQE = obj.getInt("PositionAQE");
		info.cityName = obj.getString("CityName");
		String dateStr = obj.getString("UpdateTime");
		Date date = null;
		try {
			date = sFormat.parse(dateStr);
			info.updateUTGTime = date.getTime();
		} catch (ParseException e) {
			throw new JSONException("Date formate error!!");
		}
		return info;
	} 

}
