package app.gtuinnovationcouncil.gicapp;

/**
 * Created by KINJAL on 2/10/2017.
 */


    import retrofit.Callback;
    import retrofit.client.Response;
    import retrofit.http.Field;
    import retrofit.http.FormUrlEncoded;
    import retrofit.http.POST;

    /**
     * Created by Belal on 11/5/2015.
     */
    public interface RegisterAPI {
        @FormUrlEncoded
        @POST("/RetrofitExample/insert.php")
        public void insertUser(
                @Field("firstname") String firstname,
                @Field("lastname") String lastname,
                @Field("password") String password,
                @Field("email") String email,
                @Field("gender") String gender,
                @Field("enrollment_no") String enrollment_no,
                @Field("mobile_no") String mobile_no,
                @Field("clg_name") String clg_name,
                Callback<Response> callback);
    }

