package com.example.yadomanage.retrofit;

import com.example.yadomanage.adapter.Student;
import com.example.yadomanage.retrofit.model.ApiResponse;
import com.example.yadomanage.retrofit.model.ClassModel;
import com.example.yadomanage.adapter.Class;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login.php")
    Call<ApiResponse> performTeacherLogIn(
            @Field("email") String email,
            @Field("passwd") String passwd
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<ApiResponse> performTeacherSignUp(
            @Field("id") String id,
            @Field("name") String name,
            @Field("sex") String sex,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("passwd") String passwd
    );

    @FormUrlEncoded
    @POST("addclass.php")
    Call<ApiResponse> performClassAdd(
            @Field("id") String id,
            @Field("cname") String cname,
            @Field("clinkgroup") String clinkgroup,
            @Field("tid") String tid
    );

    @FormUrlEncoded
    @POST("displayclass.php")
    Call<List<Class>> showClass(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("displayStudent.php")
    Call<List<Student>> getStudentData(
            @Field("id") String sid
    );

    @FormUrlEncoded
    @POST("deleteStudent.php")
    Call<ApiResponse> deleteStudent(
            @Field("sid") String sid
    );

    @FormUrlEncoded
    @POST("updateStudent.php")
    Call<ApiResponse> updateStudent(
            @Field("sid") String sid,
            @Field("s_name") String s_name,
            @Field("s_dob") String s_dob,
            @Field("s_phone") String s_phone,
            @Field("s_email") String s_email,
            @Field("s_address") String s_address,
            @Field("s_hometown") String s_hometown
    );

    @FormUrlEncoded
    @POST("addStudent.php")
    Call<ApiResponse> addStudent(
            @Field("sid") String sid,
            @Field("s_name") String s_name,
            @Field("s_dob") String s_dob,
            @Field("s_phone") String s_phone,
            @Field("s_email") String s_email,
            @Field("s_address") String s_address,
            @Field("s_hometown") String s_hometown,
            @Field("s_password") String s_password,
            @Field("cid") String cid
    );
}
