package com.as.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by yrx on 2016/6/24.
 */
public class CodeInitUtil {

    public static void initDao(String moduleName, String entityName) {

        StringBuilder isb = new StringBuilder();
        isb.append("package com.as.erp.trade.micro." + moduleName + ".dao;\r\n");
        isb.append("import com.as.common.dao.GenericDao;\r\n");
        isb.append("import com.as.erp.trade.micro." + moduleName +".entity." + entityName + ";\r\n");
        isb.append("public interface " + entityName +"Dao extends GenericDao<" + entityName + ", String> {\r\n");

        isb.append("}");

        try {
            File iDir = new File("O:/workspace-idea/trade-micro-s4h5/src/main/java/com/as/erp/trade/micro/" + moduleName);
            if (!iDir.exists()) {
                iDir.mkdirs();
            }
            File iFile = new File("O:/workspace-idea/trade-micro-s4h5/src/main/java/com/as/erp/trade/micro/" + moduleName + "/" + entityName + "Dao.java");
            if (!iFile.exists()) {
                iFile.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(iFile);
            fileWriter.write(isb.toString());
            fileWriter.close();
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void main(String[] args) {
        CodeInitUtil.initDao("test", "Test");
    }

}
