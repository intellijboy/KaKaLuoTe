import com.jxufe.security.vo.SecurityUser;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import java.security.Security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuburu on 2017/6/28.
 */
public class Test {

    @org.junit.Test
    public void test1() {
        Map<String, Collection<ConfigAttribute>> reCollectionMap = new HashMap<>();
        Collection<ConfigAttribute> configCollection1 = new ArrayList<>();
        Collection<ConfigAttribute> configCollection2 = new ArrayList<>();
        Collection<ConfigAttribute> configCollection3 = new ArrayList<>();
        Collection<ConfigAttribute> configCollection4 = new ArrayList<>();
        ConfigAttribute configAttribute1 = new SecurityConfig("ROLE_ADMIN_CREATE");
        ConfigAttribute configAttribute2 = new SecurityConfig("ROLE_ADMIN_QUERY");
        ConfigAttribute configAttribute3 = new SecurityConfig("ROLE_ADMIN_DELETE");
        ConfigAttribute configAttribute4 = new SecurityConfig("ROLE_ADMIN_UPDATE");
        configCollection1.add(configAttribute1);
        configCollection2.add(configAttribute2);
        configCollection3.add(configAttribute3);
        configCollection4.add(configAttribute4);
        reCollectionMap.put("/admin/create", configCollection1);
        reCollectionMap.put("/admin/query", configCollection2);
        reCollectionMap.put("/admin/delete", configCollection3);
        reCollectionMap.put("/admin/update", configCollection4);
        String url = "/admin/create";
        Object obj = url;
        System.out.println(reCollectionMap.containsKey(obj));
    }

    @org.junit.Test
    public void test2() {
        SecurityUser securityUser1 = new SecurityUser();
        securityUser1.setId(1001);
        securityUser1.setUsername("Jane");

        SecurityUser securityUser2 = null;
//        SecurityUser securityUser2 = new SecurityUser();
//        securityUser2.setId(1001);
//        securityUser2.setUsername("Jane");

        System.out.println(securityUser1.equals(securityUser2));
    }
}
