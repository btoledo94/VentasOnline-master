package gt.umg.ventasonline.ws;

/**
 * Created by wilver on 28/05/17.
 */

public interface ResourceResponse<T> {

    public void success(int statusCode, T responseData);

    public void error(int errorCode, String error);
}
