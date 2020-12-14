package jwtAuth;



import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;


public class TestEndpointWithJwt {

    /** 1. Authenticate and generate the token
     *  2. Extract the token using json Path
     *  3. Send the request with jwt token
     */
//Unirest.setTimeouts(0, 0);
//    HttpResponse<String> response = Unirest.post("http://synapzecx.bondqa.com:5000/connect/token")
//            .header("Content-Type", "application/x-www-form-urlencoded")
//            .body("grant_type=client_credentials&client_id=CoreServerToServerApp&client_secret=secret&scope=SynapzeCX&tenant=FORD")
//            .asString();


    private String body = "grant_type=client_credentials&client_id=CoreServerToServerApp&client_secret=secret&scope=SynapzeCX&tenant=FORD";
    private String access_token = "";
    //Generate the token

    @BeforeMethod
    public String getToken(){
      ValidatableResponse response = given().contentType("application/x-www-form-urlencoded")
              .formParam("grant_type","client_credentials")
              .formParam("client_id","CoreServerToServerApp")
              .formParam("client_secret","secret")
              .formParam("scope","SynapzeCX")
              .formParam("tenant","FORD")
              .when()
              .post("http://synapzecx.bondqa.com:5000/connect/token")
              .then();


    String accessToken = response.extract().path("access_token").toString();
       return "Bearer " + accessToken;

        //todo -

    }

    //Unirest.setTimeouts(0, 0);
//        HttpResponse<String> response = Unirest.get("http://synapzecx.bondqa.com:8081/api/resources")
//        .header("Content-Type", "application/json")
//        .header("Authorization", "eyJhbGciOiJSUzI1NiIsImtpZCI6IjAzRTBGMUM0QzhDNDBEMUE3QjAwMEUwMEQ4QjYwRjM4RjIyNTNDNjMiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJBLUR4eE1qRURScDdBQTRBMkxZUE9QSWxQR00ifQ.eyJuYmYiOjE2MDc0OTI0MjcsImV4cCI6MTYwNzU2NDQyNywiaXNzIjoiaHR0cDovL3N5bmFwemVjeC5ib25kcWEuY29tOjUwMDAiLCJhdWQiOlsiaHR0cDovL3N5bmFwemVjeC5ib25kcWEuY29tOjUwMDAvcmVzb3VyY2VzIiwiU3luYXB6ZUNYIl0sImNsaWVudF9pZCI6IkNvcmVTZXJ2ZXJUb1NlcnZlckFwcCIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvcm9sZSI6IlNlcnZlckNvbW11bmljYXRpb24iLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJTZXJ2ZXJBUElfQ29tbXVuaWNhdGlvbiIsInRlbmFudGNvZGUiOiJGT1JEIiwidWMiOiJDb3JlU2VydmVyVG9TZXJ2ZXJBcHAiLCJyb2xlIjoiMCIsImdyIjoiIiwicG0iOiJINHNJQUFBQUFBQUFDNHRtVUdJSVpuQmxDR0lJQTVQeERNNE0vZ3krUUJqSzRNZmdDZVE1TW9RQWFYOGdUNGtobGdFQVFKUUpGREFBQUFBPSIsImxvY2FsZSI6ImVuLVVTIiwianIiOiIiLCJsYyI6Ii8yLyIsInV0IjoiSDRzSUFBQUFBQUFBQzgxYTIyN2FRQkNkVDBFOE4ycTV0QTk5Yzh3dGlYRUlCdkpRVllnQ0txZ09SbHdpVlZYL3ZXZkdJUmdrSHRwS1o2S1ZyYjNPT1RNN083dXMrU0svcEN4RDJjcGNOaEpLSmpQa3l2SlpWcktYRk9rZFNnT1p5SGNaNFoyaU5tOHZ5M3VwNGlrWGVzUjRQNzIyUjNJUGlRSGFicENMVWZNYmZmOGRyeUZOMUYzR3V3WEt0ZlR4anREemY5RzYwb1lzRnRvRDdNVENDbEZhb3JTU25aU2c1d1E1N2FlU1dCdzZ5S1d5eHB6T01mNkhKR2hiSTJXUXRLT3hDR1NLbEtFbXQwVWZMVk04UzNsR3oyL29PNmR4YWFCM0NmMHlZNklTRkgxcXBaMHN3R2xMNHpMRWZEVEozbjg2RndQemk1MlRkeXFmeE9xVWhUK1hEc3BQaHE0ZXVqVXJiY3hUL1RnZHJIS1F3TVF1dmFKWDNDTFlLWThxOGdGeU9qZFRyTlVKNnVaT1hHckdwYmlhV0R4aWk5L3FxU2s0K0hpRzl5eEVLRTh3cWdTcGE1T2lMSmFRdEhKam9wNHh4UjZ5QW8rcDllWHRhblBzcFY1cjlJQitzQVRYL3VleDJqZFM1ZXRDR1cwTktUL3hCTVlubFo4V09YaW52d25HYWI4eEpHYzRhZlh4MXQxMkRFWXpwREh5TTNqcnptcHk1bStEM3hxMWVqTFU4OWlDT28rSjlJQS9CSU1VNDlTZitlaUJuZEkzR1Buc2dONXpzMzErUXQzREI3TXpqNmk4QVE1VkdvY3Vmc3ZmUWhvZmtXZm5HNHhZV2x6MHhPVlpPSUJramJSc2ZVOXgrUjVWb3lQVzZZZ2ZpUkY2WWY3TDlhRWlLcytEaXFnOEw5STdzcG5kQkcyUVo5djVISnRwN1hOc1Q1dlhYYkIxcDkrano0SytxbzhjRlBucTVWN2N4L09LRER6OXIrSzg4anpSYTY3b2RWZjBpbnhBOGovdjgrTGZaUTY4T0hpWkF5OE9YdWJ3aWNhaER4bHRhVUZXZzRhcDMxd0RrOFQ3R25RUEhVUHk5OWNBL1VPMHhYaDR0bzFNVmcvUmhZWFpob3lZYk5zT0pIRVJJL3o2aWMyeVhScG1EL1VKVW9Pb1p3SXBDZG0yaXRmRTJKRG90ZGQyLzhmVlUrTXNlNlhrUHNTZXo1RkR0RDNPS0EvemFGMGU1dEc2M0xXU1lIU1BqRG1BcEpDOFZ2TDlqQjNqbWJiTlBTakF2aklpWTdMMTdBQ3RoYlVTay9Yayt1MGpKTVVZRThrZGZjL1dmM1Z4OTAvMUl2YWV6WjNQL0t3WlFoNHZ4bXM4NkVKZWo3aFdXcVluZC8vVStXU2ZoM1IxUGhvdU0vWXBuc2FFQVJtVEcrTmJEbnJtMGFCRG5VL1ZNdGVVcDJkVU9Dbnc0dEE5WktsMUgyaVlxcDkrM1dTZUV1N3MvbmNJbENydVBmVW0yQlA3NmkvdndML0tIMFdCeHE3bU1RQUEiLCJzY29wZSI6WyJTeW5hcHplQ1giXX0.ByUOY5k6KtZGUf8s02S1MszeuJyht_Twyf76Qe9UmejaSIl_QkNpBZgrMJ9Rh2k03RdHYFQt1ougZR5RSU8CXZ6G0FgkobxnudXmP3kI2JVhMs6N-HU7CGNYo4pqzK-YpGpgbo_CUaUQquhlwh__pcSOViuC6MQplGbSmRBkgwyCwoCi50JP9FNkG-GD6p9zAzJzXW_2_JNadrszm4DKCnjLn4IKO9lVE4YhBJdxN_03u0Fo3_dALFxaQE1vm_YuAVOyihhpuXNfCKMbQ2h6KKnGlVfp8JkBRkrrSeInWKxj_3SPGQtcRtvEqCo34XH0hpImtlgikCWHb7GPHO_-bR1O-AU3Ry_3Fq04yfAfRB5_9sH13PvTVEDzn1WLwCAev6mXbxind-tW4N0nC4KVkvRXxYZZAdz65oXZf_uyj8nYrcMPg-9vc6WHuX8M2_1qX7gUu6XM21hwNM8uBkURfckAjNkRvrmL3nc-cRBnC-182HLp1MDOiB5sXw-TU2Fc5B9T9zSaK_BCc1XjFrNExxtkAHTE8EwZVNfBZsJt2uNQCbzX6Xv1tbEGCWATmzHPkyDW192jdikTySRQhF3dGnLxqJIHD4Wllu-e9CsHJc751pZqUYdnM6eLTlDEaojpD4wqWjdofJztMYjdsg9NgyQoh89Dpdk9-trbqTIOeO4")
//        .asString();

    //Get all the resources
    @Test
    public void getResources(){
        ValidatableResponse response = given().contentType("application/json")
                .header("Authorization",getToken())
                .when()
                .get("http://synapzecx.bondqa.com:8081/api/resources")
                .then()
                .log()
                .all()
                .assertThat().statusCode(200);

    }

//Unirest.setTimeouts(0, 0);
//    HttpResponse<String> response = Unirest.get("http://synapzecx.bondqa.com:8081/api/resources/reindex")
//            .header("Content-Type", "application/json")
//            .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjAzRTBGMUM0QzhDNDBEMUE3QjAwMEUwMEQ4QjYwRjM4RjIyNTNDNjMiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJBLUR4eE1qRURScDdBQTRBMkxZUE9QSWxQR00ifQ.eyJuYmYiOjE2MDc4MjYwNTAsImV4cCI6MTYwNzg5ODA1MCwiaXNzIjoiaHR0cDovL3N5bmFwemVjeC5ib25kcWEuY29tOjUwMDAiLCJhdWQiOlsiaHR0cDovL3N5bmFwemVjeC5ib25kcWEuY29tOjUwMDAvcmVzb3VyY2VzIiwiU3luYXB6ZUNYIl0sImNsaWVudF9pZCI6IkNvcmVTZXJ2ZXJUb1NlcnZlckFwcCIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvcm9sZSI6IlNlcnZlckNvbW11bmljYXRpb24iLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJTZXJ2ZXJBUElfQ29tbXVuaWNhdGlvbiIsInRlbmFudGNvZGUiOiJGT1JEIiwidWMiOiJDb3JlU2VydmVyVG9TZXJ2ZXJBcHAiLCJyb2xlIjoiMCIsImdyIjoiIiwicG0iOiJINHNJQUFBQUFBQUFDNHRtVUdJSVpuQmxDR0lJQTVQeERNNE0vZ3krUUJqSzRNZmdDZVE1TW9RQWFYOGdUNGtobGdFQVFKUUpGREFBQUFBPSIsImxvY2FsZSI6ImVuLVVTIiwianIiOiIiLCJsYyI6Ii8yLyIsInV0IjoiSDRzSUFBQUFBQUFBQzgxYTIyN2FRQkNkVDBFOE4ycTV0QTk5Yzh3dGlYRUlCdkpRVllnQ0txZ09SbHdpVlZYL3ZXZkdJUmdrSHRwS1o2S1ZyYjNPT1RNN083dXMrU0svcEN4RDJjcGNOaEpLSmpQa3l2SlpWcktYRk9rZFNnT1p5SGNaNFoyaU5tOHZ5M3VwNGlrWGVzUjRQNzIyUjNJUGlRSGFicENMVWZNYmZmOGRyeUZOMUYzR3V3WEt0ZlR4anREemY5RzYwb1lzRnRvRDdNVENDbEZhb3JTU25aU2c1d1E1N2FlU1dCdzZ5S1d5eHB6T01mNkhKR2hiSTJXUXRLT3hDR1NLbEtFbXQwVWZMVk04UzNsR3oyL29PNmR4YWFCM0NmMHlZNklTRkgxcXBaMHN3R2xMNHpMRWZEVEozbjg2RndQemk1MlRkeXFmeE9xVWhUK1hEc3BQaHE0ZXVqVXJiY3hUL1RnZHJIS1F3TVF1dmFKWDNDTFlLWThxOGdGeU9qZFRyTlVKNnVaT1hHckdwYmlhV0R4aWk5L3FxU2s0K0hpRzl5eEVLRTh3cWdTcGE1T2lMSmFRdEhKam9wNHh4UjZ5QW8rcDllWHRhblBzcFY1cjlJQitzQVRYL3VleDJqZFM1ZXRDR1cwTktUL3hCTVlubFo4V09YaW52d25HYWI4eEpHYzRhZlh4MXQxMkRFWXpwREh5TTNqcnptcHk1bStEM3hxMWVqTFU4OWlDT28rSjlJQS9CSU1VNDlTZitlaUJuZEkzR1Buc2dONXpzMzErUXQzREI3TXpqNmk4QVE1VkdvY3Vmc3ZmUWhvZmtXZm5HNHhZV2x6MHhPVlpPSUJramJSc2ZVOXgrUjVWb3lQVzZZZ2ZpUkY2WWY3TDlhRWlLcytEaXFnOEw5STdzcG5kQkcyUVo5djVISnRwN1hOc1Q1dlhYYkIxcDkrano0SytxbzhjRlBucTVWN2N4L09LRER6OXIrSzg4anpSYTY3b2RWZjBpbnhBOGovdjgrTGZaUTY4T0hpWkF5OE9YdWJ3aWNhaER4bHRhVUZXZzRhcDMxd0RrOFQ3R25RUEhVUHk5OWNBL1VPMHhYaDR0bzFNVmcvUmhZWFpob3lZYk5zT0pIRVJJL3o2aWMyeVhScG1EL1VKVW9Pb1p3SXBDZG0yaXRmRTJKRG90ZGQyLzhmVlUrTXNlNlhrUHNTZXo1RkR0RDNPS0EvemFGMGU1dEc2M0xXU1lIU1BqRG1BcEpDOFZ2TDlqQjNqbWJiTlBTakF2aklpWTdMMTdBQ3RoYlVTay9Yayt1MGpKTVVZRThrZGZjL1dmM1Z4OTAvMUl2YWV6WjNQL0t3WlFoNHZ4bXM4NkVKZWo3aFdXcVluZC8vVStXU2ZoM1IxUGhvdU0vWXBuc2FFQVJtVEcrTmJEbnJtMGFCRG5VL1ZNdGVVcDJkVU9Dbnc0dEE5WktsMUgyaVlxcDkrM1dTZUV1N3MvbmNJbENydVBmVW0yQlA3NmkvdndML0tIMFdCeHE3bU1RQUEiLCJzY29wZSI6WyJTeW5hcHplQ1giXX0.cIwbbYUwpGRCISKnKuDKN4VPzbh2B57BfztsTha_XXcm636porSxlh0Qxb8AnMClCODX722i5JQJ7RpYlwEpjk64b7o7rI9eC2H1xgDSHkpxVTAlNkPDr5A7fVqrauYWSEIUhq2eyH9g4dVolR30rxMYrTel8VWYDAmkAax_A0cC5uCGoto_g7vDdCxdxnT7QrU5dCcIqo-2QV1Ehk7874_C50o2AIWK6xljswItFghykuvBwFoH4zy9XGs_ZVzbDGwFgC6c6Y9FzaJJzwE2GgYwzBVtY_DmHXeBTBZtalWW7nOJjlNzzpX8DtQ1LoztAu2AMl64eA2VvqAOrzLm4wGHIvf891E-PdYlbi6BsZ9emG6dZglaEK7_BCsLh-YtqA2J3dNEVPiU40VTXfNV7BD-C0ummQiK48uooFYL6PP77laVnq0GaodC4ZyG-2oQ5EL9h4eRQoSIYTPv6rtzJwEZAjn0mEXUuR8gLb8KNxMM1pG_C5H7O_tx39uTONCeCOmCcVkYyiICnvnsIg80pQAxemk5B4arbmn9QMJ4zvg43hffSewjwlQ2rSets9FqOtxcv2c8zHdmd1P5JwLoHqam6RaF79Ianv4fk7cm9eeP_TCSxYkzPlWSmTUEC1Rc9Hyaj-2HgRr40iY8CWKEusxyn2v9UrBkiK-vYEqzJBs")
//            .body("")
//            .asString();

    //Reindex Resources
    @Test
    public void Reindex() {
        ValidatableResponse response = given().contentType("application/json")
                .header("Authorization",getToken())
                .when()
                .get("http://synapzecx.bondqa.com:8081/api/resources/reindex")
                .then()
                .log()
                .all()
                .assertThat().statusCode(200);

    }

    //Upload File - Video

//    Unirest.setTimeouts(0, 0);
//    HttpResponse<String> response = Unirest.post("synapzecx.bondqa.com:8080/api/Media/Upload")
//            .header("Authorization", "{{Token}}")
//            .field("file", new File("/C:\Users\Amita.patel\OneDrive - Bond Brand Loyalty, Inc\SynapzeCX - Phase 1\Resources\Assets/2018-SampleVideo.mp4"))
//            .asString();

// Error - Connection refused: connect
    @Test(enabled = false)
    public void UploadFileVideo(){
        String fileToUpload = "C:\\Users\\Amita.patel\\OneDrive - Bond Brand Loyalty, Inc\\SynapzeCX - Phase 1\\Resources\\Assets\\220030153-lottomax-june152018-winning-numbers";
        ValidatableResponse response = given()
                .header("Authorization",getToken())
                .multiPart("file",fileToUpload)
                //.formParam("file", fileToUpload)
                .when()
                .post("synapzecx.bondqa.com:8080/api/Media/Upload")
                .then()
                .log()
                .all()
                .assertThat().statusCode(200);


    }


    @Test
    public void sample(){
        Assert.assertTrue(true);
    }

    @Test
    public void sample2(){
        Assert.assertTrue(true);

    }


}



