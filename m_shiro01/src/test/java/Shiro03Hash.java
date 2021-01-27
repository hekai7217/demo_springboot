import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;

/**
 * @author 黄药师
 * @date 2021-01-25 9:20
 * @desc 巨梯学院 http://www.jutixueyuan.com
 * 
 *  测试 shiro的加密工具
 */
public class Shiro03Hash {

    @Test
    public void test01(){

        // 直接给一个字符 进行加密  
        // source 资源
        // 123 md5 加密后  202cb962ac59075b964b07152d234b70
        Md5Hash md5Hash = new Md5Hash("123");
        System.out.println("md5Hash = " + md5Hash);

    }

    @Test
    public void test02(){


        // 直接给一个字符 进行加密
        // source 资源
        // 加密不安全  + 加盐 (随机)  更加安全

        // 123 md5 +salt 加密后  e99a18c428cb38d5f260853678922e03
        Md5Hash md5Hash = new Md5Hash("123","abc");

        System.out.println("md5Hash = " + md5Hash);

    }

    @Test
    public void test03(){


        // 直接给一个字符 进行加密
        // source 资源
        // 加密不安全  + 加盐 (随机)  + 算列次数(加密次数) 更加安全

        // 123 md5 +salt +算列次数  加密后  dcd171b1ae8d58251fbacbf91a89c82d
        Md5Hash md5Hash = new Md5Hash("123","abc",10);
        System.out.println("md5Hash = " + md5Hash);

    }
    
    @Test
    public void test04(){

        Object source = "123" ;
        Object salt = "abc";
        SimpleHash simpleHash = new SimpleHash("md5", source, salt, 10);

        System.out.println("simpleHash = " + simpleHash);

    }
    
}


