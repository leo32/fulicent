package com.fulicent.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.fulicent.api.entity.User;

@Mapper
public interface UserDao {
	
	@Insert("INSERT INTO "+
			" fulicent.user"+
			" ("+
			" `UserName`,"+
			" `Email`,"+
			" `Password`,"+
			" `Token`,"+
			" `Status`"+
			" ) VALUES"+
			" ("+
			" #{userName},"+
			" #{email},"+
			" #{password},"+
			" #{token},"+
			" #{status}"+
			")")
    @SelectKey(statement = "SELECT LAST_INSERT_ID() AS id", keyProperty = "id", before = false, resultType = int.class)
    int Save(User user);
	
	 @Update("UPDATE"+ 
		 		" fulicent.user"+ 
		 		" SET `UserName`=#{userName},"+
		 		"  `Email`=#{email},"+
		 		"  `Password`=#{password},"+
		 		"  `Token`=#{token},"+
		 		"  `Status`=#{status}"+
		 		" WHERE " +
		 		" `Id`=#{id}")
	int Update(User users);
	 
	 @Select("<script> "+
			    "SELECT " +
			    "t.* " +
			    "FROM " +
			    " fulicent.user t " +
			    "WHERE " +
			    " t.Status=1 " +
			    "<if test='userName!=\"\" and userName!=null'>" +
			    " and t.UserName= '${userName}' " +
			    "</if>  " +	    
			    "<if test='email!=\"\" and email!=null'>" +
			    " and t.Email= '${email}' " +
			    "</if>  " +	    
			    "<if test='password!=\"\" and password!=null'>" +
			    " and t.Password='${password}' " +
			    "</if>  " +	
			    "<if test='token!=\"\" and token!=null'>" +
			    " and t.Token='${token}' " +
			    "</if>  " +	
			    "<if test='sort == \"Id\"'>  " +
			    " ORDER BY t.Id ${order} " +
			    "</if>  " +
			    "<if test ='limit gt 0'>" +
			    "LIMIT #{limit} OFFSET #{skip}</if>" +
			    "</script>")
			List<User> Users(@Param("limit") int limit, @Param("skip") int skip, @Param("sort") String sort, @Param("order") String order, @Param("userName") String userName, @Param("email") String email, @Param("password") String password, @Param("token") String token);

	 
	 @Select("<script> "+
			    "SELECT " +
			    "count(*) " +
			    "FROM " +
			    " fulicent.user t " +
			    "WHERE " +
			    " t.Status=1 " +
			    "<if test='userName!=\"\" and userName!=null'>" +
			    " and t.UserName= '${userName}' " +
			    "</if>  " +	    
			    "<if test='email!=\"\" and email!=null'>" +
			    " and t.Email= '${email}' " +
			    "</if>  " +	    
			    "<if test='password!=\"\" and password!=null'>" +
			    " and t.Password='${password}' " +
			    "</if>  " +	
			    "<if test='token!=\"\" and token!=null'>" +
			    " and t.Token='${token}' " +
			    "</if>  " +	
			    "</script>")
			int Count(@Param("userName") String userName, @Param("email") String email, @Param("password") String password, @Param("token") String token);
}
