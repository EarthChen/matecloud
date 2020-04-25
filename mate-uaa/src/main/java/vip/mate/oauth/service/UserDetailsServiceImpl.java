package vip.mate.oauth.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vip.mate.admin.feign.IUserApi;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private IUserApi userApi;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        vip.mate.admin.entity.User user = userApi.loadUserByUserName(userName).getData();
        log.info("用户名：{}", userName);
        return new User(user.getAccount(), user.getPassword(),true,true,
                true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin, ROLE_USER"));
    }
}
