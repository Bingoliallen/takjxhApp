package takjxh.android.com.taapp.utils;

/**
 * 类名称：全国组织机构代码 校验
 *
 * @Author: libaibing
 * @Date: 2020-04-24 13:51
 * @Description:
 **/
public class Regex_OrganizationCertificate {

    /**
     * 加权因子
     */
    private static int power[] = {3,7,9,10,5,8,4,2};

    /**
     * 判断机构代码是不是有效的
     * @param organizationCertificate
     * @return
     */
    public static boolean isOrganizationCertificate(String organizationCertificate) {
        String temp = organizationCertificate.toUpperCase();
        if (temp.contains("-")) {
            temp = temp.replace("-", "");
            System.out.println(temp);
        }
        if(temp.length()!=9){

            return false;
        }
        // 获取前面8位
        String pre8 = temp.substring(0,8);
        char[] pre8chars = pre8.toCharArray();// 0~z;
        // 获取校验码
        String code = temp.substring(8,9);
        boolean isCode = isCode(code,sum(pre8chars));
        return isCode;
    }

    /**
     * 求和
     * @param bit
     * @return
     */
    private static int sum(char[] bit){
        int sum = 0;
        for(int i=0;i<bit.length;i++){
            int intTemp = bit[i]>'9'?(bit[i]-'A'+10):Integer.parseInt(bit[i]+"");
            System.out.print(" "+intTemp);
            sum +=intTemp*power[i];
        }
        System.out.println();
        System.out.println(sum);
        return  sum;
    }

    /**
     * 判断机构代码的校验码和计算出的校验码是否一致
     * @param a
     * @param b
     * @return
     */
    private static boolean isCode(String a,int b){
        String codeTEmp = (11- b%11)==10?"X":(11- b%11)==11?0+"":(11- b%11)+"";
        System.out.println(codeTEmp);
        return a.equals(codeTEmp);
    }

}
