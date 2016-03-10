package com.nikita.learnletters.network;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.nikita.learnletters.models.RealmString;
import com.nikita.learnletters.models.WordObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.internal.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nikita on 10.03.16.
 */
public class NetworkManager {
    private static String baseUrl = "http://dictionary.skyeng.ru/";

    public static void downloadWords(String[] meaningIds, int width) {
        Type token = new TypeToken<RealmList<RealmString>>(){}.getType();
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .registerTypeAdapter(token, new TypeAdapter<RealmList<RealmString>>() {

                    @Override
                    public void write(JsonWriter out, RealmList<RealmString> value) throws IOException {
                        // Ignore
                    }

                    @Override
                    public RealmList<RealmString> read(JsonReader in) throws IOException {
                        RealmList<RealmString> list = new RealmList<>();
                        try {
                            in.beginArray();
                            while (in.hasNext()) {
                                list.add(new RealmString(in.nextString()));
                            }
                            in.endArray();
                        } catch (java.io.IOException e) {
                            e.printStackTrace();
                        }
                        return list;
                    }
                })
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
        WordNetworkService service = retrofit.create(WordNetworkService.class);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < meaningIds.length - 1; i++) {
            builder.append(meaningIds[i]);
            builder.append(",");
        }
        builder.append(meaningIds[meaningIds.length - 1]);
        String allIDs = builder.toString();
        service.getWords(allIDs,width).enqueue(new Callback<ArrayList<WordObject>>() {
            @Override
            public void onResponse(Call<ArrayList<WordObject>> call, Response<ArrayList<WordObject>> response) {
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(response.body());
                realm.commitTransaction();
            }

            @Override
            public void onFailure(Call<ArrayList<WordObject>> call, Throwable t) {

            }
        });
    }
}
