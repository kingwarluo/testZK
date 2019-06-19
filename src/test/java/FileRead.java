import net.sf.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-09-17 15:47
 */
public class FileRead {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<String>();
        File file = new File("D:/statSQL_log");
        BufferedReader br = null;
        String temp = null;
        int line = 1;
        try {
            br = new BufferedReader(new FileReader(file));
            while((temp = br.readLine()) != null) {
//                System.out.println(temp);
//                parseJson(temp);

                JSONObject json = JSONObject.fromObject(temp);
                String sql = (String) json.get("sql");
                sql = sql.replaceAll("\\s{1,}", " ");
                if(stringList.contains(sql) || !isDeptOrEmp(sql)) {
                    continue;
                }else {
                    System.out.println(sql);
                    stringList.add(sql);
                }
            }

            System.out.println(stringList.size());

//            File f1 = new File("D:/sql.sql");
//            if(!file.exists()) {
//                f1.getParentFile().mkdirs();
//            }
//            byte data[] = {88,89};
//            FileOutputStream fos = new FileOutputStream(f1);
//            fos.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isDeptOrEmp(String sql) {
        if(sql.contains("t_department") || sql.contains("t_employee")) {
            return true;
        }
        return false;
    }

}
