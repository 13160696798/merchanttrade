package conversion;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.ibatis.reflection.SystemMetaObject;

/**
 * Created by ADD on 2018/10/11.
 */
public class TXTConversionExcel {
    public static void main(String args[]) throws Exception {
        File file = new File("C:/Users/ADD/Desktop/交易对账文件.txt");// 将读取的txt文件
        TXTConversionExcel txtConversionExcel = new TXTConversionExcel();
        txtConversionExcel.conversionExcel(file);
    }

    public void conversionExcel(File file) throws Exception {
//        file = new File("C:/Users/ADD/Desktop/交易对账文件.txt");// 将读取的txt文件
        //获取当前时间(YYYYMMdd)
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());

        Map<String, List> map = new LinkedHashMap<>();
        Map<String, String> merchantMap = new LinkedHashMap<>();

        List<String> lineList = null;

        List<String> list = new ArrayList<>();

        if (file.exists() && file.isFile()) {

            InputStreamReader read = null;
            String line = "";
            BufferedReader input = null;
            WritableWorkbook wbook = null;
            WritableSheet sheet = null;

            try {
                read = new InputStreamReader(new FileInputStream(file), "utf-8");//读取txt文件
                input = new BufferedReader(read);
                Label t;

                while ((line = input.readLine()) != null) {
                    //save all content
                    list.add(line);
                    String[] sentence = line.split(",");   //分割line
                    //匹配商户号，除去其他行
                    if (!"".equals(sentence[0]) && sentence[0].matches("^[1-9]\\d*$")) {
                        if (map != null && map.containsKey(sentence[0])) {
                            lineList = map.get(sentence[0]);
                            lineList.add(line);
                            map.put(sentence[0], lineList);
                        } else {
                            lineList = new ArrayList<>();
                            lineList.add(line);
                            map.put(sentence[0], lineList);
                        }
                        merchantMap.put(sentence[0], sentence[0]);
                    }
                }
                String excelName;
                File file2;
                if (null != merchantMap && merchantMap.size() > 0) {
                    for (String merchantNo : merchantMap.keySet()) {
                        excelName = merchantNo + "_" + dateName;
                        file2 = new File("C:/Users/ADD/Desktop/" + excelName + ".xls");// 将生成的excel表格
                        wbook = Workbook.createWorkbook(file2); // 根据路径生成excel文件
                        sheet = wbook.createSheet("first", 0);  // 新标签页
                        int n = 0;// excel列数
                        int j = 0;//excel行数

                        for (int index = 0; index < list.size(); index++){
                            String[] sentence = list.get(index).split(",");   //分割line
                            if (!sentence[0].matches("^[1-9]\\d*$") || sentence[0].equals(merchantNo)) {
                                for (int i = 0; i < sentence.length; i++) {
                                    if(i ==14){
                                        continue;
                                    }else{
                                        t = new Label(n,j, sentence[i]);  //参数为：列，行，数据
                                        sheet.addCell(t);
                                        n++;
                                    }
                                }
                                n=0;
                                j++;
                            }
                        }
                        wbook.write();
                        wbook.close();

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                input.close();
                read.close();
            }
            System.out.println("over!");
            System.exit(0);
        } else {
            System.out.println("file is not exists or not a file");
            System.exit(0);
        }
    }
}
