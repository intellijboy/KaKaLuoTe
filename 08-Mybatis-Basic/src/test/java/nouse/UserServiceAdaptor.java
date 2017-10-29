package nouse;

/**
 * 用户服务适配器
 *
 * @author liuburu
 * @create 2017/08/09
 **/
public abstract class UserServiceAdaptor implements UserService {

    public abstract int insert();

    public abstract int delete();

    public int query(){
        return 0;
    }

    public int update(){
        return 0;
    }




}
