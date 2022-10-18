package com.chenle.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenle.shiro.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT name FROM role WHERE id IN (SELECT rid FROM role_user WHERE uid=(SELECT id FROM user WHERE name =#{principal}))")
    List<String> getUserRoleInfoMapper(@Param("principal") String principal);

    @Select({
            "<script>",
            "select info FROM permissions WHERE id IN ",
            "(SELECT pid FROM role_ps WHERE rid IN (",
            "SELECT id FROM role WHERE NAME IN ",
            "<foreach collection='roles' item='name' open='(' separator=',' close=')'>",
            "#{name}",
            "</foreach>",
            "))",
            "</script>"
            })
    List<String> getUserPermissionInfoMapper(@Param("roles")List<String> roles);
}