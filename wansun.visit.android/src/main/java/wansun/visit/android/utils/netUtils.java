package wansun.visit.android.utils;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import wansun.visit.android.api.apiManager;
import wansun.visit.android.global.waifangApplication;

/**
 * Created by User on 2019/2/12.
 */

public class netUtils {
    public static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .client(waifangApplication.getInstence().getClient())
                .baseUrl(apiManager.baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return  retrofit;
    }
}
