package com.jmy.shiro;


import com.jmy.dao.UserMapper;
import com.jmy.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthenticatingRealm {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录的验证实现方法
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken token2 = (UsernamePasswordToken) token;
        String username = token2.getUsername();
        String password = new String(token2.getPassword());
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("登陆成功");
        }else{
            throw new UnknownAccountException("用户名或密码有误！");
        }
        if (user.getStatus() == 0) {
            throw new UnknownAccountException("用户名已被禁用，请联系系统管理员！");
        }

        /**
         * principals: 可以使用户名，或d登录用户的对象
         * hashedCredentials: 从数据库中获取的密码
         * credentialsSalt：密码加密的盐值
         * RealmName:  类名（ShiroRealm）
         */
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), null, getName());
        return info; //框架完成验证
    }

    public static void main(String[] args) {
        String s = new Md5Hash("123","123",3).toString();
        System.out.println(s);
    }


}