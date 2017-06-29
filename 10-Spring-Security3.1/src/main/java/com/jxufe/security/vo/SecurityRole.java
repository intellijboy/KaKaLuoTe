package com.jxufe.security.vo;

import com.jxufe.entity.Role;
import org.springframework.security.access.ConfigAttribute;

/**
 * Created by liuburu on 2017/6/28.
 */
public class SecurityRole extends Role implements ConfigAttribute {
    @Override
    public String getAttribute() {
        return getName();
    }
}
