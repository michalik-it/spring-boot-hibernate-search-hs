package netgloo.bridge;

import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.bridge.ParameterizedBridge;
import org.hibernate.search.bridge.StringBridge;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class CustomeDateBridge implements StringBridge, ParameterizedBridge {

    public static String RESOLUTION_PROPERTY = "resolution";
    private Resolution resolution = Resolution.MILLISECOND; // default

    @Override
    public void setParameterValues(@SuppressWarnings("rawtypes") Map parameters) {
        Object resolution = parameters.get(RESOLUTION_PROPERTY);
        if (resolution != null)
            this.resolution = Resolution.valueOf((String)resolution);
    }

    @Override
    public String objectToString(Object object) {
        if (object == null)
            return null;
        if (!(object instanceof Timestamp)) {
            throw new IllegalArgumentException("Argument is not instanse of java.sql.Timestamp");
        }
        Timestamp date = (Timestamp) object;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        SimpleDateFormat ft = new SimpleDateFormat("YYYYMMddHHmmssSSS");
        switch (this.resolution) {
            case YEAR:
                ft = new SimpleDateFormat("YYYY");
                break;
            
            case MONTH:
                ft = new SimpleDateFormat("YYYYMM");
                break;    
                
            case DAY:
                ft = new SimpleDateFormat("YYYYMMdd");
                break;

            case HOUR:
                ft = new SimpleDateFormat("YYYYMMddHH");
                break;
                
            case MINUTE:
                ft = new SimpleDateFormat("YYYYMMddHHmm");
                break;
            
            case SECOND:
                ft = new SimpleDateFormat("YYYYMMddHHmmss");
                break;

            default:
                break;
        }
        String formatted = ft.format(date);
        System.out.println("Formatted: " + formatted);
        return formatted;
    }

}
