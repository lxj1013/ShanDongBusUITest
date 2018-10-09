package cn.com.hisistar.shandongbusuitest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DATE 2018/9/18 17:00
 * AUTHOR lxj
 * 南无阿弥陀佛
 */
public class BusStation {

    public static final int CHINESE = 1;
    public static final int ENGLISH = 2;
    public static final int ZH_AND_EN = 3;


    private String stationName;
    private boolean isSubway;

    public BusStation(String stationName, boolean isSubway) {
        this.stationName = stationName;
        this.isSubway = isSubway;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public boolean isSubway() {
        return isSubway;
    }

    public void setSubway(boolean subway) {
        isSubway = subway;
    }


    public int getNameInfo() {
        String nameStr = getStationName();
        if (getLastZhIndex(nameStr) > nameStr.length() - 10) {
            return CHINESE;
        }
        if (getLastZhIndex(nameStr) == 0) {
            return ENGLISH;
        }
        return ZH_AND_EN;
    }

    public int getLastZhIndex(String stationName) {
        int index = 0;
        for (int i = 0; i < stationName.length(); i++) {
            String nameStr = stationName.substring(i, i + 1);
            if (isChinese(nameStr)) {
                index = i + 1;
            }
        }
        return index;
    }

    private boolean isChinese(String str) {

        String regEx = "[\\u4e00-\\u9fa5]+";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(str);

        if (m.find())
            return true;
        else
            return false;

    }

    @Override
    public String toString() {
        return "BusStation{" +
                "stationName='" + stationName + '\'' +
                ", isSubway=" + isSubway +
                '}';
    }
}

