package gt.umg.ventasonline.Common;

import gt.umg.ventasonline.dto.SessionDto;

/**
 * Created by wilver on 28/05/17.
 */

public class Common {

    private static String urlWs;
    private static SessionDto session;

    public static void setUrlWs(String urlWs){
        Common.urlWs = urlWs;
    }

    public static String getUrlWs(){
        return Common.urlWs;
    }

    public static SessionDto getSession() {
        return Common.session;
    }

    public static void setSession(SessionDto session) {
        Common.session = session;
    }

}
