package com.profile.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.profile.model.Profile;

 
@Mapper
public interface ProfileMapper {

//	public static final String getProfile = "SELECT NAME, EXP as EXPERIENCE,CLIENT,TECHNOLOGY FROM PROFILE_DETAILS WHERE PROFILE_ID = #{id} ";

	public static final String getProfile = "SELECT * FROM PROFILE_DETAILS WHERE PHONE= #{phone} ";
	public static final String updateProfile = "UPDATE PROFILE_DETAILS SET TECHNOLOGY = #{techology} ,EXP = #{exp},EMAIL = #{EMAIL} WHERE phone = #{phone}";

	public static final String insertProfile = "INSERT INTO PROFILE_DETAILS (PHONE,NAME,EXP,EMAIL,TECH) values (#{phone},#{name},#{exp},#{email},#{tech})";

	@Select(getProfile)
	Profile getProfileById(@Param("phone") String phone);

	@Update(updateProfile)
	int updateProfileByPhone(@Param("email") String email, @Param("phone") String phone,
			@Param("exp") int exp,@Param("tech") String tech);

	@Insert(insertProfile)
	int insertProfile(@Param("phone") String phone,@Param("name") String name,@Param("email") String email,@Param("exp") int exp, @Param("tech") String tech);
			 

}
