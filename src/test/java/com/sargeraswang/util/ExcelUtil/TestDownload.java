import com.seakoon.common.utils.excel.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Controller
@RequestMapping("/export")
@Api(value = "Api - ExportController", description = "导出")
public class ExportController {
    
    @RequestMapping(value = "excel", method = RequestMethod.GET)
    public void excel(HttpServletResponse response) throws IOException {

        String fileName= System.currentTimeMillis()+".xls";

        response.setHeader("Content-disposition","attachment;filename="+new String(fileName.getBytes("gb2312"),"ISO8859-1"));  //设置文件头编码格式

        response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");//设置类型

        response.setHeader("Cache-Control","no-cache");//设置头

        response.setDateHeader("Expires", 0);//设置日期头

        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map =new LinkedHashMap<>();
        map.put("name", "");
        map.put("age", "");
        map.put("birthday","");
        map.put("sex","");
        Map<String,Object> map2 =new LinkedHashMap<String, Object>();
        map2.put("name", "测试是否是中文长度不能自动宽度.测试是否是中文长度不能自动宽度.");
        map2.put("age", null);
        map2.put("sex", null);
        map.put("birthday",null);
        Map<String,Object> map3 =new LinkedHashMap<String, Object>();
        map3.put("name", "张三");
        map3.put("age", 12);
        map3.put("sex", "男");
        map3.put("birthday",new Date());
        list.add(map);
        list.add(map2);
        list.add(map3);
        Map<String,String> map1 = new LinkedHashMap<>();
        map1.put("name","姓名");
        map1.put("age","年龄");
        map1.put("birthday","出生日期");
        map1.put("sex","性别");

        OutputStream out = response.getOutputStream();
        ExcelUtil.exportExcel(map1, list, out);
        out.flush();
        out.close();


    }
}
