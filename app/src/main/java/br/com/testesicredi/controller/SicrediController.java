package br.com.testesicredi.controller;

import br.com.testesicredi.SicrediService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SicrediController {

    private static final String apiBaseUrl = "http://5f5a8f24d44d640016169133.mockapi.io/api/";

    protected static SicrediController instance;

    protected SicrediService mSicrediService;

    protected Integer eventSelected;

    protected static SicrediController newInstance(){
        return new SicrediController();
    }

    public static SicrediController getInstance(){
        if(instance == null) instance = SicrediController.newInstance();
        return instance;
    }

    public SicrediService getSicrediService(){
        if (mSicrediService == null) initService();
        return mSicrediService;
    }

    protected void initService(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(apiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mSicrediService = retrofit.create(SicrediService.class);
    }

    public Integer getEventSelected() {
        return eventSelected != null ? eventSelected : 0;
    }

    public void setEventSelected(Integer eventSelected) {
        this.eventSelected = eventSelected;
    }
}
