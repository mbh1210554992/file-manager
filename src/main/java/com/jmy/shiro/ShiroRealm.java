package com.jmy.shiro;


import com.jmy.common.exception.CommonException;
import com.jmy.dao.RoleMapper;
import com.jmy.dao.UserMapper;
import com.jmy.model.entity.Permission;
import com.jmy.model.entity.Role;
import com.jmy.model.entity.User;
import com.jmy.model.entity.ResultCode;
import lombok.SneakyThrows;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 登录的验证实现方法
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken token2 = (UsernamePasswordToken) token;
        String username = token2.getUsername();
        String password = new String(token2.getPassword());
        User user = userMapper.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("登陆成功");
        }else{
            throw new CommonException(ResultCode.MOBILE_PASSWORD_ERROR);
        }
        if (user.getValid() == 0) {
            throw new CommonException(ResultCode.USERNAME_VALID_ERROR);
        }
        Role role = roleMapper.findRoleById(user.getRoleId());
        System.out.println("roleInfo: "+role);
        user.setRole(role);
        /**
         * principals: 可以使用户名，或d登录用户的对象
         * hashedCredentials: 从数据库中获取的密码
         * credentialsSalt：密码加密的盐值
         * RealmName:  类名（ShiroRealm）
         */
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), null, getName());
        return info; //框架完成验证
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取安全数据
        User user = (User) principalCollection.getPrimaryPrincipal();

        //2.获取权限数据
        List<Permission> perms = user.getRole().getPermissions();
        List<String> urls = new ArrayList<>();
        for(Permission permission : perms){
            urls.add(permission.getUrl());
        }

        //3.构造权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(urls);
        return info;
    }

//    public static void main(String[] args) {
//        String s = new Md5Hash("123","123",3).toString();
//        System.out.println(s);
//    }


}