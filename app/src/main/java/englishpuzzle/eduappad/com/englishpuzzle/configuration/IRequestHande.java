package englishpuzzle.eduappad.com.englishpuzzle.configuration;

/**
 * Created by Vo Quang Hoa on 19/09/2015.
 */
public interface IRequestHande<T> {
    public void onError(String reason);
    public void onSuccess(T data);
}
